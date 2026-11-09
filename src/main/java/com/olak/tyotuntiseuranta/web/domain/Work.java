package com.olak.tyotuntiseuranta.web.domain;

import java.time.LocalDate;
import java.time.LocalTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.GenerationType;

@Entity 
public class Work {
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String task;
    private Integer breaktime;
    private LocalDate date;
    private LocalTime beginning;

    @ManyToOne
    private User user;
    
    @Column(name = "end_time")
    private LocalTime end;

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public LocalTime getBeginning() {
        return beginning;
    }
    public void setBeginning(LocalTime beginning) {
        this.beginning = beginning;
    }
    public LocalTime getEnd() {
        return end;
    }
    public void setEnd(LocalTime end) {
        this.end = end;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTask() {
        return task;
    }
    public void setTask(String task) {
        this.task = task;
    }
    public Integer getBreaktime() {
        return breaktime;
    }
    public void setBreaktime(Integer breaktime) {
        this.breaktime = breaktime;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    @Override
    public String toString() {
        return "Work [id=" + id + ", task=" + task + ", breaktime=" + breaktime + ", date=" + date + ", beginning="
                + beginning + ", end=" + end + "]";
    }
 
    
}
