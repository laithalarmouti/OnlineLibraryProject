package com.OnlineLibrary.Entities.sql;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_activity")
public class UserActivity {
    public enum Action {
        REQUEST_WITH_MEETING, SIMPLE_REQUEST, TOOK, RETURNED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long activityId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String bookId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Action action;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    public Long getActivityId() { return activityId; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public String getBookId() { return bookId; }
    public void setBookId(String bookId) { this.bookId = bookId; }
    public Action getAction() { return action; }
    public void setAction(Action action) { this.action = action; }
    public Date getTimestamp() { return timestamp; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
}