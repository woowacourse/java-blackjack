package view;

import domain.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author 우가
 * @version 1.0.0
 * @Created by 우가 on 2023/03/01
 */
public class InputView {

    Scanner scanner = new Scanner(System.in);

    public List<String> getPlayer() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();

        return Arrays.stream(input.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public String addOrStop(final String playerName) {
        System.out.println(playerName + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input = scanner.nextLine();
        validateYOrN(input);
        return input;
    }

    private void validateYOrN(final String input) {
        if (!input.equals("y") && !input.equals("n")) {
            throw new IllegalArgumentException("y 혹은 n 만 입력가능합니다.");
        }
    }
}
