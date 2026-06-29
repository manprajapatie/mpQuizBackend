package com.mp.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mp.quizapp.model.Question;
import com.mp.quizapp.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    // With this we will get all quation with route of /question/allQuestions >>
    // question is our main route and allQuestions we are defining below and getting
    // it from service
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestion() {
        // Returning getAllQuestions Method from questionService (here we are using same
        // method name as it's parent)
        return questionService.getAllQuestions();
    }

    // We want result based on catagory that's why we are giving it another route
    // with dynamic route {category}
    @GetMapping("category/{category}")
    // because we are taking dynamic value so we have to take it by @PathVariable
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }

    // It will accept question from the client side and @RequestBody send it to body
    // We we want to send data we use @PostMapping
    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    // We we want to delete Question, we use @PostMapping
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id) {
        return questionService.deleteQuestion(id);
    }

    // We we want to update, we use @PutMapping
    @PutMapping("update/{id}")
    public ResponseEntity<String> updateQuestion(@PathVariable Integer id, @RequestBody Question updatedQuestion) {
        return questionService.updateQuestion(id, updatedQuestion);
    }

}
