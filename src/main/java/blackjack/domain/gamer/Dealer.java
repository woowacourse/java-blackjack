package blackjack.domain.gamer;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer {
    public static final int INIT_CARD_COUNT = 2;
    public static final int MAX_HIT_SCORE = 16;
    public static final String NAME = "딜러";

    private final Player player;

    private Dealer(Player player) {
        this.player = player;
    }

    public static Dealer newInstance() {
        return new Dealer(Player.newInstance(NAME));
    }

    public static Dealer from(List<Card> cards) {
        return new Dealer(Player.of(NAME, cards));
    }

    public void receiveInitCards(List<Card> cards) {
        player.receiveInitCards(cards);
    }

    public void receiveCard(Card card) {
        player.receiveCard(card);
    }

    public int getScore() {
        return player.getScore();
    }

    public boolean isBust() {
        return player.isBust();
    }

    public boolean isBlackjack() {
        return player.isBlackjack();
    }

    public boolean hasHitScore() {
        return player.getScore() <= MAX_HIT_SCORE;
    }

    public Card getInitCard() {
        return player.getCardHand().get(0);
    }

    @Override
    public String toString() {
        return "Dealer{" +
                "player=" + player +
                '}';
    }

    public List<Card> getCardHand() {
        return player.getCardHand();
    }
}
