package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> inputUserName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String names = scanner.nextLine();
        return Arrays.stream(names.split(",", -1))
                .map(String::strip)
                .toList();
    }

    public static boolean inputWantOneMoreCard(String name) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니다?(예는 y, 아니오는 n)%n", name);
        YesOrNo input = YesOrNo.from(scanner.nextLine());
        return input == YesOrNo.YES;
    }
}