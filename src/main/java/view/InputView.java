package view;

import domain.game.GamblingMoney;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> getPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String input = scanner.nextLine();

        return Arrays.stream(input.split(","))
            .map(String::trim)
            .toList();
    }

    public GamblingMoney getBettingMoney(String name) {
        System.out.printf("%s의 배팅 금액은?%n", name);

        String input = scanner.nextLine();
        int amount = Integer.parseInt(input);
        return GamblingMoney.bet(amount);
    }

    public boolean getYesOrNo(String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        String input = scanner.nextLine();
        if("y".equalsIgnoreCase(input)) {
            return true;
        }
        if("n".equalsIgnoreCase(input)) {
            return false;
        }
        throw new IllegalArgumentException("y또는 n만 입력가능합니다.");
    }
}
