package parser;

import exception.BlackjackException;
import exception.ExceptionMessage;

public class AmountParser {
    private AmountParser() {
    }

    public static int parse(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new BlackjackException(ExceptionMessage.INPUT_ERROR);
        }
    }
}
