package domain.participant;

import domain.card.Card;
import domain.card.Deck;
import domain.card.HandCards;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Participant {
    private final Name name;
    private final HandCards handCards;

    public Participant(Name name) {
        this.name = name;
        this.handCards = new HandCards();
    }

    public void takeCard(Deck deck, int num) {
        for (int generateIndex = 0; generateIndex < num; generateIndex++) {
            drawCard(deck.generateCard());
        }
    }

    public void drawCard(Card card) {
        handCards.takeCard(card);
    }

    public int getScoreSum() {
        return handCards.calculateScore();
    }

    abstract boolean isDrawable();

    public String getName() {
        return name.getValue();
    }

    public HandCards getHandCards() {
        return handCards;
    }

    public List<String> getCardNames() {
        return handCards.getCards().stream()
                .map(Card::getName)
                .collect(Collectors.toUnmodifiableList());
    }
}
