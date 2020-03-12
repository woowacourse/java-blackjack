package blackjack.view;

import blackjack.player.Player;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public Names inputPlayNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        return new Names(this.scanner.nextLine());
    }

    public DrawRequest inputDrawRequest(Player player) {
        System.out.println(String.format("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", player.getName()));
        return new DrawRequest(scanner.nextLine());
    }
}
