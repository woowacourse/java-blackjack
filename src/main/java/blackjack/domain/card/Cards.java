package blackjack.domain.card;

import blackjack.domain.participant.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }

    public Score sum() {
        return cards.stream()
                .map(card -> new Score(card.getNumber().getValue()))
                .reduce(new Score(0), Score::increase);
    }

    public int getAceCount() {
        return (int) cards.stream()
                .filter(card -> card.getNumber().equals(Number.ACE))
                .count();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int getCount() {
        return cards.size();
    }

}
