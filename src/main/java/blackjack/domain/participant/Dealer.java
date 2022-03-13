package blackjack.domain.participant;

public class Dealer extends Participant {

    private static final int MIN_SUM_STANDARD = 16;

    public Dealer() {
        super("딜러");
    }

    public boolean checkUnderSumStandard() {
        return holdingCard.cardSum() <= MIN_SUM_STANDARD;
    }
}
