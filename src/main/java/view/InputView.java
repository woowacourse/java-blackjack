package blackjack.view;

import blackjack.view.message.BinaryOptionMessage;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String COMMA_DELIMITER = ",";

    private final Scanner sc = new Scanner(System.in);

    public List<String> readPlayers() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return splitPlayerNames(userInput());
    }

    public boolean readPlayerToHitUntilValid(String name) {
        while (true) {
            try {
                System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", name);
                return BinaryOptionMessage.isYes(userInput());
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage("잘못된 입력입니다. 다시 입력해주세요.");
            }
        }
    }

    private List<String> splitPlayerNames(String playerNames) {
        return Arrays.stream(playerNames.split(COMMA_DELIMITER)).toList();
    }

    private String userInput() {
        return sc.nextLine();
    }
}
