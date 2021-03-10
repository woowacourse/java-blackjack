package blackjack.state;

import blackjack.domain.card.Card;

import java.util.List;

public class Hit implements State{

    private List<Card> cards;

    public Hit(List<Card> cards){
        this.cards = cards;
    }
}
