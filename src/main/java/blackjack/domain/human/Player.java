package blackjack.domain.human;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.Result;

public class Player extends Human {
    private final Name name;
    private final Cards cards;
    private Result result;

    private Player(Name name) {
        this.name = name;
        this.cards = Cards.of();
    }

    public static Player of(Name name) {
        return new Player(name);
    }

    public void calculateResult(final int dealerPoint) {
        int point = getPoint();
        if (dealerPoint > 21) {
            if (point <= 21) {
                setResult(Result.WIN);
                return;
            }
            setResult(Result.LOSE);
            return;
        }
        if (point > 21 || dealerPoint > point) {
            setResult(Result.LOSE);
            return;
        }
        if (dealerPoint == point) {
            setResult(Result.DRAW);
            return;
        }
        setResult(Result.WIN);
    }

    private void setResult(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    @Override
    public boolean isOneMoreCard() {
        return cards.getPoint() < 21;
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
}
