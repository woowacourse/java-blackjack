package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;

import java.util.List;
import java.util.Objects;

import static blackjack.domain.participant.Player.THRESHOLD_OF_BURST;

public abstract class Gamer {
    public static final String COMMA_DELIMITER = ",";

    private final PlayerName name;
    private final Cards cards;

    protected Gamer(String name) {
        this.name = new PlayerName(name);
        this.cards = new Cards();
    }

    public void receiveCard(Card card) {
        cards.addCard(card);
    }

    public boolean isBurst(int point) {
        return point > THRESHOLD_OF_BURST;
    }

    public int makeJudgingPoint() {
        return cards.calculateJudgingPoint();
    }

    public int makeFinalPoint() {
        return cards.calculateIncludeAce();
    }

    public abstract boolean canReceiveCard();

    public abstract boolean continueDraw(String draw, Deck deck);

    public String getName() {
        return name.getName();
    }

    public List<Card> getCards() {
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
