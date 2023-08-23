package edu.itstep.question.quest.config;

import edu.itstep.question.quest.entity.Quest;
import edu.itstep.question.quest.repository.QuestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
class QuestConfigTest {

    @Autowired
    private QuestRepository questRepository;

    @Test
    void testInitialDataPopulation() {
        // Verify that the initial data has been populated correctly
        List<Quest> quests = questRepository.findAll();
        assertNotNull(quests);
        assertEquals(10, quests.size()); // Assuming you've added 10 quests in your configuration
    }
}


