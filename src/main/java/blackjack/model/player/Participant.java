package blackjack.model.player;

import blackjack.model.card.CardDeck;
import blackjack.model.card.Cards;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public abstract class Participant {
    protected static final int START_DRAW_COUNT = 2;

    protected final String name;
    protected final Cards cards;

    public Participant(final String name) {
        checkEmpty(name);
        this.name = name;
        this.cards = new Cards();
    }

    protected Participant(final String name, final Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    private void checkEmpty(final String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 이름은 공백이거나 없을 수 없습니다.");
        }
    }

    public abstract Participant drawCardsBy(final CardDeck cardDeck);

    public abstract void hitOrStayBy(CardDeck cardDeck, Predicate<String> predicate,
                                     BiConsumer<String, List<String>> consumer);

    public abstract Participant hitBy(final CardDeck cardDeck);

    public boolean isWinBy(Participant otherParticipant) {
        return cards.sumScore() > otherParticipant.cards.sumScore();
    }

    public boolean isDrawWith(Participant otherParticipant) {
        return cards.sumScore() == otherParticipant.cards.sumScore();
    }

    public List<String> getCards() {
        return cards.getValues();
    }

    public String getName() {
        return name;
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public boolean isBlackjack() {
        return cards.isBlackjack();
    }
}
