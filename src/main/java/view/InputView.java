package view;

import controller.Answer;
import domain.Money;
import domain.Nickname;
import domain.participant.Player;

import java.util.*;
import java.util.stream.Collectors;

public class InputView {

    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public Answer readYesOrNo(Player player) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n", player.getName());
        return Answer.of(scanner.nextLine());
    }

    public Map<Player, Money> readPlayers() {
        Map<Player, Money> result = new HashMap<>();
        Set<Nickname> nicknames = readPlayerNames();
        for (Nickname nickname : nicknames) {
            System.out.printf("%s의 배팅 금액은?\n", nickname.value());
            Money money = readMoney();
            result.put(Player.init(nickname), money);
        }
        return result;
    }

    private Set<Nickname> readPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
        String[] names = scanner.nextLine().split(",");
        return Arrays.stream(names)
                .map(String::trim)
                .map(Nickname::new)
                .collect(Collectors.toSet());
    }

    private Money readMoney() {
        try {
            final int input = Integer.parseInt(scanner.nextLine());
            return new Money(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자 형식이 올바르지 않습니다.");
        }
    }
}
