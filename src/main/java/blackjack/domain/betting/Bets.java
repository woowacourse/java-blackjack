package blackjack.domain.betting;

import blackjack.domain.game.PlayerResult;
import java.util.ArrayList;
import java.util.List;

public class Bets {

    private final List<OwnedMoney> bets;

    public Bets(List<OwnedMoney> bets) {
        this.bets = new ArrayList<>(bets);
    }

    public OwnedMoney getPrize(PlayerResult gameResult) {
        OwnedMoney bet = getOwnedMoney(gameResult);
        Money prize = gameResult.calculatePrize(bet);
        return new OwnedMoney(gameResult.getOwner(), prize);
    }

    private OwnedMoney getOwnedMoney(PlayerResult playerResult) {
        return bets.stream()
                .filter(bet -> bet.ownedSameOwner(playerResult))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자 소유의 금액이 없습니다."));
    }
}
