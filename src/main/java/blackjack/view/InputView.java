package blackjack.view;

import blackjack.domain.gamer.Player;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public List<String> readNicknames() {
        System.out.printf("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)%n");

        String nicknames = scanner.nextLine();
        System.out.println();

        return Stream.of(nicknames.split(","))
                .map(String::trim)
                .toList();
    }

    public boolean readShouldHit(Player player) {
        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)%n", player.getNickname().getValue());

        String shouldHit = scanner.nextLine().trim().toUpperCase();

        if (shouldHit.equals("Y") || shouldHit.equals("N")) {
            return shouldHit.equals("Y");
        }

        throw new IllegalArgumentException("잘못된 입력입니다. 'Y' 또는 'N'만 입력해야 합니다.");
    }
}
