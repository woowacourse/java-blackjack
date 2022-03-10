package blackjack.view;

import static blackjack.model.Rank.ACE;
import static blackjack.model.Rank.EIGHT;
import static blackjack.model.Rank.FIVE;
import static blackjack.model.Rank.FOUR;
import static blackjack.model.Rank.JACK;
import static blackjack.model.Rank.NINE;
import static blackjack.model.Rank.QUEEN;
import static blackjack.model.Rank.SEVEN;
import static blackjack.model.Rank.SIX;
import static blackjack.model.Rank.TEN;
import static blackjack.model.Rank.THREE;
import static blackjack.model.Rank.TWO;
import static blackjack.model.Suit.HEART;
import static blackjack.model.Suit.SPADE;

import blackjack.model.Card;
import blackjack.model.Dealer;
import blackjack.model.Player;
import blackjack.model.Rank;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private OutputView() {}

    public static void printOpenCard(Dealer dealer, List<Player> gamers) {
        System.out.printf("%s와 %s에게 2장을 나누었습니다.", dealer.name(), gamerNames(gamers));

        System.out.printf("%s: %s", dealer.name(), cards(dealer));

        for (Player gamer : gamers) {
            System.out.printf("%s");
            for (Card card : gamer.openCards()) {

            }
        }
    }

    private static String gamerNames(List<Player> gamers) {
        return gamers.stream()
            .map(Player::name)
            .collect(Collectors.joining(", "));
    }

    private static String cards(Player player) {
        return player.openCards().stream().map(card -> card(card)).collect(Collectors.joining(", "));
    }

    private static String card(Card card) {
        return card.rank().symbol() + card.suit().symbol();
    }
}
