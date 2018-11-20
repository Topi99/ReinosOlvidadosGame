package org.team.project;

public class Notification {
  public boolean pending = true;
  public String from = "";
  public String to = "";
  
  public Notification(boolean pending, String from, String to) {
    this.pending = pending;
    this.from = from;
    this.to = to;
  }
}