package blackjack.domain.human;

import static blackjack.util.Constants.BLACKJACK_NUMBER;

import blackjack.domain.Result;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Player extends Human {
    private final Name name;
    private final Cards cards;
    private Result result;

    private Player(String name) {
        this.name = Name.of(name);
        this.cards = Cards.of();
    }

    public static Player of(String name) {
        return new Player(name);
    }

    public void calculateResult(final int dealerPoint) {
        int point = getPoint();
        if (dealerPoint > BLACKJACK_NUMBER) {
            this.result = getResultIfDealerBurst(point);
            return;
        }
        this.result = getResultIfDealerNotBurst(dealerPoint, point);
    }

    private Result getResultIfDealerBurst(final int point) {
        if (point <= BLACKJACK_NUMBER) {
            return Result.WIN;
        }
        return Result.LOSE;
    }

    private Result getResultIfDealerNotBurst(final int dealerPoint, final int point) {
        if (point > BLACKJACK_NUMBER || dealerPoint > point) {
            return Result.LOSE;
        }
        if (dealerPoint == point) {
            return Result.DRAW;
        }
        return Result.WIN;
    }

    public Result getResult() {
        return result;
    }

    @Override
    public boolean isHit() {
        return cards.getPoint() < BLACKJACK_NUMBER;
    }

    @Override
    public String getName() {
        return name.get();
    }

    @Override
    public Cards getCards() {
        return cards;
    }

    @Override
    public void addCard(final Card card) {
        cards.add(card);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name=" + name +
                ", cards=" + cards +
                ", result=" + result +
                '}';
    }
}
