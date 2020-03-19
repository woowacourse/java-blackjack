package domain.user;

import domain.card.Cards;
import domain.card.PlayingCards;
import domain.result.Result;

public class Player extends User {
    public Player(PlayingCards playingCards, String name, Money money) {
        super(playingCards, name, money);
    }

    @Override
    public Money calculateProfit(Result result) {
        return null;
    }

    boolean wantToHit(String willForMoreCard) {
        return willForMoreCard.equals("y");
    }

    void confirmCards(Cards cards) {
        this.playingCards.add(cards);
    }
}
