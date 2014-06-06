package com.densmolov.service

import com.densmolov.entity.Record

/**
 * Created by Denis Smolov on 02-Jun-14.
 */

trait RecordService {

  def addRecord(record: Record)

  def getFlag: Boolean

  def setFlag(flag: Boolean)

  def validateIncomingRecord(record: Record)

  def listRecords(searchValue: String): java.util.List[Record]

  def removeRecord(id: Integer)

}
