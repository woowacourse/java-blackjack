package domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Participants {
    private final List<Participant> participants;

    private Participants(List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants from(List<String> names) {
        List<Participant> participants = new ArrayList<>();
        participants.add(new Dealer());
        names.forEach(name -> participants.add(new Player(name)));
        return new Participants(participants);
    }

    private static void compareHandValue(Dealer dealer, Map<String, Result> playerResults,
        Player player) {
        int dealerHandValue = dealer.getHandValue();
        if (dealerHandValue > 21) {
            dealerHandValue = 0;
        }

        int playerHandValue = player.getHandValue();
        if (playerHandValue > 21) {
            playerHandValue = 0;
        }
        if (playerHandValue > dealerHandValue) {
            playerResults.put(player.getName(), Result.WIN);
        }
        if (playerHandValue < dealerHandValue) {
            playerResults.put(player.getName(), Result.LOSE);
        }
        if (playerHandValue == dealerHandValue) {
            compareHandCount(dealer, playerResults, player);
        }
    }

    private static void compareHandCount(Dealer dealer, Map<String, Result> playerResults, Player player) {
        if (player.getCardNames().size() > dealer.getCardNames().size()) {
            playerResults.put(player.getName(), Result.LOSE);
        }
        if (player.getCardNames().size() < dealer.getCardNames().size()) {
            playerResults.put(player.getName(), Result.WIN);
        }
        if (player.getCardNames().size() == dealer.getCardNames().size()) {
            playerResults.put(player.getName(), Result.TIE);
        }
    }

    public void deal(Deck deck) {
        for (Participant participant : participants) {
            participant.receiveCard(deck.draw());
        }
    }

    public List<Participant> getParticipants() {
        return new ArrayList<>(participants);
    }

    public List<Player> findPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        for (Participant participant : participants) {
            addParticipantIfPlayer(players, participant);
        }
        return players;
    }

    public Dealer findDealer() {
        Participant dealer = participants.stream()
            .filter(participant -> participant.getClass().equals(Dealer.class))
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("딜러가 존재하지 않습니다.")
            );
        return (Dealer)dealer;
    }

    private void addParticipantIfPlayer(ArrayList<Player> players, Participant participant) {
        if (participant.getClass().equals(Player.class)) {
            players.add((Player)participant);
        }
    }

    public Map<Participant, String> getScores() {
        Map<Participant, String> scores = new LinkedHashMap<>();
        for (Participant participant : participants) {
            if (participant.getHandValue() > 21) {
                scores.put(participant, "버스트");
                continue;
            }
            scores.put(participant, String.valueOf(participant.getHandValue()));
        }

        return scores;
    }

    public Map<String, Result> getPlayerResults() {
        Dealer dealer = findDealer();
        Map<String, Result> playerResults = new LinkedHashMap<>();
        for (Player player : findPlayers()) {
            compareHandValue(dealer, playerResults, player);
        }

        return playerResults;
    }

    public Map<Result, Integer> getDealerResults(Map<String, Result> results) {
        int dealerWinCount = 0;
        int dealerTieCount = 0;
        int dealerLoseCount = 0;

        for (Result playerResult : results.values()) {
            if (playerResult.equals(Result.WIN)) {
                dealerLoseCount += 1;
            }
            if (playerResult.equals(Result.TIE)) {
                dealerTieCount += 1;
            }
            if (playerResult.equals(Result.LOSE)) {
                dealerWinCount += 1;
            }
        }
        LinkedHashMap<Result, Integer> dealerResults = new LinkedHashMap<>();
        dealerResults.put(Result.WIN, dealerWinCount);
        dealerResults.put(Result.LOSE, dealerLoseCount);
        dealerResults.put(Result.TIE, dealerTieCount);
        return dealerResults;
    }
}
