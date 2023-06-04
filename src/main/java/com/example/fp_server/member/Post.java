package com.example.fp_server.member;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * A class that mirrors the database table Post
 * @author Marcus Larsson
 */
@Entity
@Table
public class Post {
    @Id
    @SequenceGenerator(
            name = "post_sequence",
            sequenceName = "post_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "post_sequence"
    )
    private Long id;
    private String text;
    private String email;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime date;

    /**
     * Instantiates a new Post.
     */
    public Post() {
    }

    /**
     * Instantiates a new Post.
     *
     * @param text  the text
     * @param email the email
     */
    public Post(String text, String email) {
        this.text = text;
        this.email = email;
        this.date = LocalDateTime.now();
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets text.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets text.
     *
     * @param content the content
     */
    public void setText(String content) {
        this.text = content;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", email='" + email + '\'' +
                ", date=" + date +
                '}';
    }
}
