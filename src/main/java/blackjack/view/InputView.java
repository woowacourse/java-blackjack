package blackjack.view;

import blackjack.model.Answer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputView {
    private static final String YES = "y";
    private static final String NO = "n";

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public String readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String playerNames = readStrippedLine();
        System.out.println();
        return playerNames;
    }

    public Answer askHit(final String playerName) {
        System.out.println(playerName + "은 한장의 카드를 더 받겠습니까?(예는 " + YES + ", 아니오는 " + NO + ")");
        return converToAnswer(readStrippedLine());
    }

    private Answer converToAnswer(final String rawAnswer) {
        if (YES.equals(rawAnswer)) {
            return Answer.YES;
        }
        if (NO.equals(rawAnswer)) {
            return Answer.NO;
        }
        throw new IllegalArgumentException("y 또는 n만 입력 가능합니다.");
    }

    private String readStrippedLine() {
        try {
            return reader.readLine().strip();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
