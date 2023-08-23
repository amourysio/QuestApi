package edu.itstep.question.quest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Table(name="Quest")
@Entity
@AllArgsConstructor
@ToString
@Getter
@Setter
@NoArgsConstructor
@Data
public class Quest {
   @Id
   @SequenceGenerator(
           name = "quest_sequence",
           sequenceName = "quest_sequence",
           allocationSize = 1
   )
   @GeneratedValue(
           strategy = GenerationType.SEQUENCE,
           generator = "quest_sequence"
   )
    private Long id;
    private String question;
    private List<String> incorrectAnswers;
    private String correctAnswer;
    private String difficulty;
}
