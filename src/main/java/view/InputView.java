package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final String NAME_DELIMITER = ",";
    private final Scanner scanner;

    public InputView(final Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        final String rawNames = scanner.nextLine();
        return asList(rawNames);
    }

    private List<String> asList(final String rawNames) {
        return Arrays.stream(rawNames.split(NAME_DELIMITER))
                     .map(String::trim)
                     .collect(Collectors.toUnmodifiableList());
    }

    public String readHitOrStand(final String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return scanner.nextLine()
                      .trim();
    }

    public Integer readBettingAmount(final String playerName) {
        System.out.println(playerName + "의 배팅 금액은?");
        return Integer.parseInt(scanner.nextLine());
    }
}
