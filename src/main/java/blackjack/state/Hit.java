package blackjack.state;

import blackjack.domain.card.Card;

import java.util.List;

public class Hit implements State{

    private Cards cards;

    public Hit(Cards cards){
        this.cards = cards;
    }
}
