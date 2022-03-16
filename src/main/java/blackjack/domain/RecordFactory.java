package blackjack.domain;

public class RecordFactory {

    private final int dealerScore;

    public RecordFactory(int dealerScore) {
        this.dealerScore = dealerScore;
    }

    public PlayRecord getPlayerRecord(int score) {
        return PlayRecord.of(dealerScore, score);
    }
}
