package domain;

import domain.dto.DealerResult;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Blackjack {
    private final Players players;
    private final Deck deck;

    public Blackjack(Players players, Deck deck) {
        this.players = players;
        this.deck = deck;
    }

    public void distributeInitialCards() {
        players.distributeInitialCards(deck);
    }

    public void addOneCard(Player player) {
        player.drawOneCard(deck);
    }

    public boolean addCardToDealerIfLowScore() {
        return getDealer().drawOneCardIfLowScore(deck);
    }

    public DealerResult computeDealerMatchResult() {
        Map<String, MatchResult> participantNameAndMatchResult = computeParticipantsMatchResult();
        Map<MatchResult, Integer> matchResultCount = new LinkedHashMap<>();
        MatchResult.sortedValues().forEach(matchResult -> matchResultCount.put(matchResult, 0));

        participantNameAndMatchResult.forEach((key, value) -> matchResultCount.put(MatchResult.inverse(value),
                matchResultCount.getOrDefault(MatchResult.inverse(value), 0) + 1));

        return new DealerResult(getDealer().getName(), matchResultCount);
    }

    public Map<String, MatchResult> computeParticipantsMatchResult() {
        Map<String, MatchResult> participantNameAndMatchResult = new LinkedHashMap<>();
        Player dealer = getDealer();
        List<Player> participants = getParticipants();

        for (Player participant : participants) {
            MatchResult matchResult = computeParticipantMatchResult(dealer, participant);
            participantNameAndMatchResult.put(participant.getName(), matchResult);
        }
        return participantNameAndMatchResult;
    }

    private MatchResult computeParticipantMatchResult(Player dealer, Player participant) {
        if (participant.isBust() && dealer.isBust() || participant.isBust()) {
            return MatchResult.LOSE;
        }
        if (dealer.isBust()) {
            return MatchResult.WIN;
        }
        return MatchResult.compareBySum(participant.computeOptimalSum(),
                dealer.computeOptimalSum());
    }

    public Map<String, Integer> getNameAndSumOfAllPlayers() {
        return players.mapToNameAndSum();
    }

    public Dealer getDealer() {
        return players.getDealer();
    }

    public List<Player> getParticipants() {
        return players.getParticipants();
    }
}
