package blackjack.domain.card;

public class Bust extends Finished {
    protected Bust(final Cards cards) {
        super(cards);
    }

    @Override
    public Result play(final Hand other) {
        return Result.LOSE;
    }
}
