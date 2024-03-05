package blackjack.domain;

public class Dealer {

    public static final Score NEED_CARD_CRITERION = new Score(17);
    private final Cards cards;

    public Dealer(final Cards cards) {
        this.cards = cards;
    }

    public boolean needMoreCard() {
        return NEED_CARD_CRITERION.isBiggerThan(calculate());
    }

    // TODO : 플레이어와의 중복 로직 제거
    public Score calculate() {
        int sum = cards.sum();
        int aceCount = cards.countAce();

        while (sum > BlackjackStatus.BLACKJACK_NUMBER && aceCount > 0) {
            sum = sum - 10;
            aceCount--;
        }
        return new Score(sum);
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public BlackjackStatus getStatus() {
        return BlackjackStatus.from(calculate());
    }
}
