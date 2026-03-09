package blackjack.view.parser;

import blackjack.model.game.BlackjackResult;

public class ResultParser {

    public static String parseToLabel(BlackjackResult result) {
        return switch (result) {
            case WIN -> "승";
            case LOSE -> "패";
            case PUSH -> "푸시";
        };
    }
}
