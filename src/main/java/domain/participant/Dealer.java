package domain.participant;

public class Dealer extends BlackjackParticipant {

    private static final int DEALER_STOP_HIT_STANDARD = 17;

    public Dealer() {
        super(BlackjackParticipant.dealerName());
    }

    @Override
    public boolean isDrawable() {
        int sum = calculateCardSum();
        return sum < DEALER_STOP_HIT_STANDARD;
    }

    @Override
    public double earnMoney(BlackjackParticipant player) {
        double playerEarnMoney = player.earnMoney(this);
        return playerEarnMoney * -1;
    }
}
