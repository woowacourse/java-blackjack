package view;

import blackjack.gamer.Nickname;
import blackjack.gamer.Player;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String NAME_SPLIT_DELIMITER = ",";

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");

        String nameValues = readLine().trim();

        validateNameValues(nameValues);

        String[] splitName = nameValues.split(NAME_SPLIT_DELIMITER);

        return Arrays.stream(splitName)
                .map(String::trim)
                .toList();
    }

    private void validateNameValues(String nameValues) {
        isEmptyNameValue(nameValues);
        endsWithDelimiter(nameValues);
    }

    private void endsWithDelimiter(String nameValues) {
        if (nameValues.endsWith(NAME_SPLIT_DELIMITER)) {
            throw new IllegalArgumentException("마지막에 구분자가 올 수 없습니다. 올바른 닉네임을 입력해주세요.");
        }
    }

    private void isEmptyNameValue(String nameValues) {
        if (nameValues.isEmpty()) {
            throw new IllegalArgumentException("최소 한 명 이상의 플레이어 이름을 입력해야 합니다.");
        }
    }

    public String readYesOrNo(Player player) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", player.getNickname());
        return readLine();
    }

    private String readLine() {
        return scanner.nextLine();
    }

    public BigDecimal readBettingAmount(Nickname nickname) {
        System.out.printf("\n%s의 배팅 금액은?\n", nickname.getName());
        return getBettingAmount();
    }

    private BigDecimal getBettingAmount() {
        try {
            return new BigDecimal(scanner.nextLine());
        } catch (Exception e) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액을 알맞게 입력해주세요");
        }

    }

}
