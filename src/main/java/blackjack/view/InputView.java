package blackjack.view;

import blackjack.YesOrNo;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String DELIMITER = ",";
    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> getNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();
        return Arrays.asList(input.split(DELIMITER));
    }

    public static YesOrNo getYorN(String name) {
        String msg = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)";
        System.out.println(String.format(msg, name));
        return YesOrNo.valueOf(scanner.nextLine());
    }
}
