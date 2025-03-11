package blackjack;

import card.Card;
import card.Deck;
import player.Dealer;
import player.Participant;
import player.Player;
import player.Players;
import java.util.ArrayList;
import java.util.EnumMap;
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

    public Map<String, List<Card>> openInitialCards() {
        return players.openInitialCards();
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
        Map<MatchResult, Integer> matchResultCount = new EnumMap<>(MatchResult.class);

        participantNameAndMatchResult.forEach((key, value) -> matchResultCount.put(MatchResult.inverse(value),
                matchResultCount.getOrDefault(MatchResult.inverse(value), 0) + 1));

        return matchResultCount;
    }

    public Map<String, MatchResult> computeParticipantsMatchResult() {
        Map<String, MatchResult> participantNameAndMatchResult = new LinkedHashMap<>();
        Player dealer = getDealer();

        for (Player participant : getParticipants()) {
            MatchResult matchResult = MatchResult.calculateParticipantMatchResult(dealer, participant);
            participantNameAndMatchResult.put(participant.getName(), matchResult);
        }
        return participantNameAndMatchResult;
    }
}
