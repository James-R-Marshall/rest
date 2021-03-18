package com.web.rest;

public class greeting {
 private final long id;
 private final String content;
 
 public greeting (long id, String content){
     this.id = id;
     this.content = content;
 }
 public String getContent() {
     return content;
 }
 public long getId() {
     return id;
 }
}
