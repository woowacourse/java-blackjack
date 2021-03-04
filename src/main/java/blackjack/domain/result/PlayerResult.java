package blackjack.domain.result;

import blackjack.domain.card.Card;
import java.util.List;

public class PlayerResult {

    // 3다이아몬드, 9클로버, 8다이아몬드 - 결과: 20
    // 승
    private final String name;
    private final List<Card> cards;
    private final int sum;
    private final WinOrLose winOrLose;

    // 결과 계산해주는 곳에서 sum,WinOrLose 계산 후 주입
    public PlayerResult(String name, List<Card> cards, int sum, WinOrLose winOrLose) {
        this.name = name;
        this.cards = cards;
        this.sum = sum;
        this.winOrLose = winOrLose;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getSum() {
        return sum;
    }

    public String getWinOrLose() {
        return winOrLose.getName();
    }
}
