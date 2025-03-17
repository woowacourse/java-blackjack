package blackjack.view;

import blackjack.model.player.PlayerName;

import java.util.Scanner;

public class InputView {
    
    private static Scanner scanner = new Scanner(System.in);
    
    public String inputParticipantName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return scanner.nextLine();
    }

    public int inputParticipantMoney(final PlayerName name) {
        System.out.println(String.format("%n%s의 베팅 금액은?", name.getName()));
        return StringToIntegerParser.parse(scanner.nextLine());
    }

    public String inputCallOrStay(final String name) {
        System.out.println(String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", name));
        return scanner.nextLine();
    }
}
