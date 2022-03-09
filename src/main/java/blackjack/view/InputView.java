package blackjack.view;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final static Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static List<String> getNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = SCANNER.nextLine();

        String[] splitNames = input.split(",", -1);

        return Arrays.stream(splitNames)
                .collect(toList());
    }
}
