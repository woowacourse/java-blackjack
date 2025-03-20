package model.participant;

import model.bating.BatingManager;
import model.bating.Money;
import model.score.MatchResult;

import java.util.*;

public class Players {

    private final Map<Player, Money> values;
    private static final int PLAYER_MAX_NUMBER = 30;

    public Players(Map<Player, Money> values) {
        validateNumber(values);
        this.values = values;
    }

    private void validateNumber(Map<Player, Money> values) {
        if (values.isEmpty() || values.size() > PLAYER_MAX_NUMBER) {
            throw new IllegalArgumentException("게임 참가자는 1~30명까지 가능합니다.");
        }
    }

    public Map<Player, Integer> calculateEarnings(Dealer dealer) {
        Map<Player, Integer> earnings = new HashMap<>();
        for (final Player player : values.keySet()) {
            MatchResult matchResult = player.compareToScore(dealer);
            Money money = values.get(player);
            int benefit = BatingManager.batingBenefit(matchResult, money);
            earnings.put(player, benefit);
        }
        return earnings;
    }

    public Map<Player, Money> getPlayers() {
        return values;
    }

    public List<Player> getAllPlayer() {
        Set<Player> players = values.keySet();
        return new ArrayList<>(players);
    }

    public List<String> getNicknames() {
        return values.keySet().stream()
                .map(Participant::getNickname)
                .toList();
    }
}
