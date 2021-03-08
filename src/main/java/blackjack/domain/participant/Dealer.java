package blackjack.domain.participant;

public class Dealer extends Participant {
    private static final Name name = new Name("딜러");
    private static final int STAY_THRESHOLD = 17;

    public boolean isStay() {
        return calculateResult() >= STAY_THRESHOLD;
    }

    public String getFirstCardInfo() {
        return cards.get(0).toString();
    }

    @Override
    public String getName() {
        return name.toString();
    }
}
