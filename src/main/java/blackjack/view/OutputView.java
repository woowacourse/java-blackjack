package blackjack.view;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public static void printPlayersCardHandStatus(Dealer dealer, List<Player> players) {
        /*
        딜러와 pobi, jason에게 2장의 나누었습니다.
        딜러: 3다이아몬드
        pobi카드: 2하트, 8스페이드
        jason카드: 7클로버, K스페이드
*/
        String s1 = players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
        String s = String.format("딜러와 %s에게 2장의 나누었습니다.", s1);
//        System.out.printf();
    }
}
