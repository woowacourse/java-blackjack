package blackjack.domain.result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Participants;
import blackjack.domain.participants.Player;
import blackjack.domain.rule.BasicRule;
import blackjack.domain.rule.MoneyRule;

public class BasicResult implements Result {
    private Map<MoneyRule, Integer> dealerResult;
    private Map<Participant, MoneyRule> playerResults;

    public BasicResult() {
        dealerResult = new HashMap<>();
        playerResults = new HashMap<>();
    }

    private void set(Player player, MoneyRule moneyRule) {
        dealerResult.put(moneyRule, dealerResult.getOrDefault(moneyRule, 0) + 1);
        playerResults.put(player, moneyRule);
    }

    @Override
    public void judge(Participants participants) {
        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();
        for (Player player : players) {
            set(player, BasicRule.of(dealer, player));
        }
    }

    public Map<MoneyRule, Integer> getDealerResult() {
        return dealerResult;
    }

    public Map<Participant, MoneyRule> getPlayerResults() {
        return playerResults;
    }
}
