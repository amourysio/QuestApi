package edu.itstep.question.quest.service;

import edu.itstep.question.quest.repository.QuestRepository;
import edu.itstep.question.quest.entity.Quest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class QuestService {
    private final QuestRepository questRepository;
    public List<Quest> getAllQuest(){
        log.info("getAllQuest was called!");
        return questRepository.findAll();
    }

    public Optional<Quest> getQuestById(Long id){
        log.info("getQuestById was called!");
        return questRepository.findById(id);
    }

    public Quest addNewQuest(Quest quest){
        log.info("addNewQuest was called!");
        Optional<Quest> questOptional = questRepository
                .findById(quest.getId());
       Boolean exist = questRepository.existsById(quest.getId());
        if(exist){
            throw new IllegalStateException(
                    "This id:" + "[" + quest.getId() + "] is taken");
        }
        Quest savedQuest = questRepository.save(quest);

        return savedQuest;
    }

    public void deleteQuest(Long id) {
        log.info("deleteQuest was called!");
        boolean exists = questRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException(
                    "Quest with id [" + id + "] doesn't exists");
        }
        questRepository.deleteById(id);
    }

    @Transactional
    public Quest updateQuest(Long id,
                            String question,
                            String correctAnswer,
                            String difficulty) {
        log.info("updateQuest was called!");
        Quest quest = questRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Quest with id [" + id + "] doesn't exists"));

        if(question != null &&
        question.length() > 0 &&
        !Objects.equals(quest.getQuestion(), question)){
            Optional<Quest> questOptional = questRepository
                    .findQuestByQuestion(question);
            if(questOptional.isPresent()){
                throw new IllegalStateException("Question is taken");
            }
            quest.setQuestion(question);
        }
        if(correctAnswer != null &&
                correctAnswer.length() > 0 &&
                !Objects.equals(quest.getCorrectAnswer(), correctAnswer)){
            quest.setCorrectAnswer(correctAnswer);
        }
        if(difficulty != null &&
                difficulty.length() > 0 &&
                !Objects.equals(quest.getDifficulty(), difficulty)){
            quest.setDifficulty(difficulty);
        }
        return quest;
    }
}
