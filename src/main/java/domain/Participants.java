package domain;

import java.util.ArrayList;
import java.util.EnumMap;
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
        int dealerHandValue = getParticapantHandValue(dealer);
        int playerHandValue = getParticapantHandValue(player);

        if (playerHandValue != dealerHandValue) {
            playerResults.put(player.getName(), Result.compareHandValue(playerHandValue, dealerHandValue));
            return;
        }
        if (playerHandValue == 0) {
            playerResults.put(player.getName(), Result.TIE);
            return;
        }
        compareHandCount(dealer, playerResults, player);
    }

    private static int getParticapantHandValue(Participant participant) {
        int participantHandValue = participant.getHandValue();
        if (participantHandValue > 21) {
            participantHandValue = 0;
        }
        return participantHandValue;
    }

    private static void compareHandCount(Dealer dealer, Map<String, Result> playerResults, Player player) {
        int playerHandCount = player.getCardNames().size();
        int dealerHandCount = dealer.getCardNames().size();
        if (playerHandCount != dealerHandCount) {
            playerResults.put(player.getName(), Result.compareHandCount(playerHandCount, dealerHandCount));
            return;
        }
        playerResults.put(player.getName(), Result.TIE);
    }

    public void deal(Deck deck) {
        for (Participant participant : participants) {
            participant.receiveCard(deck.draw());
        }
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
            judgeBust(scores, participant);
        }

        return scores;
    }

    private void judgeBust(Map<Participant, String> scores, Participant participant) {
        if (participant.getHandValue() > 21) {
            scores.put(participant, "버스트");
            return;
        }
        scores.put(participant, String.valueOf(participant.getHandValue()));
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
        EnumMap<Result, Integer> result = new EnumMap<>(Result.class);

        for (Result playerResult : results.values()) {
            judgeResult(result, playerResult);
        }
        return result;
    }

    private void judgeResult(EnumMap<Result, Integer> result, Result playerResult) {
        if (playerResult.equals(Result.WIN)) {
            result.put(Result.LOSE, result.getOrDefault(Result.LOSE, 0) + 1);
        }
        if (playerResult.equals(Result.TIE)) {
            result.put(Result.TIE, result.getOrDefault(Result.TIE, 0) + 1);
        }
        if (playerResult.equals(Result.LOSE)) {
            result.put(Result.WIN, result.getOrDefault(Result.WIN, 0) + 1);
        }
    }
}
