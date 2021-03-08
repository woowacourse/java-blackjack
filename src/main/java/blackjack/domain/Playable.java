package blackjack.domain;

import java.util.List;

public abstract class Playable {
    private static final int WIN = 1;
    private static final int DRAW = 0;
    private static final int LOSE = -1;

    private final String name;
    private final Cards cards;

    public Playable(String name, List<Card> cards) {
        this.name = name;
        this.cards = new Cards(cards);
    }

    public abstract boolean isAvailableToTake();

    public String getName(){
        return this.name;
    }


    public int sumCards() {
        return cards.sumCards();
    }

    public List<Card> getUnmodifiableCards() {
        return cards.getUnmodifiableList();
    }

    public int result(int counterpart) {
        int playerSum = sumCardsForResult();

        if (win(counterpart, playerSum)) {
            return WIN;
        }

        if (lose(counterpart, playerSum)) {
            return LOSE;
        }

        return DRAW;
    }

    private boolean lose(int counterpart, int playerSum) {
        if (counterpart <= Cards.BLACK_JACK && playerSum > Cards.BLACK_JACK) {
            return true;
        }
        if (counterpart <= Cards.BLACK_JACK && counterpart > playerSum) {
            return true;
        }
        return false;
    }

    private boolean win(int counterpart, int playerSum) {
        if (counterpart > Cards.BLACK_JACK && playerSum <= Cards.BLACK_JACK) {
            return true;
        }
        if (playerSum <= Cards.BLACK_JACK && counterpart < playerSum) {
            return true;
        }
        return false;
    }

    public void takeCard(Card card) {
        cards.add(card);
    }

    public int sumCardsForResult() {
        return cards.sumCardsForResult();
    }

}
