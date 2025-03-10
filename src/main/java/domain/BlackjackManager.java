package domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackjackManager {
    private final Players players;
    private final Deck deck;

    public BlackjackManager(Players players, Deck deck) {
        this.players = players;
        this.deck = deck;
    }

    public void distributeInitialCards() {
        players.distributeInitialCards(deck);
    }

    public void openInitialCards() {
        players.openInitialCards();
    }

    public void addOneCard(Player player) {
        player.drawOneCard(deck);
    }

    public boolean addCardToDealerIfLowScore() {
        return getDealer().drawOneCardIfLowScore(deck);
    }

    public Map<Player, Integer> computePlayerSum() {
        Map<Player, Integer> results = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            results.put(player, player.computeOptimalSum());
        }
        return results;
    }

    public Map<MatchResult, Integer> computeDealerMatchResultCount(
            Map<Player, MatchResult> participantNameAndMatchResult) {
        Map<MatchResult, Integer> matchResultCount = new LinkedHashMap<>();
        MatchResult.sortedValues().forEach(matchResult -> matchResultCount.put(matchResult, 0));

        participantNameAndMatchResult.forEach((key, value) -> matchResultCount.put(MatchResult.inverse(value),
                matchResultCount.getOrDefault(MatchResult.inverse(value), 0) + 1));
        return matchResultCount;
    }

    public Map<Player, MatchResult> computeParticipantsMatchResult(Dealer dealer,
                                                                   List<Player> participants) {
        Map<Player, MatchResult> results = new LinkedHashMap<>();
        for (Player participant : participants) {
            MatchResult matchResult = computeParticipantMatchResult(dealer, participant);
            results.put(participant, matchResult);
        }
        return results;
    }

    private MatchResult computeParticipantMatchResult(Dealer dealer, Player participant) {
        if (participant.isBust()) {
            return MatchResult.LOSE;
        }
        if (dealer.isBust()) {
            return MatchResult.WIN;
        }
        return MatchResult.compareBySum(participant.computeOptimalSum(),
                dealer.computeOptimalSum());
    }

    public String getDealerName() {
        return getDealer().getName();
    }

    public Dealer getDealer() {
        return players.getDealer();
    }

    public List<Player> getParticipants() {
        return players.getParticipants();
    }
}
