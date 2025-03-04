package blackjack.view;

import java.util.Scanner;

public class InputVIew {
    private static final String DELIMITER = ",";

    private final Scanner scanner = new Scanner(System.in);

    public String[] readPlayerName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String playerNames = scanner.nextLine();
        return playerNames.split(DELIMITER);
    }

    public String readOneMoreCard(String playerName) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", playerName);
        return scanner.nextLine();
    }
}
