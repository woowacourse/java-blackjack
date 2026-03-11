package view;

import java.util.*;
import java.util.stream.Collectors;

public class InputView {
    static Scanner sc = new Scanner(System.in);

    // 플레이어 이름 입력받기
    public static List<String> askName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        List<String> playerNames = splitComma(sc.nextLine());
        System.out.println();
        return playerNames;
    }

    // 히트 여부 입력받기
    public static boolean askHit(String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String answer = sc.nextLine();
        if(answer.equals("y")) {
            return true;
        }

        if(answer.equals("n")) {
            return false;
        }

        throw new IllegalArgumentException("[ERROR] y와 n만 입력가능합니다.");
    }

    private static List<String> splitComma(String input) {
        List<String> names = Arrays.stream(input.split(","))
                .map(String::trim)
                .collect(Collectors.toList());

        validateDuplicate(names);

        return names;
    }

    private static void validateDuplicate(List<String> names) {
        Set<String> set = new HashSet<>(names);

        if (set.size() != names.size()) {
            throw new IllegalArgumentException("[ERROR] 중복된 이름이 존재합니다.");
        }
    }
}
