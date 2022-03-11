package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String QUESTION_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)";
    public static final String REGEX_NAME = ",";

    public List<String> askEntryNames() {
        System.out.println(QUESTION_NAME);
        String namesInput = new Scanner(System.in).nextLine();
        return splitNames(namesInput);
    }

    private List<String> splitNames(String namesInput) {
        return Arrays.asList((namesInput.split(REGEX_NAME)));
    }
}
