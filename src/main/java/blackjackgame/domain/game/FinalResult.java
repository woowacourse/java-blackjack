package blackjackgame.domain.game;

import blackjackgame.domain.user.Dealer;
import blackjackgame.domain.user.Player;
import blackjackgame.domain.user.Players;
import blackjackgame.domain.user.User;
import blackjackgame.domain.user.dto.ProfitDto;
import java.util.LinkedHashMap;
import java.util.Map;

public class FinalResult {
    private final Players players;
    private final Dealer dealer;

    public FinalResult(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public Map<User, ProfitDto> getBetResultByPlayer() {
        Map<User, ProfitDto> betByPlayer = new LinkedHashMap<>();
        betByPlayer.put(dealer, new ProfitDto(calculateDealerProfit()));
        for (Player player : players.getPlayers()) {
            betByPlayer.put(player, new ProfitDto(player.getProfit()));
        }
        return betByPlayer;
    }

    private int calculateDealerProfit() {
        int dealerProfit = 0;
        for (Player player : players.getPlayers()) {
            dealerProfit -= player.getProfit();
        }
        return dealerProfit;
    }
}
