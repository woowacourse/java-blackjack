package participant;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int HIT_CONDITION = 17;


    private final String nickname;

    public Dealer() {
        this.nickname = DEALER_NAME;
    }

    @Override
    public boolean canHit() {
        return cards.isHit(HIT_CONDITION);
    }

    @Override
    public String getNickname() {
        return nickname;
    }
}
