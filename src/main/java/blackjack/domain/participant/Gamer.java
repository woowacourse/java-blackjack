package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;

import java.util.List;
import java.util.Objects;

import static blackjack.domain.participant.Player.THRESHOLD_OF_BURST;

public abstract class Gamer {
    public static final String COMMA_DELIMITER = ",";
    private static final String ERROR_MESSAGE_WITH_SPACE = "이름에 공백이 포함됩니다.";
    private static final String SPACE = " ";

    protected final Cards cards;
    private final String name;

    protected Gamer(String name) {
        validateSpace(name);
        this.name = name;
        cards = new Cards();
    }

    private void validateSpace(String name) {
        if (name.contains(SPACE)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_WITH_SPACE);
        }
    }

    public void receiveCard(Card card) {
        cards.addCard(card);
    }

    public void receiveOneCard() {
        receiveCard(Deck.dealCard());
    }

    public boolean isBurst() {
        return cards.calculateMaximumPoint() > THRESHOLD_OF_BURST;
    }

    public int makeJudgingPoint() {
        return cards.calculateJudgingPoint();
    }

    public int makeMaximumPoint() {
        return cards.calculateMaximumPoint();
    }

    public abstract boolean canReceiveCard();

    public abstract Boolean continueDraw(String draw);

    public String getName() {
        return name;
    }

    public List<Card> toList() {
        return cards.getCards();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gamer gamer = (Gamer) o;
        return Objects.equals(name, gamer.name) && Objects.equals(cards, gamer.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cards);
    }
}
