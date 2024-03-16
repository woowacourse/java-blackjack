package blackjack.domain.game;

import blackjack.domain.Ownable;
import blackjack.domain.betting.Money;
import blackjack.domain.betting.OwnedMoney;
import blackjack.domain.participant.Name;

public class PlayerResult implements Ownable<Name> {

    private final Name name;
    private final GameResult gameResult;

    public PlayerResult(Name name, GameResult gameResult) {
        this.name = name;
        this.gameResult = gameResult;
    }

    public Money calculatePrize(OwnedMoney bet) {
        return gameResult.calculatePrize(bet.getMoney());
    }

    @Override
    public Name getOwner() {
        return name;
    }
}
