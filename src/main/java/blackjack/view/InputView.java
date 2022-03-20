package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final String DELIMITER = ",";
    private static final Scanner SCANNER = new Scanner(System.in);

    public List<String> requestPlayerNamesToPlayGame() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return Arrays.stream(readLine().split(DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public String requestHitOrStay(String playerName) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", playerName);
        return readLine();
    }

    public int requestPlayerBettingMoney(String playerName) {
        try {
            System.out.printf("%n%s의 배팅 금액은?%n", playerName);
            return Integer.parseInt(readLine());
        } catch (IllegalArgumentException e) {
            System.out.println("배팅 금액은 0보다 큰 숫자로만 입력해주세요.");
            return requestPlayerBettingMoney(playerName);
        }
    }

    private String readLine() {
        return SCANNER.nextLine();
    }
}
