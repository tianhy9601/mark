package com.thy.Bean;


public class User {

  private long id;
  private String userName;
  private String userpassword;
  private String sex;
  private String birthday;
  private String userphone;
  private String userAddress;
  private String userlei;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getUserpassword() {
    return userpassword;
  }

  public void setUserpassword(String userpassword) {
    this.userpassword = userpassword;
  }


  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }


  public String getBirthday() {
    return birthday;
  }

  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }


  public String getUserphone() {
    return userphone;
  }

  public void setUserphone(String userphone) {
    this.userphone = userphone;
  }


  public String getUserAddress() {
    return userAddress;
  }

  public void setUserAddress(String userAddress) {
    this.userAddress = userAddress;
  }


  public String getUserlei() {
    return userlei;
  }

  public void setUserlei(String userlei) {
    this.userlei = userlei;
  }

  public User() {
  }

  public User(String userName, String userpassword, String sex, String birthday, String userphone, String userAddress, String userlei) {
    this.userName = userName;
    this.userpassword = userpassword;
    this.sex = sex;
    this.birthday = birthday;
    this.userphone = userphone;
    this.userAddress = userAddress;
    this.userlei = userlei;
  }

  public User(long id, String userName, String userpassword, String sex, String birthday, String userphone, String userAddress, String userlei) {
    this.id = id;
    this.userName = userName;
    this.userpassword = userpassword;
    this.sex = sex;
    this.birthday = birthday;
    this.userphone = userphone;
    this.userAddress = userAddress;
    this.userlei = userlei;
  }

  public User(long id,String userName, String sex, String birthday, String userphone, String userAddress, String userlei) {
    this.id = id;
    this.userName = userName;
    this.sex = sex;
    this.birthday = birthday;
    this.userphone = userphone;
    this.userAddress = userAddress;
    this.userlei = userlei;
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", userName='" + userName + '\'' +
            ", userpassword='" + userpassword + '\'' +
            ", sex='" + sex + '\'' +
            ", birthday='" + birthday + '\'' +
            ", userphone='" + userphone + '\'' +
            ", userAddress='" + userAddress + '\'' +
            ", userlei='" + userlei + '\'' +
            '}';
  }
}
