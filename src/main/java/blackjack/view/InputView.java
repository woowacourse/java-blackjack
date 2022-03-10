package blackjack.view;

import java.util.Scanner;
import java.util.stream.Stream;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {

    }

    public static String inputNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String text = SCANNER.nextLine();
        if (!text.matches("^\\w+(,\\w+)*")) {
            throw new IllegalArgumentException("잘못된 이름 형식입니다.");
        }
        return text;
    }

    public static String chooseOptions(String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", name);
        String value = SCANNER.nextLine();

        if (value.matches("(y|n)")) {
            return value;
        }

        return chooseOptions(name);
    }


}
