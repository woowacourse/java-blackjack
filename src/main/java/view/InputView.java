package view;

import java.util.List;
import java.util.Scanner;

public class InputView {
    Scanner sc = new Scanner(System.in);

    public String inputPurchaseAmount() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return sc.nextLine();
    }

    public void isBlank(List<String> names) {
        for (String name : names) {
            if (name.isBlank()) {
                throw new IllegalArgumentException();
            }
        }
    }
}
