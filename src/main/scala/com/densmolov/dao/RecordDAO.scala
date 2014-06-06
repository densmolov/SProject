package com.densmolov.dao

import com.densmolov.entity.Record

/**
 * Created by Denis Smolov on 02-Jun-14.
 */

trait RecordDAO {

  def addRecord(record: Record)

  def listRecords(searchValue: String): java.util.List[Record]

  def removeRecord(id: Integer)

  def getRecordById(userId: Integer): Record

}