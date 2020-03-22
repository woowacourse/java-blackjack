package domain.gamer;

import domain.PlayerResult;
import domain.card.Deck;
import domain.card.PlayingCards;

public class Dealer extends Player {
    private static final int ADD_THRESHOLD = 17;
    private static final String DEALER_NAME = "딜러";
    private static final int INIT_DEALER_MONEY = 0;

    public Dealer(PlayingCards playingCards) {
        super(DEALER_NAME, INIT_DEALER_MONEY, playingCards);
    }

    public void receiveDealerCard(Deck deck) {
        if (canGetExtraCard()) {
            addCard(deck);
        }
    }

    public boolean canGetExtraCard() {
        return playingCards.calculateScore() < ADD_THRESHOLD;
    }

    public PlayerResult compare(Player player) {
        if (player.isBlackJack() && !isBlackJack()) {
            return PlayerResult.BLACKJACKWIN;
        } else if (player.isBust() || (player.isBust() && isBust())) {
            return PlayerResult.LOSE;
        } else if ((!player.isBust() && isBust()) || (player.calculateScore() > calculateScore())) {
            return PlayerResult.WIN;
        } else if (player.calculateScore() == calculateScore()) {
            return PlayerResult.DRAW;
        }
        return PlayerResult.LOSE;
    }
}
