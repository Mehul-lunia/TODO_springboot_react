package org.example.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;


import java.time.LocalDateTime;

    // TODO : add a status attribute

@Entity
public class TODO
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todo_id;

    @Column(nullable = false)
    private String title;

    private String content;


    private LocalDateTime created_on;


    private LocalDateTime reminder_datetime;

    public LocalDateTime getReminder_datetime() {
        return reminder_datetime;
    }

    public void setReminder_datetime(LocalDateTime reminder_datetime) {
        this.reminder_datetime = reminder_datetime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public TODO(){}
    public TODO(String title, String content, LocalDateTime created_on)
    {
        this.title = title;
        this.content = content;
        this.created_on = created_on;
    }

    public LocalDateTime getCreated_on() {
        return created_on;
    }

    public void setCreated_on(LocalDateTime created_on) {
        this.created_on = created_on;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
