package com.osi.tools.bean;

import java.util.ArrayList;

public class SmsBean {
public String phonenumber;
public String message;
public long timestamp;
public int id;
public ArrayList<String> children;
public ArrayList<String> getChildren() {
	return children;
}
public void setChildren(ArrayList<String> children) {
	this.children = children;
}
public String getPhonenumber() {
	return phonenumber;
}
public void setPhonenumber(String phonenumber) {
	this.phonenumber = phonenumber;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public long getTimestamp() {
	return timestamp;
}
public void setTimestamp(long timestamp) {
	this.timestamp = timestamp;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}


}
