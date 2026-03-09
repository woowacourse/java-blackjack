package parser;

import expcetion.BlackjackException;
import expcetion.ExceptionMessage;

public class AnswerParser {

    private static final String YES = "y";
    private static final String NO = "n";

    private AnswerParser() {
    }

    public static boolean parse(String input) {
        if (YES.equals(input)) {
            return true;
        }
        if (NO.equals(input)) {
            return false;
        }
        throw new BlackjackException(ExceptionMessage.INPUT_ERROR);
    }
}
