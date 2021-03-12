package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;
import java.util.Collections;
import java.util.List;

public class Dealer implements Participant {
    private static final Score MINIMUM_SCORE_OF_NOT_TAKING_CARD = Score.of(17);
    private final Name name;
    private final Cards cards;

    public Dealer() {
        this(new Name("딜러"), new Cards(Collections.emptyList()));
    }

    public Dealer(Cards cards) {
        this(new Name("딜러"), cards);
    }

    public Dealer(Name name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    @Override
    public boolean isNotAbleToTake() {
        return sumCards().isHigherThan(MINIMUM_SCORE_OF_NOT_TAKING_CARD);
    }

    @Override
    public void takeCard(Card card) {
        cards.takeCard(card);
    }

    @Override
    public Name getName() {
        return this.name;
    }

    @Override
    public Score sumCards() {
        return cards.sumCards();
    }

    @Override
    public Score sumCardsForResult() {
        return cards.sumCardsForResult();
    }

    @Override
    public Cards getCards() {
        return cards;
    }

    @Override
    public List<Card> getUnmodifiableCards() {
        return cards.getUnmodifiableList();
    }

}
