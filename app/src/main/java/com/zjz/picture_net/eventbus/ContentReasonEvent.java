package com.zjz.picture_net.eventbus;

/**
 * Created by zjz on 2018/1/30.
 */

public class ContentReasonEvent {
    private String reason;

    public String getReason() {
        return reason;
    }

    public ContentReasonEvent(String reason) {
        this.reason = reason;
    }
}