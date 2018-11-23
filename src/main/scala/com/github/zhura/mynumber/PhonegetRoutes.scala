package com.github.zhura.mynumber

import com.github.zhura.mynumber.util.DigitalSegmentUtils
import org.scalatra._
import org.slf4j.{Logger, LoggerFactory}

class PhonegetRoutes(buckets: Map[Int, Map[String, Seq[String]]]) extends ScalatraServlet {
  val logger: Logger = LoggerFactory.getLogger(getClass)

  get("/") {
    logger.debug("GET request to /")
    views.html.mynumber()()
  }

  get("/:digitalPhone") {
    logger.debug(s"GET request to /${params("digitalPhone")}")
    val input = params.getOrElse("digitalPhone", "").trim
    input match {
      case "" => error("Phone # is required")
      case possiblePhone if possiblePhone.length != 10 => error("Only 10 digit phones are supported", input)
      case possiblePhone if !(DigitalSegmentUtils.toLong(possiblePhone) > 0) => error(s"$input is not a valid phone #", input)
      case validPhone =>
        val memorized = DigitalSegmentUtils.toWord(validPhone, buckets)
        views.html.mynumber("", input)(memorized.mkString("; "))
    }
  }

  post("/") {
    logger.debug(s"POST request to / with params $params")
    val input = params.getOrElse("digitalPhone", "").trim
    input match {
      case "" => error("Phone # is required")
      case possiblePhone if possiblePhone.length != 10 => error("Only 10 digit phones are supported", input)
      case possiblePhone if !(DigitalSegmentUtils.toLong(possiblePhone) > 0) => error(s"$input is not a valid phone #", input)
      case validPhone => redirect(s"${fullUrl(requestPath)}/$validPhone")
    }
  }


  /**
    * Halts with error
    *
    * @param error        validation message
    * @param invalidValue invalid value (in case of bad request)
    * @param status       HTTP status of response
    */
  def error(error: String, invalidValue: String = "", status: Int = 400): Unit = {
    halt(status, views.html.mynumber(error, invalidValue)())
  }

}
