package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private static final Scanner sc = new Scanner(System.in);

    public static List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return parse(sc.nextLine());
    }

    public static String readIntent(String nickname) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n",nickname);
        return sc.nextLine();
    }

    private static List<String> parse(String input) {
        String[] split = input.split(",", -1);
        return Arrays.asList(split);
    }

}
