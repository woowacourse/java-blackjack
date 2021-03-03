package blackjack.domain.player;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;

public class Dealer implements Player {

    private static final int NUMBER_OF_INITIAL_CARDS = 1;
    private static final int LIMIT_SCORE_TO_HIT = 16;

    private final Name name;
    private final Cards cards;

    public Dealer(){
        name = new Name("딜러");
        cards = new Cards();
    }

    private boolean ableToDraw(){
        return cards.getScore().isBelow(LIMIT_SCORE_TO_HIT);
    }

    @Override
    public void initializeCards(Deck deck) {
        for(int i=0; i<NUMBER_OF_INITIAL_CARDS; i++){
            cards.add(deck.draw());
        }
    }

    @Override
    public void drawCard(Deck deck) {
        cards.add(deck.draw());
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
