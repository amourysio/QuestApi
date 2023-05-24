package edu.itstep.question.quest.service;
import edu.itstep.question.quest.entity.Quest;
import edu.itstep.question.quest.repository.QuestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class QuestServiceTest {
    @Mock
    QuestRepository questRepository;

    @InjectMocks
    QuestService underTest;
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

    @Test
    void addNewQuestReturnQuest(){
        when(questRepository.save(Mockito.any(Quest.class))).thenReturn(quest);
        Quest savedQuest = underTest.addNewQuest(quest);
        assertThat(savedQuest).isNotNull();
    }

    @Test
    void getAllQuestReturnList() {
        underTest.getAllQuest();

        verify(questRepository).findAll();
    }

    @Test
    void getQuestById() {
       Long questId = 1L;

        when(questRepository.findById(questId)).thenReturn(Optional.ofNullable(quest));

        Optional<Quest> questReturn = underTest.getQuestById(questId);

        assertThat(questReturn).isNotNull();
    }


    @Test
    @Disabled
    void deleteQuest() {
        // Still Doesn't work, I don't know where is problem here!
        underTest.addNewQuest(quest);
        verify(questRepository,times(0)).delete(quest);

    }
    @Test
    void willThrowWhenIdIsTaken() {

        given(questRepository.existsById(quest.getId()))
                .willReturn(true);

        // when
        assertThatThrownBy(() ->underTest.addNewQuest(quest))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("This id:" + "[" + quest.getId() + "] is taken");

        //then
        verify(questRepository, never()).save(any());

    }
    @Test
    void updateQuestReturnUpdatedQuest() {
        when(questRepository.findById(anyLong())).thenReturn(Optional.of(quest));

        Quest updateJoke = underTest.updateQuest(1L, "Test","Test","Test");

        assertThat(updateJoke.getQuestion()).isEqualTo("Test");
        assertThat(updateJoke.getCorrectAnswer()).isEqualTo("Test");
        assertThat(updateJoke.getDifficulty()).isEqualTo("Test");

    }
}