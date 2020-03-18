package domain.user;

import domain.card.Cards;
import domain.card.PlayingCards;

public class Player extends User {
    Player(PlayingCards playingCards, String name) {
        super(playingCards, name);
    }

    boolean wantToHit(String willForMoreCard) {
        return willForMoreCard.equals("y");
    }

    void confirmCards(Cards cards) {
        this.playingCards.add(cards);
    }
}
