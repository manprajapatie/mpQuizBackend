package com.mp.quizapp.service;

import java.util.ArrayList;
import java.util.List;
// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mp.quizapp.dao.QuestionDao;
import com.mp.quizapp.entities.Question;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;

    // we wrape it with ResponseEntity for check status code if it is okay or not
    // and also handle the error
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> addQuestion(Question question) {
        // it will save the question from the client
        questionDao.save(question);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    // It will delete the object
    public ResponseEntity<String> deleteQuestion(Integer id) {
        if (!questionDao.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Question not found.");
        }

        questionDao.deleteById(id);
        return ResponseEntity.ok("Question deleted successfully.");
    }

    // It will update the object
    public ResponseEntity<String> updateQuestion(Integer id, Question updatedQuestion) {

        Question question = questionDao.findById(id).orElse(null);

        try {
            question.setQuestionTitle(updatedQuestion.getQuestionTitle());
            question.setOption1(updatedQuestion.getOption1());
            question.setOption2(updatedQuestion.getOption2());
            question.setOption3(updatedQuestion.getOption3());
            question.setOption4(updatedQuestion.getOption4());
            question.setRightAnswer(updatedQuestion.getRightAnswer());
            question.setDifficultyLevel(updatedQuestion.getDifficultyLevel());
            question.setCategory(updatedQuestion.getCategory());

            questionDao.save(question);

            return new ResponseEntity<>("Question updated successfully", HttpStatus.ACCEPTED);

        } catch (Exception e) {
            return new ResponseEntity<>("Failed to Update", HttpStatus.BAD_REQUEST);
        }

    }

}
