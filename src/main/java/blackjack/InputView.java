package blackjack;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    Scanner sc = new Scanner(System.in);

    public List<String> readNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = sc.nextLine();
        String[] inputs = input.split(",");
        return Arrays.stream(inputs).toList();
    }
}
