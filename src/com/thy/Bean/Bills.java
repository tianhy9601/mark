package com.thy.Bean;


public class Bills {

  private int id;
  private String title;
  private String unit;
  private int number;
  private double money;
  private int pid;
  private int pay;
  private String time;

  private String providerName;

  public Bills(String title, String unit, int number, double money, int pid, int pay) {
    this.title = title;
    this.unit = unit;
    this.number = number;
    this.money = money;
    this.pid = pid;
    this.pay = pay;
  }

  public Bills(int id, String title, double money, int pay, String time, String providerName) {
    this.id = id;
    this.title = title;
    this.money = money;
    this.pay = pay;
    this.time = time;
    this.providerName = providerName;
  }

  public String getProviderName() {
    return providerName;
  }

  public void setProviderName(String providerName) {
    this.providerName = providerName;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public double getMoney() {
    return money;
  }

  public void setMoney(double money) {
    this.money = money;
  }

  public int getPid() {
    return pid;
  }

  public void setPid(int pid) {
    this.pid = pid;
  }

  public int getPay() {
    return pay;
  }

  public void setPay(int pay) {
    this.pay = pay;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public Bills() {
  }

  public Bills(int id, String title, String unit, int number, double money, int pid, int pay, String time) {
    this.id = id;
    this.title = title;
    this.unit = unit;
    this.number = number;
    this.money = money;
    this.pid = pid;
    this.pay = pay;
    this.time = time;
  }

  @Override
  public String toString() {
    return "Bills{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", unit='" + unit + '\'' +
            ", number=" + number +
            ", money=" + money +
            ", pid=" + pid +
            ", pay=" + pay +
            ", time='" + time + '\'' +
            ", providerName='" + providerName + '\'' +
            '}';
  }
}
