package com.mp.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

import com.mp.quizapp.dto.QuizDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mp.quizapp.dao.QuestionDao;
import com.mp.quizapp.dao.QuizDao;
import com.mp.quizapp.model.Question;
import com.mp.quizapp.model.QuestionWrapper;
import com.mp.quizapp.model.Quiz;
import com.mp.quizapp.model.Response;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<Integer> createQuiz(String category, int numQ, String title) {

        // Getting Random Question from database
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>(quiz.getId(), HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        // Optional make this data option, it may be show or not
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser_ = new ArrayList<>();
        for (Question q : questionsFromDB) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(),
                    q.getOption3(), q.getOption4());
            questionsForUser_.add(qw);
        }

        return new ResponseEntity<>(questionsForUser_, HttpStatus.OK);
    }

    // Calculating Result with the help of comparition giving ans and right answer
    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDao.findById(id).get();
        List<Question> questions = quiz.getQuestions();

        int right = 0;
        int i = 0;
        for (Response response : responses) {
            if (response.getResponse().equals(questions.get(i).getRightAnswer()))
                right++;
            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }

    public ResponseEntity<List<QuizDTO>> getAllQuiz() {

        List<QuizDTO> quizzes = quizDao.findAll()
                .stream()
                .map(quiz -> new QuizDTO(
                        quiz.getId(),
                        quiz.getTitle(),
                        quiz.getQuestions().size()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(quizzes);
    }
}
