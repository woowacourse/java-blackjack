package domain.participant;

import domain.Deck;
import domain.card.Card;
import domain.card.Cards;
import domain.GamePoint;

import java.util.Collections;
import java.util.List;

public final class Player implements Participant {

    private static final int INITIAL_CARD_COUNT = 2;

    private final Name name;
    private Cards cards;

    private Player(Name name, Cards cards) {
        validateCardsSize(cards.size());
        this.name = name;
        this.cards = cards;
    }

    private Player(Name name) {
        this.name = name;
        this.cards = new Cards(Collections.emptyList());
    }

    public static Player create(Name name, Cards cards) {
        return new Player(name, cards);
    }

    public static Player of(Name name) {
        return new Player(name);
    }

    private void validateCardsSize(final int size) {
        if (size != INITIAL_CARD_COUNT) {
            throw new IllegalArgumentException("유저는 카드 2장 이상을 갖고 있어야 합니다.");
        }
    }

    @Override
    public void draw(final Card card) {
        if (canReceive()) {
            this.cards = cards.add(card);
        }
    }

    @Override
    public boolean canReceive() {
        return cards.isEmpty() || !cards.isBust();
    }

    @Override
    public GamePoint getGamePoint() {
        return cards.getPoint();
    }

    public List<Card> openCards() {
        return cards.getCards();
    }

    public boolean isGreaterThan(final GamePoint point) {
        return cards.isGreaterThan(point);
    }

    public boolean isEqualTo(final GamePoint point) {
        return cards.havePointOf(point);
    }

    public boolean isLowerThan(final GamePoint point) {
        return cards.isLowerThan(point);
    }

    public boolean hasSameName(final Player player) {
        return this.name.equals(player.getName());
    }

    public Name getName() {
        return name;
    }

    public boolean isBusted() {
        return cards.isBust();
    }

    public Cards getCards() {
        return cards;
    }

    public void give(final Deck deck, final int count) {
        for (int i = 0; i < count; i++) {
            draw(deck.drawCard());
        }
    }
}
