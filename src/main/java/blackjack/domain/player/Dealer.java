package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dealer extends Player {

    public static final int DEALER_SCORE_PIVOT = 16;

    public Dealer(Cards cards) {
        super(cards, new Name("딜러"));
    }

    @Override
    public List<Card> getInitCards() {//singletonList가 뭐인지?
        return new ArrayList<>(Arrays.asList(cards.getFirstCard()));
    }

    public boolean isEnoughScore() {
        return getScore() > DEALER_SCORE_PIVOT;
    }
}
