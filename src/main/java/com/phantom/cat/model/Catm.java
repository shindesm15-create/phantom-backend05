package com.phantom.cat.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "messages")
public class Catm {

    @Id
    private String id;

    private String from;
    private String to;

    // Text message
    private String content;

    // Image URL
    private String imageUrl;

    // TEXT, IMAGE, VIDEO, FILE, VOICE
    private String messageType;

    // Reply message text
    private String reply;

    // Temporary message
    private boolean temp;

    // Typing indicator
    private boolean isTyping;

    // Seen timestamp
    private long seenTime;

    // Sent timestamp
    private long timestamp;

    // SENT, DELIVERED, SEEN
    private String status;

    // Users who saved the message
    private List<String> savedBy = new ArrayList<>();

    public Catm() {
    }

    // ===== ID =====

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // ===== FROM =====

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    // ===== TO =====

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    // ===== CONTENT =====

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // ===== IMAGE URL =====

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // ===== MESSAGE TYPE =====

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    // ===== REPLY =====

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    // ===== TEMP =====

    public boolean isTemp() {
        return temp;
    }

    public void setTemp(boolean temp) {
        this.temp = temp;
    }

    // ===== TYPING =====

    public boolean isTyping() {
        return isTyping;
    }

    public void setTyping(boolean typing) {
        this.isTyping = typing;
    }

    // ===== SEEN TIME =====

    public long getSeenTime() {
        return seenTime;
    }

    public void setSeenTime(long seenTime) {
        this.seenTime = seenTime;
    }

    // ===== TIMESTAMP =====

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    // ===== STATUS =====

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // ===== SAVED BY =====

    public List<String> getSavedBy() {
        return savedBy;
    }

    public void setSavedBy(List<String> savedBy) {
        this.savedBy = savedBy;
    }
}