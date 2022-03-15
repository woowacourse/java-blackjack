package blackjack.model.player;

import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.card.Cards;

public class Dealer extends Participant {
    private static final int SCORE_HIT_CRITERIA = 16;

    public Dealer() {
        super("딜러");
    }

    private Dealer(final String name, final Cards cards){
        super(name, cards);
    }

    @Override
    public Participant receive(final Card card) {
        String name = super.name;
        Cards newCards = this.cards.add(card);
        return new Dealer(name, newCards);
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
    public Participant drawBy(final CardDeck deck) {
        String name = super.name;
        Cards newCards = this.cards.add(deck.draw());
        return new Dealer(name, newCards);
    }

    @Override
    public boolean isImpossibleHit() {
        return cards.sumScore() > SCORE_HIT_CRITERIA;
    }
}
