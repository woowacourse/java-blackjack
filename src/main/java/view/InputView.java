package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import domain.participant.Name;
import domain.participant.player.Player;
import domain.participant.player.Players;

public class InputView implements AutoCloseable, BlackjackViewParser {

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public Players askPlayers() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = requireNotBlank(readLine());
        List<Player> players = Arrays.stream(input.split(","))
                .map(name -> Player.from(new Name(name.trim())))
                .toList();
        return Players.from(players);
    }

    public GameCommand askMoreCard(final Player player) {
        System.out.printf("%n%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", parseName(player.name()));
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

    @Override
    public void close() throws Exception {
        READER.close();
    }
}
