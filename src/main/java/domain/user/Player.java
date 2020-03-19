package domain.user;

import domain.card.Cards;
import domain.card.PlayingCards;

public class Player extends User {
    public Player(PlayingCards playingCards, String name, Money money) {
        super(playingCards, name, money);
    }

    boolean wantToHit(String willForMoreCard) {
        return willForMoreCard.equals("y");
    }

    void confirmCards(Cards cards) {
        this.playingCards.add(cards);
    }
}
