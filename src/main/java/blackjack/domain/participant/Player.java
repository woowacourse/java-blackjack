package blackjack.domain.participant;

import blackjack.domain.card.Cards;
import blackjack.domain.result.MatchResult;
import blackjack.domain.result.Result;

public class Player extends Participant {
    public Player(String name) {
        super(name);
    }

    @Override
    public boolean canGetMoreCard() {
        return cards.computeScore() < Cards.MAX_SUM;
    }

    public MatchResult createMatchResult(Dealer dealer) {
        Result result = Result.findResultByScore(computeScore(), dealer.computeScore());
        return new MatchResult(name, result);
    }
}
