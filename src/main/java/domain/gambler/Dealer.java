package domain.gambler;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.Cards;
import domain.card.Score;

public class Dealer implements Gambler {
    private static final int FIRST_INDEX = 0;
    private static final int DEALER_MAX_HIT_SCORE = 16;
    private static final Name NAME = new Name("딜러");

    private final Name name;
    private final Cards cards;

    public Dealer() {
        this.cards = new Cards();
        this.name = NAME;
    }

    @Override
    public boolean canHit() {
        return cards.canHit(DEALER_MAX_HIT_SCORE);
    }

    @Override
    public void drawCard(CardDeck cardDeck, int cardCount) {
        for (int i = 0; i < cardCount; i++) {
            cards.add(cardDeck.draw());
        }
    }

    public void drawCard(CardDeck cardDeck) {
        drawCard(cardDeck, 1);
    }

    public Card getFirstCard() {
        return cards.getCards().get(FIRST_INDEX);
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public Score getScore() {
        return cards.getScore();
    }

    @Override
    public Cards getCards() {
        return cards;
    }
}
