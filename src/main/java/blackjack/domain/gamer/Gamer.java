package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.rule.CardCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Gamer {

    protected List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }

    public boolean isBusted() {
        return calculateSum() > CardCalculator.BUST_THRESHOLD;
    }

    public int calculateSum() {
        return CardCalculator.calculate(cards);
    }

    public abstract String getName();

    public String getCardStatus() {
        return cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(","));
    }
}
