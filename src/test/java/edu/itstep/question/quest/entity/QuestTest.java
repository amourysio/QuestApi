package edu.itstep.question.quest.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Locale;


class QuestTest {

    Quest quest;
    Quest empty;

    @BeforeEach
    void setUp() {
         quest = new Quest(
                 1L,
                "To what decimal number is equal matematical symbol 'PI'?",
                List.of("6.17", "5.9", "2.1"),
                "3.14",
                "Intern"
        );
         empty = new Quest();
    }

    @Test
    void isEqualToId() {
        long id = 1L;
        assertThat(quest.getId()).isEqualTo(id);
    }

    @Test
    void IsNotEqualId() {
        long id = 2L;
        assertThat(quest.getId()).isNotEqualTo(id);
    }

    @Test
    void checkSetId() {
        long id = 2L;
        quest.setId(id);
        assertThat(quest.getId()).isEqualTo(id);
    }

    @Test
    void isEqualToQuestion() {
        String question = "To what decimal number is equal matematical symbol 'PI'?";

        assertThat(quest.getQuestion().toLowerCase(Locale.ROOT)).isEqualTo(question.toLowerCase(Locale.ROOT));
    }

    @Test
    void isNotEqualToQuestion() {
        String question = "Some Text";

        assertThat(quest.getQuestion().toLowerCase(Locale.ROOT)).isNotEqualTo(question.toLowerCase(Locale.ROOT));
    }
    @Test
    void checkSetQuestion() {
        String question = "Some Text";
        quest.setQuestion(question);
        assertThat(quest.getQuestion().toLowerCase(Locale.ROOT)).isEqualTo(question.toLowerCase(Locale.ROOT));
    }

    @Test
    void isEqualToIncorrectAnswers() {
        List<String> incorrect = List.of("6.17", "5.9", "2.1");

        assertThat(quest.getIncorrectAnswers()).isEqualTo(incorrect);
    }
    @Test
    void isNotEqualToIncorrectAnswers() {
        List<String> incorrect = List.of("1", "2", "3");

        assertThat(quest.getIncorrectAnswers()).isNotEqualTo(incorrect);
    }

    @Test
    void setIncorrectAnswers() {
        List<String> incorrect = List.of("1", "2", "3");
        quest.setIncorrectAnswers(incorrect);
        assertThat(quest.getIncorrectAnswers()).isEqualTo(incorrect);
    }

    @Test
    void isEqualToCorrectAnswer() {
        String correct = "3.14";
        assertThat(quest.getCorrectAnswer().toLowerCase(Locale.ROOT)).isEqualTo(correct.toLowerCase(Locale.ROOT));
    }
    @Test
    void isNotEqualToCorrectAnswer() {
        String correct = "2";
        assertThat(quest.getCorrectAnswer().toLowerCase(Locale.ROOT)).isNotEqualTo(correct.toLowerCase(Locale.ROOT));
    }

    @Test
    void setCorrectAnswer() {
        String correct = "2";
        quest.setCorrectAnswer(correct);
        assertThat(quest.getCorrectAnswer().toLowerCase(Locale.ROOT)).isEqualTo(correct.toLowerCase(Locale.ROOT));
    }

    @Test
    void isEqualToDifficulty() {
        String difficulty = "Intern";

        assertThat(quest.getDifficulty().toLowerCase(Locale.ROOT)).isEqualTo(difficulty.toLowerCase(Locale.ROOT));
    }

    @Test
    void isNotEqualToDifficulty() {
        String difficulty = "Junior";

        assertThat(quest.getDifficulty().toLowerCase(Locale.ROOT)).isNotEqualTo(difficulty.toLowerCase(Locale.ROOT));
    }

    @Test
    void setDifficulty() {
        String difficulty = "Junior";
        quest.setDifficulty(difficulty);
        assertThat(quest.getDifficulty().toLowerCase(Locale.ROOT)).isEqualTo(difficulty.toLowerCase(Locale.ROOT));
    }

    @Test
    void testIsEqualToString() {
        Quest quest1 = new Quest(
                1L,
                "To what decimal number is equal matematical symbol 'PI'?",
                List.of("6.17", "5.9", "2.1"),
                "3.14",
                "Intern"
        );
        assertThat(quest.toString()).isEqualTo(quest1.toString());
    }

    @Test
    void isEmpty(){
        assertThat(empty.getId()).isEqualTo(null);
        assertThat(empty.getQuestion()).isEqualTo(null);
        assertThat(empty.getIncorrectAnswers()).isEqualTo(null);
        assertThat(empty.getDifficulty()).isEqualTo(null);
    }

    @Test
    void isNotEmpty(){
        assertThat(quest.getId()).isNotEqualTo(null);
        assertThat(quest.getQuestion()).isNotEqualTo(null);
        assertThat(quest.getIncorrectAnswers()).isNotEqualTo(null);
        assertThat(quest.getDifficulty()).isNotEqualTo(null);
    }

}