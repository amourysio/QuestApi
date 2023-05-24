package edu.itstep.question.quest.repository;

import edu.itstep.question.quest.entity.Quest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestRepository
        extends JpaRepository<Quest, Long> {
    Optional<Quest> findById(Long id);
    Optional<Quest> findByDifficulty(String difficulty);
    Optional<Quest> findQuestByQuestion(String question);
}
