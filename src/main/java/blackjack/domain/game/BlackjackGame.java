package blackjack.domain.game;

import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;

public class BlackjackGame {
    public static final int DEAL_CARDS_COUNT = 2;

    private final Deck deck;

    public BlackjackGame(Deck deck) {
        this.deck = deck;
    }

    public void deal(Dealer dealer, Players players) {
        dealer.draw(deck.pickCards(DEAL_CARDS_COUNT));
        players.draw(() -> deck.pickCards(DEAL_CARDS_COUNT));
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
