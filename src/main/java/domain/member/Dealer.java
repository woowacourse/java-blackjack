package domain.member;

public class Dealer implements Participant {
    private static final int DEALER_DRAW_THRESHOLD = 16;

    private final MemberInfo dealerInfo;

    public Dealer(MemberInfo dealerInfo) {
        this.dealerInfo = dealerInfo;
    }

    @Override
    public MemberInfo info() {
        return dealerInfo;
    }

    public boolean canDrawCard() {
        return !isFinished() && currentScore() <= DEALER_DRAW_THRESHOLD;
    }
}
