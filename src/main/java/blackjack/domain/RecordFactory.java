package blackjack.domain;

public class RecordFactory {

    private final int dealerScore;
    private final boolean isDealerBust;

    public RecordFactory(int dealerScore) {
        this.dealerScore = dealerScore;
        this.isDealerBust = dealerScore > 21;
    }

    public Record getRecord(int score) {
        if (isDealerBust) {
            return getRecordWhenDealerBust(score);
        }

        if (score > 21 || score < dealerScore) {
            return Record.LOSS;
        }

        if (dealerScore == score) {
            return Record.PUSH;
        }

        return Record.WIN;
    }

    private Record getRecordWhenDealerBust(int score) {
        if (score > 21) {
            return Record.LOSS;
        }

        return Record.WIN;
    }
}
