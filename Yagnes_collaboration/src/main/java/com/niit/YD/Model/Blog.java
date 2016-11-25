package com.niit.YD.Model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Entity
@Table(name="C_BLOG")
@Component
public class Blog extends BaseDomain {

@Id
private Integer id;
private String title;
private String description;
@Column(name="user_id")
private String userID;

@Transient
@Column(name="date_time")
private Date dateTime;
private char status;
private String reason;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getUserID() {
	return userID;
}
public void setUserID(String userID) {
	this.userID = userID;
}
public Date getDateTime() {
	return dateTime;
}
public void setDateTime(Date dateTime) {
	this.dateTime = dateTime;
}
public char getStatus() {
	return status;
}
public void setStatus(char status) {
	this.status = status;
}
public String getReason() {
	return reason;
}
public void setReason(String reason) {
	this.reason = reason;
}
@Override
public String toString() {
	return "Blog [id=" + id + ", title=" + title + ", description=" + description + ", userID=" + userID + ", dateTime="
			+ dateTime + ", status=" + status + ", reason=" + reason + "]";
}





}
