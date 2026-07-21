package com.mp.quizapp.dto;

public class QuizDTO {
    private Integer id;
    private String title;
    private Integer totalQuestions;

    public QuizDTO() {
    }

    public QuizDTO(Integer id, String title, Integer totalQuestions) {
        this.id = id;
        this.title = title;
        this.totalQuestions = totalQuestions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(Integer totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

}
