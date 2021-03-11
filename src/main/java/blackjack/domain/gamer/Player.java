package blackjack.domain.gamer;

import blackjack.domain.ResultType;
import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import java.util.List;

public class Player {

    private final String name;
    private final int money;
    private final Hands hands;

    public Player(String name, int money,  Hands hands) {
        validate(money);
        this.name = name;
        this.money = money;
        this.hands = hands;
    }

    private void validate(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("[에러]: 베팅 금액은 음수가 될 수 없습니다.");
        }
    }

    public List<Card> showHands() {
        return hands.toList();
    }

    public String getName() {
        return name;
    }

    public Score calculateScore() {
        return hands.calculate();
    }

    public boolean isBusted() {
        return calculateScore().isBusted();
    }

    public boolean hasBlackjack() {
        return hands.isBlackjack();
    }

    public void receiveCard(Card card) {
        hands.addCard(card);
    }

    public List<Card> showOpenHands() {
        return hands.cardsOf(2);
    }

    public double calculateProfit(ResultType resultType) {
        if (ResultType.WIN.equals(resultType)) {
            if (hands.isBlackjack()) {
                return money * 1.5;
            }
            return money;
        }
        if (ResultType.LOSE.equals(resultType)) {
            return -money;
        }
        return 0;
    }

    public boolean isMaxScore() {
        return hands.isMaxScore();
    }

    public boolean isUnderMaxScore() {
        return hands.isUnderMaxScore();
    }

    public int getScore() {
        return calculateScore().getValue();
    }
}
