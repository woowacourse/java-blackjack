package blackjack.view;

import blackjack.domain.Player;
import blackjack.exception.ErrorMessage;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String DELIMITER = ",";

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public List<String> readNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return split(scanner.nextLine());
    }

    public String readReceiveCard(Player player) {
        System.out.println(player.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return scanner.nextLine();
    }

    public int readBettingAmount(String name) {
        System.out.println("\n" + name + "의 배팅 금액은?");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_BETTING_AMOUNT_INPUT.getMessage());
        }
    }

    private List<String> split(String input) {
        return Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .toList();
    }
}
