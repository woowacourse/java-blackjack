package blackjack.domain.player;

import blackjack.domain.BetMoney;

public class Dealer extends Player {

    private static final String NAME = "딜러";
    private static final String NOT_ALLOW_METHOD_MESSAGE = "딜러는 배탱액을 조회할 수 없습니다.";

    public Dealer() {
        super(NAME);
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    @Override
    public BetMoney getBetMoney() {
        throw new UnsupportedOperationException(NOT_ALLOW_METHOD_MESSAGE);
    }
}
