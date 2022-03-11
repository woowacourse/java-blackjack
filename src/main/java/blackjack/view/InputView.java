package blackjack.view;

import static java.util.stream.Collectors.toList;

import blackjack.dto.PlayerDto;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String SCAN_PLAYER_NAMES_INSTRUCTION = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";

    public List<String> scanPlayerNames() {
        System.out.println(SCAN_PLAYER_NAMES_INSTRUCTION);
        final List<String> playerNames = getPlayerNames();
        validate(playerNames);
        return playerNames;
    }

    private void validate(final List<String> playerNames) {
        checkEmpty(playerNames);
        checkCount(playerNames);
        checkDuplicate(playerNames);
    }

    private void checkEmpty(final List<String> playerNames) {
        if (playerNames.stream()
            .anyMatch(String::isEmpty)) {
            throw new IllegalArgumentException("이름에 빈값을 포함할 수 없습니다.");
        }
    }

    private void checkCount(final List<String> playerNames) {
        if (playerNames.size() < 1) {
            throw new IllegalArgumentException(1 + "개 이상 입력하세요.");
        }
    }

    public void checkDuplicate(List<String> values) {
        if (isDuplicate(values)) {
            throw new IllegalArgumentException("입력값이 중복될 수 없습니다.");
        }
    }

    private List<String> getPlayerNames() {
        final String inputNames = SCANNER.nextLine();
        Objects.requireNonNull(inputNames, "null을 허용하지 않습니다.");
        return Arrays.stream(inputNames
                .split(",", -1))
            .map(String::trim)
            .collect(toList());
    }

    private boolean isDuplicate(final List<String> names) {
        return names.size() != names.stream()
            .distinct()
            .count();
    }

    public String scanHitOrStay(final PlayerDto playerDto) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", playerDto.getName());
        return SCANNER.nextLine();
    }
}
