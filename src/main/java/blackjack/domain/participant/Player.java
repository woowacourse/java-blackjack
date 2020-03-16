package blackjack.domain.participant;

import blackjack.domain.result.PlayerResult;
import blackjack.domain.result.ResultType;

import static blackjack.domain.result.ResultType.BUST;

public class Player extends Participant {
    private static final String YES = "y";
    private static final String NO = "n";
    static final String NOT_SUPPORTED_REPLY_ERR_MSG = "응답은 y 혹은 n만 가능합니다.";

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean canGetMoreCard() {
        return cards.computeScore() < BUST;
    }

    public PlayerResult createPlayerResult(Dealer dealer) {
        ResultType resultType = ResultType.findResultByScore(computeScore(), dealer.computeScore());
        return new PlayerResult(name, resultType);
    }

    public boolean wantMoreCard(String reply) {
        if (YES.equals(reply)) {
            return true;
        }
        if (NO.equals(reply)) {
            return false;
        }
        throw new IllegalArgumentException(NOT_SUPPORTED_REPLY_ERR_MSG);
    }
}
