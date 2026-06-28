package com.mp.quizapp.service;

import java.util.List;
// import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mp.quizapp.dao.QuestionDao;
import com.mp.quizapp.entities.Question;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;

    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionDao.findByCategory(category);
    }

    public String addQuestion(Question question) {
        // it will save the question from the client
        questionDao.save(question);
        return "success";
    }

    // It will delete the object
    public String deleteQuestion(Integer id) {
        if (questionDao.existsById(id)) {
            questionDao.deleteById(id);
            return "Question deleted successfully.";
        }
        return "Question not found.";
    }

    // It will update the object
    public String updateQuestion(Integer id, Question updatedQuestion) {

        Question question = questionDao.findById(id).orElse(null);
        if (question == null) {
            return "Question not found";
        }
        question.setQuestionTitle(updatedQuestion.getQuestionTitle());
        question.setOption1(updatedQuestion.getOption1());
        question.setOption2(updatedQuestion.getOption2());
        question.setOption3(updatedQuestion.getOption3());
        question.setOption4(updatedQuestion.getOption4());
        question.setRightAnswer(updatedQuestion.getRightAnswer());
        question.setDifficultyLevel(updatedQuestion.getDifficultyLevel());
        question.setCategory(updatedQuestion.getCategory());

        questionDao.save(question);

        return "Question updated successfully";
    }

}
