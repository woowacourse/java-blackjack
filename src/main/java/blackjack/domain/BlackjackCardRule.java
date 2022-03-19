package blackjack.domain;

public enum BlackjackCardRule {

    INITIALLY_DISTRIBUTED_CARD_COUNT(2);

    private final int count;

    BlackjackCardRule(final int count) {
        this.count = count;
    }

    public boolean isNotEquals(final int count) {
        return this.count != count;
    }

    public boolean isOverThan(final int count) {
        return this.count > count;
    }

}
