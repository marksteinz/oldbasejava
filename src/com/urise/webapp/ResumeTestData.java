package com.urise.webapp;

import com.urise.webapp.model.*;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = new Resume("name");
        TextSection text = new TextSection("qwe");

        resume.addContact(ContactType.PHONE, "+7911");
        resume.addContact(ContactType.SKYPE, "gg");
        resume.addContact(ContactType.SKYPE, "gg2");
        resume.addSection(SectionType.EDUCATION, text);

        System.out.println(resume.getSection(SectionType.EDUCATION));

        String skype = resume.getContact(ContactType.SKYPE);

        System.out.println(skype);
        System.out.println(skype);


    }
}
