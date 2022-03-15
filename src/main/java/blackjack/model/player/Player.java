package blackjack.model.player;

import blackjack.model.card.CardDeck;
import blackjack.model.card.Cards;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Player extends Participant {

    public Player(final String name) {
        super(name);
    }

    private Player(final String name, final Cards cards) {
        super(name, cards);
    }

    public void hitOrStayBy(CardDeck cardDeck, Predicate<String> predicate, BiConsumer<String, List<String>> consumer) {
        while (canHit() && isHitSign(predicate)) {
            hitBy(cardDeck);
            consumer.accept(getName(), getCards());
        }
    }

    private boolean canHit() {
        return !cards.isBlackjack() && !cards.isBust();
    }

    private boolean isHitSign(Predicate<String> predicate) {
        return predicate.test(getName());
    }

    @Override
    public Participant drawCardsBy(final CardDeck deck) {
        String name = this.name;
        Cards newCards = this.cards;
        for (int i = 0; i < Player.START_DRAW_COUNT; i++) {
            newCards = this.cards.add(deck.draw());
        }
        return new Player(name, newCards);
    }

    @Override
    public Participant hitBy(final CardDeck deck) {
        String name = super.name;
        Cards newCards = this.cards.add(deck.draw());
        return new Player(name, newCards);
    }
}
