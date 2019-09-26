package com.thy.Bean;


public class Providers {

  private long providerId;
  private String providerName;
  private String providerCard;
  private String people;
  private String phone;
  private String address;
  private String fax;
  private String description;

  public Providers(long providerId, String providerName, String people, String phone, String address,String fax, String description) {
    this.providerId = providerId;
    this.providerName = providerName;
    this.people = people;
    this.phone = phone;
    this.address=address;
    this.fax = fax;
    this.description = description;
  }

  public Providers(long providerId, String providerName) {
    this.providerId = providerId;
    this.providerName = providerName;
  }

  public long getProviderId() {
    return providerId;
  }

  public void setProviderId(long providerId) {
    this.providerId = providerId;
  }


  public String getProviderName() {
    return providerName;
  }

  public void setProviderName(String providerName) {
    this.providerName = providerName;
  }


  public String getProviderCard() {
    return providerCard;
  }

  public void setProviderCard(String providerCard) {
    this.providerCard = providerCard;
  }


  public String getPeople() {
    return people;
  }

  public void setPeople(String people) {
    this.people = people;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public String getFax() {
    return fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "Providers{" +
            "providerId=" + providerId +
            ", providerName='" + providerName + '\'' +
            ", providerCard='" + providerCard + '\'' +
            ", people='" + people + '\'' +
            ", phone='" + phone + '\'' +
            ", address='" + address + '\'' +
            ", fax='" + fax + '\'' +
            ", description='" + description + '\'' +
            '}';
  }

  public Providers(String providerName, String providerCard, String people, String phone, String address, String fax, String description) {
    this.providerName = providerName;
    this.providerCard = providerCard;
    this.people = people;
    this.phone = phone;
    this.address = address;
    this.fax = fax;
    this.description = description;
  }

  public Providers(long providerId, String providerName, String providerCard, String people, String phone, String address, String fax, String description) {
    this.providerId = providerId;
    this.providerName = providerName;
    this.providerCard = providerCard;
    this.people = people;
    this.phone = phone;
    this.address = address;
    this.fax = fax;
    this.description = description;
  }
}
