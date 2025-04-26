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
    private Long id;
    @Column(nullable = false)
    private String title;
    private String content;
    private LocalDateTime created_on;
    private LocalDateTime updated_on;
    private boolean status;


    public Long getId()
    {
        return this.id;
    }
    public LocalDateTime getUpdated_on() {
        return updated_on;
    }
    public void setUpdated_on(LocalDateTime updated_on) {
        this.updated_on = updated_on;
    }
    public boolean getStatus() {return this.status;}
    public void setStatus(boolean status) {
        this.status = status;
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
