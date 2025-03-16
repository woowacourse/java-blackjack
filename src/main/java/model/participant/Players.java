package model.participant;

import model.Money;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Players {

    private final List<Player> values;

    private Players(List<Player> values) {
        validateNumber(values);
        validateDuplication(values);
        this.values = values;
    }

    public static Players from(Map<Nickname, Money> batingMoneys) {
        List<Player> players = new ArrayList<>();
        for (Nickname nickname : batingMoneys.keySet()) {
            Money batingMoney = batingMoneys.get(nickname);
            Player player = new Player(nickname, batingMoney);
            players.add(player);
        }
        return new Players(players);
    }

    private void validateDuplication(List<Player> values) {
        if (values.stream().distinct().count() != values.size()) {
            throw new IllegalArgumentException("중복된 플레이어는 등록할 수 없습니다.");
        }
    }

    private void validateNumber(List<Player> values) {
        if (values.isEmpty() || values.size() > 30) {
            throw new IllegalArgumentException("게임 참가자는 1~30명까지 가능합니다.");
        }
    }

    public List<Player> getPlayers() {
        return values;
    }

    public List<String> getNicknames() {
        return values.stream()
                .map(Participant::getNickname)
                .toList();
    }

    public HashMap<Player, Integer> getMatchResult(Dealer dealer) {
        HashMap<Player, Integer> batingResults = new HashMap<>();
        for (Player player : getPlayers()) {
            int earnings = player.calculateEarnings(dealer);
            batingResults.put(player, earnings);
        }
        return batingResults;
    }
}
