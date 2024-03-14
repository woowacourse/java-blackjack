package blackjack.domain.game;

import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;

import java.util.ArrayList;
import java.util.List;

public class BlackjackGame {
    public static final int DEAL_CARDS_COUNT = 2;
    private static final int SHOW_DEALER_DEAL_CARD_COUNT = 1;

    private final Deck deck;

    public BlackjackGame(Deck deck) {
        this.deck = deck;
    }

    public Hand makeInitialHand() {
        return new Hand(List.of());
    }

    public void deal(Dealer dealer, Players players) {
        dealer.draw(deck.pickCards(DEAL_CARDS_COUNT));

        List<Hand> playersCards = new ArrayList<>();
        for (int i = 0; i < players.getPlayersCount(); i++) {
            playersCards.add(new Hand(deck.pickCards(DEAL_CARDS_COUNT)));
        }

        players.draw(playersCards);
    }

    public boolean isPlayerCanHit(Player player) {
        return !player.isBust() && !player.isBlackjack() && !player.isMaxScore();
    }

    public void hit(Player player) {
        player.draw(deck.pickCard());
    }

    public int drawUntilOverBoundWithCount(Dealer dealer) {
        int count = 0;
        while (dealer.isScoreUnderBound()) {
            dealer.draw(deck.pickCard());
            count++;
        }
        return count;
    }
}
