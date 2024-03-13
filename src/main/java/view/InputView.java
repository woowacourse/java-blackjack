package view;

import domain.game.Money;
import domain.user.Name;
import domain.user.Player;
import domain.user.Players;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    private InputView() {
    }

    public static Players inputPlayers() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        List<String> names = List.of(SCANNER.nextLine().split(",", -1));
        List<Player> players = names.stream()
                .map((name) -> new Player(new Name(name)))
                .toList();
        return new Players(players);
    }

    public static Money inputMoney(String name) {
        System.out.println("\n" + name + "의 베팅 금액은?");
        return new Money(new BetAmount(inputInteger()).value());
    }

    public static Command inputAddCommand(String name) {
        System.out.println(name + "은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
        return Command.get(SCANNER.nextLine());
    }

    private static int inputInteger() {
        try {
            return SCANNER.nextInt();
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException("입력은 정수여야 합니다.");
        }
    }
}
