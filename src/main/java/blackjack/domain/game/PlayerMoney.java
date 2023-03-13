package blackjack.domain.game;

import blackjack.domain.card.Hand;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Score;

import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerMoney {

    private final Map<Player, Money> playerMoney;

    public PlayerMoney() {
        this.playerMoney = new LinkedHashMap<>();
    }

    public void addPlayerMoney(Player player, Money money) {
        playerMoney.put(player, money);
    }

    public Map<Player, Money> calculateYieldAllPlayer(Hand dealerHand) {
        Map<Player, Money> calculatedPlayerMoney = new LinkedHashMap<>();
        for (Player player : playerMoney.keySet()) {
            Result result = Result.calculateResult(player.getHand(), dealerHand);
            Money money = result.calculateYield(playerMoney.get(player));
            calculatedPlayerMoney.put(player, money);
        }
        return calculatedPlayerMoney;
    }
}
