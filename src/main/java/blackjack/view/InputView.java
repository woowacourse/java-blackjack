package blackjack.view;

import blackjack.exception.ErrorMessage;
import blackjack.model.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner sc;

    public InputView() {
        this.sc = new Scanner(System.in);
    }

    public List<String> getName() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = sc.nextLine();
        return Arrays.stream(input.split(","))
                .map(String::trim)
                .toList();
    }

    public boolean getReceiveCard(Player player) {
        System.out.println(player.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input = sc.nextLine();
        if (input.equals("n")) {
            return false;
        }
        if (!input.equals("y")) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getMessage());
        }
        return true;
    }

    public Integer getBettingAmount(String name) {
        System.out.println();
        System.out.println(name + "의 배팅 금액은?");
        return Integer.parseInt(sc.nextLine());
    }
}
