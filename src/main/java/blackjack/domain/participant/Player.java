package blackjack.domain.participant;

import java.util.Objects;

import static blackjack.domain.participant.Dealer.DEALER_NAME;

public class Player extends Participant {

    static final int MAX_SCORE_TO_HIT = 21;
    static final String DEALER_NAME_MESSAGE = String.format("%s라는 이름은 사용할 수 없습니다.", DEALER_NAME);

    private final Bet bet;

    public Player(String name, int betAmount) {
        super(validateNameNotEqualToDealer(name));
        this.bet = new Bet(betAmount);
    }

    private static String validateNameNotEqualToDealer(String name) {
        if (DEALER_NAME.equals(name)) {
            throw new IllegalArgumentException(DEALER_NAME_MESSAGE);
        }
        return name;
    }

    public boolean isHittable() {
        return getTotalScore() < MAX_SCORE_TO_HIT;
    }

    public int getBetAmount() {
        return bet.getAmount();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player that = (Player) o;
        return Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
