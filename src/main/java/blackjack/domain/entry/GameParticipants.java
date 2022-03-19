package blackjack.domain.entry;

import blackjack.domain.result.PlayerOutcome;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameParticipants {
    private final Dealer dealer;
    private final Players players;

    public GameParticipants(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
    }

    public Map<Participant, Integer> calculateBettingResult() {
        Map<Participant, Integer> bettingResult = getBettingResult();
        bettingResult.put(dealer, dealer.calculateBettingMoney(bettingResult));

        return bettingResult;
    }

    private Map<PlayerOutcome, List<Player>> getGameResult() {
        return players.match(dealer);
    }

    private Map<Participant, Integer> getBettingResult() {
        return getGameResult().entrySet().stream()
                .map(entry -> entry.getValue()
                        .stream()
                        .collect(Collectors.toMap(player -> player, player -> player.calculateBettingMoney(entry.getKey())))
                ).flatMap(m -> m.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public List<Participant> getParticipant() {
        List<Participant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players.getPlayers());
        return participants;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Participant find(String name) {
        if (name.equals("딜러")) {
            return dealer;
        }
        return players.getPlayers().stream()
                .filter(player -> name.equals(player.getName()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게임 참여자 이름입니다."));
    }
}
