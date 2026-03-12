package blackjack.view;

import blackjack.model.game.HitAnswer;
import blackjack.model.participant.Bet;
import blackjack.model.participant.Name;
import blackjack.model.participant.Names;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class InputView {

    private static final String DELIMITER = ",";
    private static final int INCLUDE_EMPTY_ELEMENT = -1;

    private static final Map<String, HitAnswer> ANSWERS = Map.of(
            "y", HitAnswer.HIT,
            "n", HitAnswer.STAY
    );

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public Names readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String rawNames = readLine();
        System.out.println();

        List<Name> names = Arrays.stream(rawNames.split(DELIMITER, INCLUDE_EMPTY_ELEMENT))
                .map(Name::new)
                .toList();

        return new Names(names);
    }

    public Bet readBet(final Name playerName) {
        System.out.println(playerName.get() + "의 배팅 금액은?");
        String betAmount = readLine();
        System.out.println();

        return Bet.from(betAmount);
    }

    public HitAnswer askHit(final String playerName) {
        System.out.println(playerName + "은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");

        return ANSWERS.get(readLine());
    }

    private String readLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
