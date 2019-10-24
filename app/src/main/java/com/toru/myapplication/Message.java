package com.toru.myapplication;

public class Message {
    private  String ContactNumber;
    private  String MessageText;
    private  String Photo;

    public Message() {
    }

    public Message(String contactNumber, String messageText) {
        ContactNumber = contactNumber;
        MessageText = messageText;
    }

    public Message(String contactNumber, String messageText, String photo) {
        ContactNumber = contactNumber;
        MessageText = messageText;
        Photo = photo;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public Message setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
        return this;
    }

    public String getMessageText() {
        return MessageText;
    }

    public Message setMessageText(String messageText) {
        MessageText = messageText;
        return this;
    }

    public String getPhoto() {
        return Photo;
    }

    public Message setPhoto(String photo) {
        Photo = photo;
        return this;
    }
}
