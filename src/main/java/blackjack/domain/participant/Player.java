package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Player extends Participant {

    private final String name;

    public Player(String name, CardDeck cardDeck) {
        super(cardDeck);
        this.name = name;
    }

    @Override
    public boolean canHit() {
        Set<Integer> possibleSum = cardDeck.calculatePossibleSum();
        int minScore = Collections.min(possibleSum);
        return minScore <= BLACKJACK_GOAL_SCORE;
    }

    @Override
    public List<Card> showStartCards() {
        List<Card> cards = cardDeck.getCards();
        return cards.subList(0,2);
    }

    @Override
    public String getName() {
        return name;
    }
}
