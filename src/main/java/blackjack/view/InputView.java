package blackjack.view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> requestPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");

        final String text = scanner.nextLine();
        return Stream.of(text.split(",", -1))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
