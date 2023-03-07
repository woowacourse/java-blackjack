package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Participant {

    private final Name name;
    private final Hand hand;

    public Participant(final Name name, final List<Card> cards) {
        this.name = name;
        this.hand = new Hand(cards);
    }

    public void draw(final Card card) {
        hand.add(card);
    }

    public int getScore() {
        return hand.getScore();
    }

    public abstract boolean isHit();

    public String getName() {
        return name.getValue();
    }

    public List<Card> getHand() {
        return hand.getCards();
    }

    public List<String> getCardNames() {
        return hand.getCards().stream()
                .map(Card::getCardName)
                .collect(Collectors.toList());
    }
}
