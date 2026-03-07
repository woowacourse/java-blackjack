package view;

import util.InputParser;

import java.util.List;
import java.util.Scanner;

public class InputView {
    static Scanner sc = new Scanner(System.in); // TODO: close 어떻게...?

    // 플레이어 이름 입력받기
    public static List<String> askName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return InputParser.splitComma(sc.nextLine());
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
}
