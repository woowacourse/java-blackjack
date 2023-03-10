package blackjack.view;

import static java.util.stream.Collectors.toList;

import blackjack.domain.participant.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public final class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();
        System.out.println();

        return Arrays.stream(input.split(","))
                .map(String::trim)
                .collect(toList());
    }

    public int readBettingMoney(final Player player) {
        System.out.println(player.getName() + "의 배팅 금액은?");
        String input = scanner.nextLine();
        System.out.println();

        return Integer.parseInt(input);
    }

    public String readHitOrStand(final Player player) {
        System.out.println(player.getName() + "은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input = scanner.nextLine();
        isValidInput(input);
        System.out.println();

        return input;
    }

    private void isValidInput(final String input) {
        if (!(input.equals("y") || input.equals("n"))) {
            throw new IllegalArgumentException("[ERROR] y 또는 n으로 입력해주세요.");
        }
    }
}
