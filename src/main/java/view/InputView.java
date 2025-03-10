package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import model.Player;

public class InputView {
    private static final String HIT = "Hit";
    private static final String STAND = "Stand";
    private static Scanner SCANNER = new Scanner(System.in);

    public static List<String> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)");
        String[] names = SCANNER.nextLine().split(",", -1);
        validateDelimeter(names);
        return Arrays.stream(names).toList();
    }

    public static boolean readHit(Player player) {
        OutputView.printHitOrStand(player);
        String hit = SCANNER.nextLine();
        validateHit(hit);
        return hit.equals(HIT);
    }

    private static void validateDelimeter(String[] names) {
        String regex = "^[a-zA-Zㄱ-ㅎ가-힣]+$";

        for (String name : names) {
            validateInputName(name, regex);
        }
    }

    private static void validateInputName(String name, String regex) {
        if (!Pattern.matches(regex, name)) {
            throw new IllegalArgumentException("[ERROR] 잘못된 이름 형식입니다. 입력값 : " + name);
        }
    }

    private static void validateHit(String hit) {
        if (!(hit.equals(HIT) || hit.equals(STAND))) {
            throw new IllegalArgumentException("[ERROR] Hit 또는 Stand를 입력해주세요. 입력값 : " + hit);
        }
    }
}
