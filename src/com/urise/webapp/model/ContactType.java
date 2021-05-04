package com.urise.webapp.model;

public enum ContactType {
    PHONE("номер телефона"),
    SKYPE("скайп"),
    MAIL("эл.почта"),
    PROFILE("личная страница");

    private  final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
