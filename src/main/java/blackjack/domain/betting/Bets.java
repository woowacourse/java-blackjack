package blackjack.domain.betting;

import blackjack.domain.game.PlayerResult;
import blackjack.domain.game.GameResult;
import blackjack.domain.participant.Name;
import java.util.ArrayList;
import java.util.List;

public class Bets {

    private final List<OwnedMoney> bets;

    public Bets(List<OwnedMoney> bets) {
        this.bets = new ArrayList<>(bets);
    }

    public OwnedMoney getPrize(PlayerResult gameResult) {
        GameResult result = gameResult.getResult();
        OwnedMoney bet = getOwnedMoney(gameResult.getName());
        Money money = result.calculatePrize(bet.getMoney());
        return new OwnedMoney(gameResult.getName(), money);
    }

    private OwnedMoney getOwnedMoney(Name playerName) {
        OwnedMoney bet = bets.stream()
                .filter(ownedMoney -> ownedMoney.ownedBy(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자 소유의 금액이 없습니다."));
        return bet;
    }
}
