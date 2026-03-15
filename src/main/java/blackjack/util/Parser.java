package blackjack.util;

import blackjack.controller.DrawCommand;

import java.util.ArrayList;
import java.util.List;

import static blackjack.util.StringUtils.notEmpty;

public class Parser {
    private Parser(){
    }

    public static List<String> splitDelimiter(String playerNames) {
        notEmpty(playerNames);
        String[] values = playerNames.split(",");

        List<String> splitResult = new ArrayList<>();
        for (String part : values) {
            splitResult.add(part.trim());
        }

        return splitResult;
    }

    public static DrawCommand parseDrawInput(String input){
        notEmpty(input);
        return DrawCommand.of(input);
    }
}
