package com.example.dell.chat.Model;

import android.widget.Button;

/**
 * Created by Dell on 31/05/2019.
 */

public class Chat {

    private String sender;
    private String receiver;
    private String message;
    private String messageDec;
    private boolean isseen;

    public Chat(String sender, String receiver, String message, String messageDec, boolean isseen) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.messageDec = messageDec;
        this.isseen = isseen;
    }

    public Chat() {
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageDec() {
        return messageDec;
    }

    public void setMessageDec(String messageDec) {
        this.messageDec = messageDec;
    }

    public boolean isIsseen() {
        return isseen;
    }

    public void setIsseen(boolean isseen) {
        this.isseen = isseen;
    }
}
