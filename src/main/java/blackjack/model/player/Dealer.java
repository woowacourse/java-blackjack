package blackjack.model.player;

import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.card.Cards;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class Dealer extends Participant {

    public Dealer() {
        super("딜러");
    }

    private Dealer(final String name, final Cards cards){
        super(name, cards);
    }

    public void hitOrStayBy(CardDeck cardDeck, Predicate<String> predicate, BiConsumer<String, List<String>> consumer) {
        while (canHit()) {
            hitBy(cardDeck);
            consumer.accept(super.getName(), super.getCards());
        }
    }

    private boolean canHit() {
        return !super.cards.isBlackjack() && !super.cards.isStopScore();
    }

    @Override
    public Participant drawCardsBy(final CardDeck deck) {
        String name = this.name;
        Cards newCards = this.cards;
        for (int i = 0; i< Player.START_DRAW_COUNT; i++) {
            newCards = this.cards.add(deck.draw());
        }
        return new Dealer(name, newCards);
    }

    @Override
    public Participant hitBy(final CardDeck deck) {
        String name = super.name;
        Cards newCards = this.cards.add(deck.draw());
        return new Dealer(name, newCards);
    }
}
