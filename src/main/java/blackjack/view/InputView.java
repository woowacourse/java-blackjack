package blackjack.view;

import blackjack.model.Answer;
import blackjack.view.parser.AnswerParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputView {

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public String readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String playerNames = readLine();
        System.out.println();
        
        return playerNames;
    }

    public Answer askHit(final String playerName) {
        System.out.println(playerName + "은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");

        return AnswerParser.parseToModel(readLine());
    }

    private String readLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
