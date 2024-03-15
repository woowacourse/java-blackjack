package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import domain.participant.player.Player;

public class InputView implements BlackjackViewParser {

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public List<String> askPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = requireNotBlank(readLine());
        return Arrays.stream(input.split(",")).toList();
    }

    public int askPlayerBet(String name) {
        System.out.printf("%s의 의 배팅 금액은?", name);
        String input = requireNotBlank(readLine());
        return Integer.parseInt(input);
    }

    public GameCommand askMoreCard(final Player player) {
        System.out.printf("%n%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", parseName(player.name()));
        return GameCommand.from(readLine());
    }

    private String requireNotBlank(final String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("입력은 공백으로만 이루어질 수 없습니다.");
        }
        return input;
    }

    private String readLine() {
        try {
            return requireNotBlank(READER.readLine());
        } catch (final IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
