package view;

import domain.participant.Player;
import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public String getPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요. (쉼표 기준으로 분리)");
        return scanner.nextLine();
    }

    public String getPlayerBetAmount(Player player) {
        System.out.printf("%s의 배팅 금액은?\n", player.getName());
        return scanner.nextLine();
    }

    public String askReceive(String name) {
        System.out.println(name + "는 한 장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)");
        return scanner.nextLine();
    }
}
