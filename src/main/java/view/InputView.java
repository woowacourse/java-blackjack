package view;

import domain.participant.Gamer;
import domain.participant.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final String NAME_SPLIT_DELIMITER = ",";
    private static final String NEXT_LINE = System.lineSeparator();

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");

        String nameValues = readLine().trim();
        validateNullOrBlank(nameValues);
        String[] splitName = nameValues.split(NAME_SPLIT_DELIMITER);

        return Arrays.stream(splitName).map(String::trim).toList();
    }

    public String readYesOrNo(Gamer player) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", player.getName());
        return readLine();
    }

    private String readLine() {
        return scanner.nextLine();
    }

    private void validateNullOrBlank(String nameValues) {
        if (nameValues == null || nameValues.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 이름이 입력되지 않았습니다.");
        }
    }

    public int readBetAmount(final Player player) {
        String betAmountFormat = String.format("%s의 베팅 금액은?", player.getName());
        System.out.println(NEXT_LINE + betAmountFormat);

        String input = readLine();
        return parseToInt(input);
    }

    private int parseToInt(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("베팅 금액은 숫자로만 입력해야 합니다.");
        }
    }
}
