package blackjack.view;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String NAME_DELIMITER = ", ";

    public static void printInputNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void printGameInitializeMessage(Dealer dealer, List<Player> players, int startingCardCount) {
        String playerNames = String.join(NAME_DELIMITER,
            players.stream()
                .map(Player::getName)
                .collect(Collectors.toList()));
        System.out.println("\n" + dealer.getName() + "와 " + playerNames + "에게 " + startingCardCount + "장의 카드를 나누었습니다.");

    }
}
