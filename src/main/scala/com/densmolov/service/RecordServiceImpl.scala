package com.densmolov.service

import com.densmolov.entity.Record
import org.slf4j.{LoggerFactory, Logger}
import com.densmolov.dao.RecordDAO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.stereotype.Service
import java.util
import scala.util.control.Breaks
import scala.collection.JavaConversions


/**
 * Created by Denis Smolov on 02-Jun-14.
 */

@Service
class RecordServiceImpl extends RecordService{

  private var flag: Boolean = false

  private final val logger: Logger = LoggerFactory.getLogger(classOf[RecordServiceImpl])

  @Autowired
  private val recordDAO: RecordDAO = null

  @Transactional
  override def addRecord(record: Record) {
    validateIncomingRecord(record)
    if (flag) {
      recordDAO.addRecord(record)
    }
  }

  override def getFlag: Boolean = flag

  override def setFlag(flag: Boolean) {
    this.flag = flag
  }

  @Transactional
  override def listRecords(searchValue: String): java.util.List[Record] = {
    logger.info("Service!")
    recordDAO.listRecords(searchValue)
  }

  override def validateIncomingRecord(record: Record) {
    val loop = new Breaks
    setFlag(flag = true)
    val recList: java.util.List[Record] = recordDAO.listRecords(searchValue = "")
    if (recList != null) {
      loop.breakable {
        for (rec: Record <- JavaConversions.asScalaBuffer(recList)) {
          if (record.getName.toLowerCase == rec.getName.toLowerCase) {
            setFlag(flag = false)
            logger.info("Client is trying to push the name that already exists in the database.")
            loop.break()
          }
        }
      }
    }
  }

  @Transactional
  override def removeRecord(id: Integer) {
    recordDAO.removeRecord(id)
  }

}
