package com.allert.allert.classes;

public class Notification {
    private User user;
    private Need_Request need_Request;
    private Respond respond;
    private Call call;

    public Notification(Entity user, Need_Request need_Request) {
        this.user = user;
        this.need_Request = need_Request;
    }

    public Notification(Volunteer user, Respond respond) {
        this.user = user;
        this.respond = respond;
    }

    public Notification(Entity user, Call call) {
        this.user = user;
        this.call = call;
    }
    public Notification(Volunteer user, Call call) {
        this.user = user;
        this.call = call;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Need_Request getNeed_Request() {
        return need_Request;
    }

    public void setNeed_Request(Need_Request need_Request) {
        this.need_Request = need_Request;
    }

    public Respond getRespond() {
        return respond;
    }

    public void setRespond(Respond respond) {
        this.respond = respond;
    }

    public Call getCall() {
        return call;
    }

    public void setCall(Call call) {
        this.call = call;
    }
}
