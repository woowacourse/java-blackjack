package parser;

import exception.BlackjackException;
import exception.ExceptionMessage;

public class AnswerParser {
    private AnswerParser() {
    }

    public static boolean parse(String input) {
        if (input.equals("y")) {
            return true;
        }
        if (input.equals("n")) {
            return false;
        }

        throw new BlackjackException(ExceptionMessage.INPUT_ERROR);
    }
}
