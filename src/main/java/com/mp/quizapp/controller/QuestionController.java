package com.mp.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mp.quizapp.entities.Question;
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
    public List<Question> getAllQuestion() {
        // Returning getAllQuestions Method from questionService (here we are using same
        // method name as it's parent)
        return questionService.getAllQuestions();
    }

    // We want result based on catagory that's why we are giving it another route
    // with dynamic route {category}
    @GetMapping("category/{category}")
    // because we are taking dynamic value so we have to take it by @PathVariable
    public List<Question> getQuestionsByCategory(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }

    // It will accept question from the client side and @RequestBody send it to body
    //We we want to send data we use @PostMapping
    @PostMapping("add")
    public String addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

}
