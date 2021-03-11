package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;
import java.util.Collections;
import java.util.List;

public class Player implements Participant {
    private final Name name;
    private final Cards cards;

    public Player(Name name) {
        this(name, new Cards(Collections.emptyList()));
    }

    public Player(Name name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    @Override
    public boolean isNotAbleToTake() {
        return sumCards().isBurst();
    }

    @Override
    public void takeCard(Card card) {
        cards.takeCard(card);
    }

    @Override
    public String getName() {
        return this.name.toString();
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
