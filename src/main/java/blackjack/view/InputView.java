package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String REQUEST_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉽표 기준으로 분리)";

    public static List<String> requestName() {
        System.out.println(REQUEST_NAME_MESSAGE);
        return Arrays.stream(scanner.nextLine().trim().split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
