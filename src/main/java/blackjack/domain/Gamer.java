package blackjack.domain;

import static blackjack.domain.Denomination.*;

import java.util.List;

public abstract class Gamer {

    private final Name name;
    private final BattingMoney battingMoney;
    private final Cards cards;

    public Gamer(String name, int battingMoney, List<Card> cards) {
        this.name = new Name(name);
        this.battingMoney = new BattingMoney(battingMoney);
        this.cards = new Cards(cards);
    }

    public int getTotalScore() {
        return cards.calculateTotalScore();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return getTotalScore() > BLACKJACK_SCORE;
    }

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }

    abstract boolean canHit();

    public boolean addMoney(int value) {
        return battingMoney.addMoney(value);
    }

    public String getName() {
        return name.getValue();
    }

    public Cards getCards() {
        return cards;
    }

    public int getBattingMoney() {
        return battingMoney.getValue();
    }
}
