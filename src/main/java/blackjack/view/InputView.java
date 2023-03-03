package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    public static final String NAME_SPLIT = ",";

    private InputView() {}

    public static List<String> readNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String namesValue = scanner.nextLine();
        return Arrays.stream(namesValue.split(NAME_SPLIT))
                .collect(Collectors.toList());
    }
}
