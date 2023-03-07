package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

import java.util.List;
import java.util.stream.Collectors;

public class Participant {

    private final Name name;
    private final Hand cards;

    public Participant(final Name name, final List<Card> cards) {
        this.name = name;
        this.cards = new Hand(cards);
    }

    public void draw(final Card card) {
        cards.add(card);
    }

    public int getScore() {
        return cards.getScore();
    }

    public boolean isBust() {
        return getScore() > 21;
    }

    public String getName() {
        return name.getValue();
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    public List<String> getCardNames() {
        return cards.getCards().stream()
                .map(Card::getCardName)
                .collect(Collectors.toList());
    }
}
