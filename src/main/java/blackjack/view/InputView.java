package blackjack.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputView {
    private static final String YES = "y";
    private static final String NO = "n";

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public String readPlayerNames() {
        String playerNames = readStrippedLine();
        System.out.println();
        return playerNames;
    }

    public boolean isHitAnswer() {
        return isYes(readStrippedLine());
    }

    private boolean isYes(final String rawAnswer) {
        validateAnswer(rawAnswer);
        return YES.equals(rawAnswer);
    }

    private void validateAnswer(final String rawAnswer) {
        if (!YES.equals(rawAnswer) && !NO.equals(rawAnswer)) {
            throw new IllegalArgumentException(YES + " 또는 " + NO + "만 입력 가능합니다.");
        }
    }

    private String readStrippedLine() {
        try {
            return reader.readLine().strip();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
