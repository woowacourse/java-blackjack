package view;

import domain.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");

        String nameValues = readLine().trim();

        validateNullOrBlank(nameValues);

        String[] splitName = nameValues.split(",");

        return Arrays.stream(splitName)
                .map(String::trim)
                .toList();
    }

    private void validateNullOrBlank(String nameValues) {
        if (nameValues == null || nameValues.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 이름이 입력되지 않았습니다.");
        }
    }

    private String readLine() {
        return scanner.nextLine();
    }

    public String readYesOrNo(Player player) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", player.getName());
        return readLine();
    }
}
