package blackjack.view;

import blackjack.participant.Player;

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

    public int readBetting(String nickname) {
        System.out.printf("%s의 배팅 금액은? %n", nickname);

        String betting = scanner.nextLine();
        System.out.println();

        return Integer.parseInt(betting.trim());
    }

    public boolean readShouldHit(Player player) {
        String wantsHit = "Y";
        String doesNotWantHit = "N";

        System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 %s, 아니오는 %s)%n",
                player.getNickname().getValue(), wantsHit, doesNotWantHit);

        String shouldHit = scanner.nextLine().trim().toUpperCase();

        if (shouldHit.equals(wantsHit) || shouldHit.equals(doesNotWantHit)) {
            return shouldHit.equals(wantsHit);
        }

        throw new IllegalArgumentException(String.format("잘못된 입력입니다. '%s' 또는 '%s'만 입력해야 합니다.",
                wantsHit, doesNotWantHit));
    }
}
