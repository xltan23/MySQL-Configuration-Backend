package sg.edu.nus.iss.server.models;

import java.util.Date;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Member {

    // Defining Member attributes
    private String name;
    private String telegram;
    private String grade;
    private String dateCreation;

    // Generate getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    // Member to JSON OBJECT
    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                .add("telegram", telegram)
                .add("grade", grade)
                .add("date", (new Date()).toString())
                .build();
    }

    // Create Member object from JsonObject payload
    public static Member create(SqlRowSet srs) {
        Member member = new Member();
        member.setName(srs.getString("name"));
        member.setTelegram(srs.getString("telegram"));
        member.setGrade(srs.getString("grade"));
        member.setDateCreation(srs.getString("date"));
        return member;
    }
}
