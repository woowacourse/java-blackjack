package blackJack.domain.participant;

import blackJack.domain.money.Bet;

public class Player extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final String ERROR_MESSAGE_PROHIBIT_NAME = "플레이어의 이름은 '딜러'일 수 없습니다.";
    private static final int BLACK_JACK = 21;

    private Bet bet;

    public Player(String name) {
        super(name);
        this.bet = Bet.ZERO;
        validateProhibitName(name);
    }

    private void validateProhibitName(String name) {
        if (name.equals(DEALER_NAME)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_PROHIBIT_NAME);
        }
    }

    public void betting(int money) {
        bet = bet.add(money);
    }

    @Override
    public boolean isAvailableHit() {
        return this.getScore() <= BLACK_JACK;
    }

    public Bet getBet() {
        return bet;
    }
}
