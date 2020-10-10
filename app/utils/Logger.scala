package utils

import play.api.Logger

/**
 * for logger
 */
trait Logger {
  protected val logger = Logger(this.getClass)
}
