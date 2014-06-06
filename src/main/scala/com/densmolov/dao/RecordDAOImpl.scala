package com.densmolov.dao

import com.densmolov.entity.Record
import org.slf4j.{LoggerFactory, Logger}
import org.hibernate.{Criteria, Session, SessionFactory}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import org.hibernate.criterion.{MatchMode, Restrictions}

/**
 * Created by Denis Smolov on 02-Jun-14.
 */

@Repository
class RecordDAOImpl extends RecordDAO {

  private final val logger: Logger = LoggerFactory.getLogger(classOf[RecordDAOImpl])

  @Autowired
  private val sessionFactory: SessionFactory = null

  override def addRecord(record: Record) {
    logger.info("New record was added.")
    sessionFactory.getCurrentSession.save(record)
  }

  override def listRecords(searchValue: String): java.util.List[Record] = {
    val session: Session = sessionFactory.getCurrentSession
    val criteria: Criteria = session.createCriteria(classOf[Record])
    if (searchValue != "") {
      criteria.add(Restrictions.like("name", searchValue, MatchMode.ANYWHERE))
    }
    val namePhones: java.util.List[Record] = criteria.list.asInstanceOf[java.util.List[Record]]
    if (namePhones != null && namePhones.size != 0) {
      logger.info("Some records were successfully found.")
      return namePhones
    }
    logger.info("List is null.")
    null
  }

  override def removeRecord(id: Integer) {
    logger.info("Record " + id + " was successfully removed.")
    sessionFactory.getCurrentSession.delete(getRecordById(id))
  }

  override def getRecordById(id: Integer): Record = {
    sessionFactory.getCurrentSession.get(classOf[Record], id).asInstanceOf[Record]
  }

}