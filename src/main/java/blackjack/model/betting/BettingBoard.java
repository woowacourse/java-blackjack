package blackjack.model.betting;

import blackjack.model.participant.Player;
import java.util.List;

public class BettingBoard {
    private final List<BettingAccount> bettingAccounts;

    public BettingBoard(final List<BettingAccount> bettingAccounts) {
        this.bettingAccounts = bettingAccounts;
    }

    public BettingAccount findBettingAccountBy(final Player player) {
        return bettingAccounts.stream()
                .filter(bettingAccount -> bettingAccount.isOwnedBy(player))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 플레이어에 해당하는 베팅 정보가 존재하지 않습니다."));
    }
}
