package blackjack.view;

import java.util.Scanner;

public class InputView {


    public String readNames() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();

        validateBlank(input);
        return input;
    }

    public String readGetOneMore(String name) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", name);
        String input = scanner.nextLine();

        validateBlank(input);
        return input;
    }

    private void validateBlank(String input) {
        if(input.isBlank()) {
            throw new IllegalArgumentException("입력값이 없습니다.");
        }
    }
}
