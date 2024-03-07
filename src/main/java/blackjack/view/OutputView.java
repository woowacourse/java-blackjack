package blackjack.view;

import java.util.List;
import java.util.stream.Collectors;
import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Player;

public class OutputView {

    public void printInitialDeal(Dealer dealer, List<Player> players) {
        System.out.printf("%s와 %s에게 2장을 나누었습니다.%n", dealer.getName(), buildPlayerNameMessage(players));
        System.out.printf("%s: %s%n", dealer.getName(), buildDealerInitialDealMessage(dealer.getCards()));
        for (Player player : players) {
            System.out.printf("%s카드: %s%n", player.getName(), buildCardsMessage(player.getCards()));
        }
    }

    private String buildDealerInitialDealMessage(List<Card> cards) {
        return buildCardsMessage(cards.subList(1, cards.size()));
    }

    private String buildCardsMessage(List<Card> cards) {
        return cards.stream()
                .map(Card::getName)
                .collect(Collectors.joining(", "));
    }

    private String buildPlayerNameMessage(List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));
    }
}
