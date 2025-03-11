package view;

import java.util.Scanner;

public class InputView {
    private Scanner scanner = new Scanner(System.in);

    public String inputUsers() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return scanner.next();
    }

    public String inputYesOrNo(String playerName) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", playerName);
        return scanner.next();
    }

    public void close() {
        scanner.close();
    }
}
