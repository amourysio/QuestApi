package edu.itstep.question.quest.exception;

public class QuestNotFoundException extends RuntimeException{
    public QuestNotFoundException(Long id) { super("Could not found quest with [" + id + "]"); }
}
