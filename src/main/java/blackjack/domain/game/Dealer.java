package blackjack.domain.game;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends Gamer {

    public static final int RECEIVED_MAXIMUM = 16;
    public static final String NAME = "딜러";

    public Dealer() {
        super(NAME);
    }

    public boolean isLowerScore(final Player player) {
        return sumOfCards() < player.sumOfCards();
    }

    public List<Card> openPartOfCards() {
        return playingCards.getPartOfCard();
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean isDrawable() {
        return playingCards.calculateTotal() <= RECEIVED_MAXIMUM;
    }
}
