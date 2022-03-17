package model.participator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.Status;
import model.betting.Profit;
import model.card.Card;
import model.card.Cards;
import model.card.cardGettable.CardsGettable;

public abstract class Participator {
    private final String name;
    private final Cards cards;
    private Profit profit;

    public Participator(String name) {
        checkNameIsNullOrEmpty(name);
        this.cards = new Cards(new ArrayList<>());
        this.name = name;
        this.profit = new Profit(0);
    }

    private void checkNameIsNullOrEmpty(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("이름 입력 실패 : 이름이 빈값이나 공백이 될 수 없습니다.");
        }
    }

    protected void setCardsGettableStrategy(CardsGettable strategy) {
        cards.setCardsGettableStrategy(strategy);
    }

    public abstract boolean canReceiveCard();

    public void receiveCard(Card card) {
        cards.addCard(card);
    }

    public boolean isSameName(String otherName) {
        return name.equals(otherName);
    }

    public void addProfit(long amount) {
        profit = profit.add(amount);
    }

    public Status getStatus() {
        return cards.getStatus();
    }

    public int getSum() {
        return cards.getSum();
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards.getCardsByStrategy());
    }

    public String getPlayerName() {
        return name;
    }

    public long getProfit() {
        return profit.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participator that = (Participator) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
