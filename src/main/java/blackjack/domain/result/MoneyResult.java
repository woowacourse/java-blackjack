package blackjack.domain.result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Money;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Participants;
import blackjack.domain.participants.Player;
import blackjack.domain.rule.BasicRule;
import blackjack.domain.rule.MoneyRule;

public class MoneyResult implements Result {
    private Money dealerMoney;
    private Map<Participant, Money> playersMoney;

    public MoneyResult() {
        this.dealerMoney = new Money(0);
        this.playersMoney = new HashMap<>();
    }

    public void initPlayerMoney(Player player, String money) {
        playersMoney.put(player, new Money(money));
    }

    private Money applyMoney(Money bettingMoney, MoneyRule moneyRule) {
        return bettingMoney.multiply(moneyRule.getMultiplyValue());
    }

    @Override
    public void judge(Participants participants) {
        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();
        for (Player player : players) {
            MoneyRule playerMoneyRule = BasicRule.of(dealer, player);
            Money playerBettingMoney = playersMoney.getOrDefault(player, new Money(0));
            Money playerMoney = applyMoney(playerBettingMoney, playerMoneyRule);
            playersMoney.put(player, playerMoney);
            dealerMoney = dealerMoney.subtract(playerMoney);
        }
    }

    public Map<Participant, Money> getPlayersMoney() {
        return this.playersMoney;
    }

    public Money getDealerMoney() {
        return this.dealerMoney;
    }
}
