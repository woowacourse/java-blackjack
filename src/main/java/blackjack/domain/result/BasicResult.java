package blackjack.domain.result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Participants;
import blackjack.domain.participants.Player;
import blackjack.domain.rule.BasicRule;

public class BasicResult {
    private Map<BasicRule, Integer> dealerResult = new HashMap<>();
    private Map<Participant, BasicRule> playerResults = new HashMap<>();

    private void set(Player player, BasicRule basicRule) {
        dealerResult.put(basicRule, dealerResult.getOrDefault(basicRule, 0) + 1);
        playerResults.put(player, basicRule);
    }

    public void judge(Participants participants) {
        Dealer dealer = participants.getDealer();
        List<Player> players = participants.getPlayers();
        for (Player player : players) {
            set(player, BasicRule.getResultOfPlayer(dealer, player));
        }
    }

    public Map<BasicRule, Integer> getDealerResult() {
        return dealerResult;
    }

    public Map<Participant, BasicRule> getPlayerResults() {
        return playerResults;
    }
}
