package view;

import blackjack.gamer.Nickname;
import blackjack.gamer.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String NAME_SPLIT_DELIMITER = ",";

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");

        String nameValues = readLine().trim();
        String[] splitName = nameValues.split(NAME_SPLIT_DELIMITER);

        return Arrays.stream(splitName)
                .map(String::trim)
                .toList();
    }

    public String readYesOrNo(Player player) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", player.getNickname());
        return readLine();
    }

    private String readLine() {
        return scanner.nextLine();
    }

    public Integer readBettingAmount(Nickname nickname) {
        System.out.printf("\n%s의 배팅 금액은?\n", nickname.getName());
        return getNextInt();
    }

    private int getNextInt() {
        try {
            String value = readLine();
            return Integer.parseInt(value);
        } catch (Exception e) {
            throw new IllegalArgumentException("[ERROR] 금액을 알맞게 입력해주세요.");
        }
    }
}
