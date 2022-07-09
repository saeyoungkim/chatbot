package utils

// メッセージの検証のために必要
import java.nio.charset.StandardCharsets

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import java.util.Base64

import com.google.inject.Singleton
import javax.inject.Inject
import play.api.Configuration
import play.api.mvc.{AnyContent, Request}
import play.api.libs.json.Json

sealed abstract class Verifier {
  protected val conf: Configuration
  protected val hmacSHA256: String = "HmacSHA256"
  // 署名を検証する→Defaultはfalseにすることで、Defaultをそのまま使えるようにする
  def validateSignature(request: Request[AnyContent]): Boolean = false
}

@Singleton
class LineVerifier @Inject()(
  override val conf: Configuration
) extends Verifier  {
  final private val ChannelSecret = conf.get[String](ConfPath.Line.ChannelSecretPath)
  final private val XLineSignature = "X-Line-Signature"

  // TODO Booleanではなく例外が投げられたらresponseとして例外別に正しいresponseを投げれるようにする
  override def validateSignature(request: Request[AnyContent]): Boolean = {
    // Lineのガイドまま
    // https://developers.line.biz/ja/reference/messaging-api/#signature-validation
    val key: SecretKeySpec = new SecretKeySpec(ChannelSecret.getBytes(), hmacSHA256)
    val mac: Mac = Mac.getInstance(hmacSHA256)
    mac.init(key)
    val source: Array[Byte] = request.body.asJson.map { json =>
      Json.stringify(json)
    }.getOrElse {
      throw new RuntimeException("There is no request body.")
    }.getBytes(StandardCharsets.UTF_8)
    val signature: String = Base64.getEncoder.encodeToString(mac.doFinal(source))

    val xLineSignature: String = request.headers.get(XLineSignature).getOrElse("")

    signature == xLineSignature
  }
}
