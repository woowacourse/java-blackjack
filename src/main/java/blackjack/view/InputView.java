package blackjack.view;

import blackjack.domain.Player;

import java.util.Scanner;

public class InputView {

    private final Scanner sc;

    public InputView() {
        this.sc = new Scanner(System.in);
    }

    public String getName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return sc.nextLine();
    }

    public String getReceiveCard(Player player) {
        System.out.println(player.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return sc.nextLine();
    }
}
