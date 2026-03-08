package blackjack.util;

import blackjack.controller.DrawCommand;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private Parser(){
    }

    public static List<String> splitDelimiter(String playerNames) {
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

    public static void notEmpty(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("입력이 비어있습니다.");
        }
    }
}
