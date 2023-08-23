package edu.itstep.question.quest.controller;

import edu.itstep.question.quest.exception.QuestNotFoundException;
import edu.itstep.question.quest.service.QuestService;
import edu.itstep.question.quest.entity.Quest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RequestMapping(path = "api/v1")
@RestController
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class QuestController {
    private final QuestService questService;

    @GetMapping(path = "/questions")
    public List<Quest> getQuests(){ return questService.getAllQuest(); }

    @PostMapping(path = "/questions")
    public void registerNewQuest(@RequestBody Quest quest) { questService.addNewQuest(quest); }

    @GetMapping("/questions/{id}")
    public Optional<Quest> getQuestById(@PathVariable Long id){
        return Optional.ofNullable(questService.getQuestById(id).orElseThrow(() -> new QuestNotFoundException(id)));
    }
    @DeleteMapping(path = "/questions/{id}")
    public void deleteQuest(@PathVariable("id") Long id){
        questService.deleteQuest(id);
    }

    @PutMapping(path = "{id}")
    public void updateQuest(
            @PathVariable("id") Long id,
            @RequestParam(required = false) String question,
            @RequestParam(required = false) List<String> ignoredIncorrectAnswers,
            @RequestParam(required = false) String correctAnswer,
            @RequestParam(required = false) String difficulty){
        questService.updateQuest(id, question,correctAnswer,difficulty);
    }
}
