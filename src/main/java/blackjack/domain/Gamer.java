package blackjack.domain;

import static blackjack.domain.Denomination.*;

import java.util.List;

public abstract class Gamer {

    private final String name;
    private final BattingMoney battingMoney;
    private final Cards cards;

    public Gamer(String name, int battingMoney, List<Card> cards) {
        this.name = name.trim();
        checkName(this.name);

        this.battingMoney = new BattingMoney(battingMoney);
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

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }

    abstract boolean canHit();

    public boolean addMoney(int value) {
        return battingMoney.addMoney(value);
    }

    public String getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }

    public int getBattingMoney() {
        return battingMoney.getValue();
    }
}
