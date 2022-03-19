package blackjack.domain;

import static blackjack.domain.Denomination.*;

import java.util.List;

public abstract class Gamer {

    private final String name;
    private final BettingMoney bettingMoney;
    private final Cards cards;

    public Gamer(String name, int bettingMoney, List<Card> cards) {
        this.name = name.trim();
        checkName(this.name);

        this.bettingMoney = new BettingMoney(bettingMoney);
        this.cards = new Cards(cards);
    }

    private void checkName(String value) {
        if (value.isBlank()) {
            throw new IllegalArgumentException("이름은 공백이 아니어야합니다.");
        }
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getTotalScore() {
        return cards.calculateTotalScore();
    }

    public boolean isBust() {
        return getTotalScore() > BLACKJACK_SCORE;
    }

    public boolean isTie(Gamer gamer) {
        return this.getTotalScore() == gamer.getTotalScore();
    }

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }

    abstract boolean canHit();

    abstract boolean calculateBettingMoneyResult(Gamer player);

    public boolean addMoney(int value) {
        return bettingMoney.addMoney(value);
    }

    public String getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }

    public int getBettingMoney() {
        return bettingMoney.getValue();
    }
}
