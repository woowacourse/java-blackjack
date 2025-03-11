package domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Blackjack {
    private final Players players;
    private final Deck deck;

    public Blackjack(Players players, Deck deck) {
        this.players = players;
        this.deck = deck;
    }

    public Blackjack(List<String> names, Deck deck) {
        this.players = createPlayers(new Dealer(), createParticipants(names));
        this.deck = deck;
    }

    private Players createPlayers(Player dealer, List<Player> participants) {
        List<Player> players = new ArrayList<>();
        players.add(dealer);
        players.addAll(participants);
        return new Players(players);
    }

    private List<Player> createParticipants(List<String> names) {
        return names.stream()
                .map(Participant::new)
                .collect(Collectors.toList());
    }

    public void distributeInitialCards() {
        players.distributeInitialCards(deck);
    }

    public List<Card> openDealerCards() {
        Player dealer = getDealer();
        return dealer.openInitialCards();
    }

    public List<Card> openParticipantsCards(Player player) {
        return player.openInitialCards();
    }

    public void addCard(Player player) {
        player.drawOneCard(deck);
    }

    public boolean addCardToDealerIfLowScore() {
        return getDealer().drawOneCardIfLowScore(deck);
    }

    public Map<String, Integer> getNameAndSumOfAllPlayers() {
        return players.mapToNameAndSum();
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }

    public Dealer getDealer() {
        return players.getDealer();
    }

    public List<Player> getParticipants() {
        return players.getParticipants()
                .stream()
                .toList();
    }

    public Map<MatchResult, Integer> computeDealerMatchResult() {
        Map<String, MatchResult> participantNameAndMatchResult = computeParticipantsMatchResult();
        Map<MatchResult, Integer> matchResultCount = new LinkedHashMap<>();
        MatchResult.sortedValues().forEach(matchResult -> matchResultCount.put(matchResult, 0));
        
        participantNameAndMatchResult.forEach((key, value) -> matchResultCount.put(MatchResult.inverse(value),
                matchResultCount.getOrDefault(MatchResult.inverse(value), 0) + 1));

        return matchResultCount;
    }

    public Map<String, MatchResult> computeParticipantsMatchResult() {
        Map<String, MatchResult> participantNameAndMatchResult = new LinkedHashMap<>();
        Player dealer = getDealer();
        List<Player> participants = getParticipants();

        for (Player participant : participants) {
            MatchResult matchResult = MatchResult.calculateParticipantMatchResult(dealer, participant);
            participantNameAndMatchResult.put(participant.getName(), matchResult);
        }
        return participantNameAndMatchResult;
    }

}
