package blackjack.view;

import static java.util.stream.Collectors.toList;

import blackjack.domain.player.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public final class InputView {
    private static final String DELIMITER = ",";
    private static final int LIMIT = -1;
    private static InputView INSTANCE = new InputView();

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public List<String> readPlayers() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        final String names = scanner.nextLine();

        return Arrays.stream(names.split(DELIMITER, LIMIT))
                .map(String::trim)
                .collect(toList());
    }

    public int readBet(final Player player) {
        System.out.println(player.getNameValue() + "의 배팅 금액은?");

        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("베팅 금액은 숫자만 입력해야 합니다.");
        }
    }

    public String readCommand(final Player player) {
        System.out.println(player.getNameValue() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");

        return scanner.nextLine();
    }
}
