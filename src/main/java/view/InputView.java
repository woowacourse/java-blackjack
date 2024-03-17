package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import domain.participant.attributes.Bet;
import domain.participant.attributes.Name;
import domain.participant.attributes.Names;
import domain.participant.player.Player;

public class InputView {

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public Names askPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = readLine();
        System.out.println();
        return new Names(Arrays.stream(input.split(",")).toList());
    }

    public Bet askPlayerBet(Name name) {
        System.out.printf("%s의 의 배팅 금액은?%n", name.value());
        String input = readLine();
        System.out.println();
        return new Bet(Integer.parseInt(input));
    }

    public GameCommand askMoreCard(final Player player) {
        Name name = player.name();
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", name.value());
        return GameCommand.from(readLine());
    }

    private String readLine() {
        try {
            return requireNotBlank(READER.readLine());
        } catch (final IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private String requireNotBlank(final String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("입력은 공백으로만 이루어질 수 없습니다.");
        }
        return input;
    }
}
