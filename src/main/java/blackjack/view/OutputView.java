package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public static void printCardHandStatus(Dealer dealer, Players players) {
        List<Player> readPlayers = players.getPlayers();

        System.out.println("딜러와 " + readPlayers.stream()
                .map(player -> player.getName().getValue())
                .collect(Collectors.joining(", ")) + "에게 2장을 나누었습니다." );

        System.out.println("딜러: " + dealer.showOneCard());

        for (Player player : readPlayers) {
            System.out.printf("%s카드: %s%n",
                    player.getName().getValue(),
                    player.getCards().getCardHand().stream()
                            .map(Card::toString)
                            .collect(Collectors.joining(", ")));
        }
    }
}
