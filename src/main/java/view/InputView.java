package view;

import static exception.ExceptionHandler.retry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import domain.participant.attributes.Bet;
import domain.participant.attributes.Name;
import domain.participant.player.Player;

public class InputView {

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public List<Name> askPlayerNames() {
        return retry(() -> {
            System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
            String input = readLine();
            System.out.println();
            List<String> names = Arrays.stream(input.split(",")).toList();
            validateNotDuplicated(names);
            return names.stream()
                    .map(Name::new)
                    .toList();
        });
    }

    private void validateNotDuplicated(final List<String> names) {
        Set<String> distinctNames = new HashSet<>();
        names.forEach(name -> {
            if (distinctNames.contains(name)) {
                throw new IllegalArgumentException("중복된 이름을 입력할 수 없습니다: %s".formatted(name));
            }
            distinctNames.add(name);
        });
    }

    public Bet askPlayerBet(final Name name) {
        return retry(() -> {
            System.out.printf("%s의 의 배팅 금액은?%n", name.value());
            String input = readLine();
            System.out.println();
            return new Bet(parseInt(input));
        });
    }

    public int parseInt(final String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException exception) {
            throw new NumberFormatException("숫자를 입력해주세요: %s".formatted(value));
        }
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
