package com.densmolov.entity

import org.hibernate.validator.constraints.NotEmpty
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import java.io.Serializable

/**
 * Created by Denis Smolov on 02-Jun-14.
 */

@Entity
@Table(name = "RECORD")
class Record() extends Serializable{

  @Id
  @Column(name = "ID")
  @GeneratedValue
  private val id: Integer = null

  @NotEmpty
  @Column(name = "NAME")
  private var name: String = null

  @NotEmpty
  @Column(name = "TELEPHONE")
  private var telephone: String = null

  def getId: Int = this.id

  def setName(str: String) { this.name = str }
  def getName: String = this.name

  def setTelephone(str: String) { this.telephone = str }
  def getTelephone: String = this.telephone

  override def toString =
    "Record was found! Id = " + id + ", name = " + name + ", telephone = " + telephone + "."

}
