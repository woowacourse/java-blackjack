package blackjack.model.player;

public class Dealer extends Player {
    private static final String NAME = "딜러";
    private static final int SCORE_HIT_CRITERIA = 16;

    private final String name;

    public Dealer() {
        super();
        this.name = NAME;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isImpossibleHit() {
        return this.cards.isScoreOverThan(SCORE_HIT_CRITERIA);
    }
}
