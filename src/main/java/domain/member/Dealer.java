package domain.member;

public class Dealer implements Participant {

    private final MemberInfo dealerInfo;

    public Dealer(MemberInfo dealerInfo) {
        this.dealerInfo = dealerInfo;
    }

    @Override
    public MemberInfo info() {
        return dealerInfo;
    }
}
