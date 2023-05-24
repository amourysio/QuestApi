package edu.itstep.question.quest.repository;

import edu.itstep.question.quest.entity.Quest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@DataJpaTest
class QuestRepositoryTest {

    @Autowired
    private QuestRepository underTest;
    Quest quest;

    @BeforeEach
    void setUp() {
        quest = new Quest(
                1L,
                "TestQuestion",
                List.of("TestIncorrect1", "TestIncorrect2", "TestIncorrect3"),
                "TestCorrect",
                "TestDifficulty"
        );
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }


    @Test
    public void checkRepositorySaveThenReturnQuest() {
        Quest quest1 = underTest.save(quest);

        assertThat(quest1).isNotNull();
        assertThat(quest1.getId()).isGreaterThan(0);
    }

    @Test
    void findByIdExist() {
        Long id = 1L;
        underTest.save(quest);

        boolean expected = underTest.findById(id).isPresent();
        assertThat(expected).isTrue();
    }

    @Test
    void findByIdDoesNotExistExist() {
        Long id = 3L;
        underTest.save(quest);

        boolean expected = underTest.findById(id).isPresent();
        assertThat(expected).isFalse();
    }

    @Test
    void findByDifficultyExist() {
        String difficulty = "TestDifficulty";
        underTest.save(quest);

        boolean expected = underTest.findByDifficulty(difficulty).isPresent();
        assertThat(expected).isTrue();
    }
    @Test
    void findByDifficultyDoesNotExist() {
        String difficulty = "TestDoesNotExist";
        underTest.save(quest);

        boolean expected = underTest.findByDifficulty(difficulty).isPresent();
        assertThat(expected).isFalse();
    }

    @Test
    void findByQuestionExist() {
        String question = "TestQuestion";
        underTest.save(quest);

        boolean expected = underTest.findQuestByQuestion(question).isPresent();
        assertThat(expected).isTrue();
    }

    @Test
    void findByQuestionDoesNotExist() {
        String question = "TestDoesNotExist";
        underTest.save(quest);

        boolean expected = underTest.findByDifficulty(question).isPresent();
        assertThat(expected).isFalse();
    }
}