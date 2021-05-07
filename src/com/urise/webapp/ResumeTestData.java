package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class ResumeTestData {
    public static void main(String[] args) {
        createResume("1", "gg");
    }

    public static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume("name");

        Organization.Position position = new Organization.Position(2021, Month.of(5), "trainee", "java");

        Organization google = new Organization("google", "google.com", position);


        TextSection Personal = new TextSection("LowSkills");
        TextSection Objective = new TextSection("noob");

        ListSection Achievement = new ListSection(Arrays.asList("course: startjava"));
//        List<String> Qualifications = new ListSection("java");
        OrganizationSection Experience = new OrganizationSection(Arrays.asList(google));
//        List<Organization> Education = new OrganizationSection();

        resume.addContact(ContactType.PHONE, "+7911");
        resume.addContact(ContactType.SKYPE, "skype");
        resume.addContact(ContactType.MAIL, "gmail");
        resume.addContact(ContactType.PROFILE, "vkontakte");

        resume.addSection(SectionType.PERSONAL, Personal);
        resume.addSection(SectionType.OBJECTIVE, Objective);
//        resume.addSection(SectionType.ACHIEVEMENT, Achievement);
//        resume.addSection(SectionType.QUALIFICATIONS, Qualifications);
//        resume.addSection(SectionType.EXPERIENCE, Experience);
//        resume.addSection(SectionType.EDUCATION, Education);

        System.out.println(resume.getSection(SectionType.EDUCATION));

        String phone = resume.getContact(ContactType.PHONE);
        String skype = resume.getContact(ContactType.SKYPE);
        String mail = resume.getContact(ContactType.MAIL);
        String profile = resume.getContact(ContactType.PROFILE);


        System.out.println(phone);
        System.out.println(skype);
        System.out.println(mail);
        System.out.println(profile);

        System.out.println(resume.toString());
        return resume;
    }
}