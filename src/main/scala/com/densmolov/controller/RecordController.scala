package com.densmolov.controller

import com.densmolov.entity.Record
import com.densmolov.service.RecordService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation._
import javax.validation.Valid

/**
 * Created by Denis Smolov on 02-Jun-14.
 */

@Controller
class RecordController {

  private final val logger: Logger = LoggerFactory.getLogger(classOf[RecordController])

  @Autowired
  private val recordService: RecordService = null

  @RequestMapping(value = Array("/"), method = Array(RequestMethod.GET))
  def home: String = "forward:/pages/index.html"

  @RequestMapping(value = Array("/showData"), method = Array(RequestMethod.GET))
  @ResponseBody
  def listNamePhones(@RequestParam searchValue: String): java.util.List[Record] = {
    logger.info("Controller!")
    recordService.listRecords(searchValue)
  }

  @RequestMapping(value = Array("/add"), method = Array(RequestMethod.POST))
  def addRecord(@Valid record: Record, result: BindingResult): String = {
    if (!result.hasErrors) {
      recordService.addRecord(record)
    }
    "redirect:/"
  }

  @RequestMapping(value = Array("/validate"), method = Array(RequestMethod.GET))
  @ResponseBody
  def getFlag: Boolean = recordService.getFlag

  @RequestMapping(value = Array("/remove"), method = Array(RequestMethod.GET))
  def removeRecord(@RequestParam id: Integer): String = {
    recordService.removeRecord(id)
    "redirect:/"
  }

}