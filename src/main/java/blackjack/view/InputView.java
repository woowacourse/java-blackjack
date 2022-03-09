package blackjack.view;

import java.util.Scanner;

public class InputView {

    private static final String regex = "^[a-zA-Z]+$";
    private static final Scanner scanner = new Scanner(System.in);

    public static String[] inputName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉽표 기준으로 분리)");
        String[] names = scanner.nextLine().split(",");
        validateInputName(names);
        return names;
    }

    private static void validateInputName(String[] names) {
        for (String name : names) {
            if (!name.matches(regex)) {
                throw new IllegalArgumentException("");
            }
        }
    }


    public static boolean requestHitOrNot(String name) {
        System.out.println(name + "은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return scanner.nextLine().equals("y");
    }
}
