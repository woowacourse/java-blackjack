package blackjack.domain.player;

import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;

public class Gambler implements Player{

    private static final int NUMBER_OF_INITIAL_CARDS = 2;

    private final Name name;
    private final Cards cards;

    public Gambler(Name name){
        this.name= name;
        this.cards = new Cards();
    }

    @Override
    public void initializeCards(Deck deck){
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
