package com.example.blogapi.payload;

public class PostDto {
    private long id;
    private String title;
    private String content;
    private String author;
    private String imageUrl; // 🔹 NEW field

    // Constructors
    public PostDto() {
    }

    public PostDto(long id, String title, String content, String author, String imageUrl) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.imageUrl = imageUrl;
    }

    // Getters & Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageUrl() { // 🔹 Getter
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) { // 🔹 Setter
        this.imageUrl = imageUrl;
    }
}
