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

}
