package blackjack.domain;

public class Dealer {

    private final Cards cards;

    public Dealer(final Cards cards) {
        this.cards = cards;
    }

    public boolean needMoreCard() {
        return calculate() <= 16;
    }

    // TODO : 플레이어와의 중복 로직 제거
    public int calculate() {
        int sum = cards.sum();
        int aceCount = cards.countAce();

        while (sum > BlackjackStatus.BLACKJACK_NUMBER && aceCount > 0) {
            sum = sum - 10;
            aceCount--;
        }
        return sum;
    }
}
