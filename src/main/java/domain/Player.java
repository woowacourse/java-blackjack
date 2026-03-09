package domain;

import domain.constant.BlackJackRule;
import domain.constant.DealerName;

public class Player extends Participant {

    Player(String name, Hand hand) {
        super(name, hand);
        requireNonDealer(name);
    }

    static Player of(String name, DrawStrategy drawStrategy) {
        return new Player(name, Hand.based(drawStrategy));
    }

    @Override
    protected boolean isPlayable() {
        return hand.scoreSum() <= BlackJackRule.PLAYER_PLAYABLE_THRESHOLD.value();
    }

    private void requireNonDealer(String name) {
        if (name.equals(DealerName.DEFAULT.dealerName())) {
            String errorMessage = String.format("플레이어는 \"%s\"라는 이름을 사용할 수 없다.", DealerName.DEFAULT.dealerName());
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
