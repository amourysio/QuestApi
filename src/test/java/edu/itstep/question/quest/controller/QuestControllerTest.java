package edu.itstep.question.quest.controller;

import edu.itstep.question.quest.entity.Quest;
import edu.itstep.question.quest.service.QuestService;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;



@WebMvcTest(QuestController.class)
class QuestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private QuestService underTest;
    @Autowired
    private ObjectMapper objectMapper;
    private Quest quest;
    private static final String API_V1_URL = "/api/v1";
    private static final String QUESTIONS_URL = "/questions";
    private static final String ID_URL = "/id/{id}";
    private static final String NOT_FOUND_URL = "/quest";

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
    void getQuests() throws Exception {
        List<Quest> questList = Arrays.asList(new Quest(
                1L,
                "TestQuestion",
                List.of("TestIncorrect1", "TestIncorrect2", "TestIncorrect3"),
                "TestCorrect",
                "TestDifficulty"
        ),
                new Quest(
                        2L,
                        "TestQuestion",
                        List.of("TestIncorrect1", "TestIncorrect2", "TestIncorrect3"),
                        "TestCorrect",
                        "TestDifficulty"
                ));

        when(underTest.getAllQuest()).thenReturn(questList);
        mockMvc.perform(get(API_V1_URL+QUESTIONS_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].question").value("TestQuestion"));
    }

    @Test
    void registerNewQuest() throws Exception {
        when(underTest.addNewQuest(quest)).thenReturn(quest);
        mockMvc.perform(post(API_V1_URL+QUESTIONS_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(quest)))
                .andExpect(status().isOk());
    }

    @Test
    void getQuestById() throws Exception {
        Quest quest = new Quest(1L, "Question", Arrays.asList("Answer1", "Answer2"), "CorrectAnswer", "Easy");
        String link = API_V1_URL + QUESTIONS_URL + "/1";
        when(underTest.getQuestById(1L)).thenReturn(Optional.of(quest));

        mockMvc.perform(get(link))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(quest.getId()))
                .andExpect(jsonPath("$.question").value(quest.getQuestion()))
                .andExpect(jsonPath("$.correctAnswer").value(quest.getCorrectAnswer()))
                .andExpect(jsonPath("$.difficulty").value(quest.getDifficulty()));

    }

    @Test
    public void deleteQuest() throws Exception {
        Long questId = 1L;


        mockMvc.perform(MockMvcRequestBuilders.delete(API_V1_URL+ QUESTIONS_URL+"/{id}", questId))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(underTest, times(1)).deleteQuest(questId);
        verifyNoMoreInteractions(underTest);
    }

    @Test
    void updateQuest() throws Exception {
        when(underTest.updateQuest(1L,"test","test","test")).thenReturn(quest);


        ResultActions response = mockMvc.perform(put(API_V1_URL+"/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(quest)));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
    @Test
    void NotFound() throws Exception {

        underTest.addNewQuest(quest);
        mockMvc.perform(post(NOT_FOUND_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(quest)))
                .andExpect(status().isNotFound());
    }

}