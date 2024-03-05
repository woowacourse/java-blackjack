package blackjack.domain;

import java.util.List;

public class CardMaker {

    public CardMaker() {

    }

    public List<Card> make() {
        return List.of(Card.SPADE_NINE, Card.CLUB_FIVE);
    }
}
