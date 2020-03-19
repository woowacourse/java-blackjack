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
        if (playerLose(result)) {
            return result.calculateProfit(money).multiply(LOSE_PENALTY_RATE);
        }
        return result.calculateProfit(money);
    }

    private boolean playerLose(Result result) {
        return result.equals(Result.DEALER_WIN);
    }

    boolean wantToHit(String willForMoreCard) {
        return willForMoreCard.equals("y");
    }

    void confirmCards(Cards cards) {
        this.playingCards.add(cards);
    }
}
