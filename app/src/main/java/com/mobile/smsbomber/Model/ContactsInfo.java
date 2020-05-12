package com.mobile.smsbomber.Model;


public class ContactsInfo {
    private String contactId;
    private String displayName;
    private String phoneNumber;
    private int nbSms = 0;

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public int getNbSms() {
        return nbSms;
    }

    public void setNbSms(int nbSms) {
        this.nbSms = nbSms;
    }
}