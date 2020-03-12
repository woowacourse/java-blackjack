package blackjack.domain.participant;

import blackjack.domain.card.Cards;
import blackjack.domain.result.PlayerResult;
import blackjack.domain.result.ResultType;

public class Player extends Participant {
    public Player(String name) {
        super(name);
    }

    @Override
    public boolean canGetMoreCard() {
        return cards.computeScore() < Cards.MAX_SUM;
    }

    public PlayerResult createPlayerResult(Dealer dealer) {
        ResultType resultType = ResultType.findResultByScore(computeScore(), dealer.computeScore());
        return new PlayerResult(name, resultType);
    }
}
