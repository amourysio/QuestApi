package edu.itstep.question.quest.config;

import edu.itstep.question.quest.repository.QuestRepository;
import edu.itstep.question.quest.entity.Quest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class QuestConfig {
    @Bean
    CommandLineRunner commandLineRunner(QuestRepository questRepository) {
        return args -> {
            Quest quest1 = new Quest(
                    1L,
                    "To what decimal number is equal matematical symbol 'PI'?",
                    List.of("6.17", "5.9", "2.1"),
                    "3.14",
                    "Intern"
            );
            Quest quest2 = new Quest(
                    2L,
                    "What is meaning this abbreviation in Software Sciences 'OOP' ?",
                    List.of("Option-oriented packet", "Opportunity-operated program", "Open-operation principle"),
                    "Object-oriented programming",
                    "Intern"
            );
            questRepository.saveAll(List.of(quest1,quest2));
        };
    }
}
