package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardFactory;

public class CardDistributor {

    private final CardFactory cardFactory;

    public CardDistributor() {
        cardFactory = new CardFactory(Card.getCards());
    }
    
    public void distribute(Gamer gamer, int number) {
        for (int i = 0; i < number; i++) {
            gamer.addCard(cardFactory.draw());
        }
    }
}
