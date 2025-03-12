package blackjack.view;

import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);
    
    public String inputParticipant() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return scanner.nextLine();
    }

    public String inputCallOrStay(String name) {
        System.out.println(String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", name));
        return scanner.nextLine();
    }

    public String inputBetAmount(String name) {
        System.out.println(String.format("%s의 배팅 금액은?", name));
        return scanner.nextLine();
    }
}
