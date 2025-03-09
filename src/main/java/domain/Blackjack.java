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

    public void addCardByName(String name) {
        Player participant = players.getPlayerByName(name);
        participant.drawOneCard(deck);
    }

    public boolean addCardToDealerIfLowScore() {
        return getDealer().drawOneCardIfLowScore(deck);
    }

    public Map<String, Integer> getNameAndSumOfAllPlayers() {
        return players.mapToNameAndSum();
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
        Dealer dealer = getDealer();
        List<Participant> participants = getParticipants();

        for (Participant participant : participants) {
            MatchResult matchResult = computeParticipantMatchResult(dealer, participant);
            participantNameAndMatchResult.put(participant.getName(), matchResult);
        }
        return participantNameAndMatchResult;
    }

    private MatchResult computeParticipantMatchResult(Dealer dealer, Participant participant) {
        if (participant.isBurst() && dealer.isBurst() || participant.isBurst()) {
            return MatchResult.LOSE;
        }
        if (dealer.isBurst()) {
            return MatchResult.WIN;
        }
        return MatchResult.compareBySum(participant.computeOptimalSum(),
                dealer.computeOptimalSum());
    }
    
    public Dealer getDealer() {
        return players.getDealer();
    }

    public List<Participant> getParticipants() {
        return players.getParticipants()
                .stream()
                .map(player -> (Participant) player)
                .toList();
    }

    public Players getPlayers() {
        return players;
    }
}
