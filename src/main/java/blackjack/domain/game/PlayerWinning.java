package blackjack.domain.game;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;

public final class PlayerWinning {

    private final String nickname;
    private final WinningType winningType;

    public PlayerWinning(Dealer dealer, Player player) {
        this.nickname = player.getNickname();
        this.winningType = WinningType.parse(player.getPoint(), dealer.getPoint());
    }

    public String getNickname() {
        return nickname;
    }

    public WinningType getWinningType() {
        return winningType;
    }
}
