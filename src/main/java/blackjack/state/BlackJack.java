package blackjack.state;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class BlackJack implements State{

    private Cards cards;

    public BlackJack(Cards cards) {
        this.cards = cards;
    }
}
