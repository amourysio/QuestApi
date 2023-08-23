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
            Quest quest3 = new Quest(
                    3L,
                    "In finance, what does 'GDP' represent?",
                    List.of("Global Debt Proportion", "Gross Domestic Product", "General Development Plan"),
                    "Gross Domestic Product",
                    "Intern"
            );

            Quest quest4 = new Quest(
                    4L,
                    "What does the abbreviation 'HTML' stand for?",
                    List.of("Hypertext Transfer Markup Language", "Hyperlinking Textual Markdown Language", "High-Level Text Manipulation Logic"),
                    "Hypertext Transfer Markup Language",
                    "Intern"
            );

            Quest quest5 = new Quest(
                    5L,
                    "In the medical field, what does 'MRI' stand for?",
                    List.of("Medical Radiology Imaging", "Magnetic Resonance Imaging", "Microscopic Refraction Intensity"),
                    "Magnetic Resonance Imaging",
                    "Intern"
            );

            Quest quest6 = new Quest(
                    6L,
                    "What does 'AI' typically refer to in the context of technology?",
                    List.of("Algorithmic Integration", "Automated Inference", "Advanced Interface"),
                    "Artificial Intelligence",
                    "Intern"
            );

            Quest quest7 = new Quest(
                    7L,
                    "In the context of chemistry, what is the meaning of 'pH'?",
                    List.of("Particular Hydrogen", "Potential of Hydrogen", "Phosphorus Hydroxide"),
                    "Potential of Hydrogen",
                    "Intern"
            );

            Quest quest8 = new Quest(
                    8L,
                    "What does the abbreviation 'URL' stand for?",
                    List.of("Universal Resource Locator", "Uniform Reference Label", "User-Retrieved Link"),
                    "Universal Resource Locator",
                    "Intern"
            );
            Quest quest9 = new Quest(
                    9L,
                    "In the field of biology, what does the abbreviation 'DNA' stand for?",
                    List.of("Double-Nucleotide Assembly", "Deoxyribonucleic Acid", "Dominant Neural Architecture"),
                    "Deoxyribonucleic Acid",
                    "Intern"
            );

            Quest quest10 = new Quest(
                    10L,
                    "What is the full form of the abbreviation 'NASA'?",
                    List.of("National Aeronautics and Space Association", "Northern Astronomical and Space Agency", "New Atmospheric Science Advancements"),
                    "National Aeronautics and Space Association",
                    "Intern"
            );

            questRepository.saveAll(List.of(quest1,quest2,quest3,quest4,quest5,quest6,quest7,quest8,quest9,quest10));
        };
    }
}
