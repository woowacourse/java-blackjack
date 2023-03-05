package domain.people;

import java.util.ArrayList;
import java.util.List;

import view.ErrorMessage;

public class Participants {
    private final List<Participant> participants;

    private Participants(List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants from(List<String> names) {
        List<Participant> participants = new ArrayList<>();
        participants.add(new Dealer());

        for (String name : names) {
            participants.add(new Player(name));
        }
        return new Participants(participants);
    }

    public Dealer findDealer() {
        Participant dealer = participants.stream()
            .filter(participant -> participant.equals(new Dealer()))
            .findFirst()
            .orElseThrow(() -> new IllegalStateException(ErrorMessage.NO_DEALER.getMessage())
            );
        return (Dealer)dealer;
    }

    public List<Player> findPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        for (Participant participant : participants) {
            addParticipantIfPlayer(players, participant);
        }
        return players;
    }

    private void addParticipantIfPlayer(ArrayList<Player> players, Participant participant) {
        if (!participant.equals(new Dealer())) {
            players.add((Player)participant);
        }
    }

    // public Map<Participant, String> getScores() {
    //     Map<Participant, String> scores = new LinkedHashMap<>();
    //     for (Participant participant : participants) {
    //         judgeBust(scores, participant);
    //     }
    //
    //     return scores;
    // }
    //
    // private void judgeBust(Map<Participant, String> scores, Participant participant) {
    //     int handValue = participant.getHandValue();
    //     if (handValue > BUST_BOUNDARY_VALUE) {
    //         scores.put(participant, BUST);
    //         return;
    //     }
    //     scores.put(participant, String.valueOf(handValue));
    // }
    //
    // public Map<Result, Integer> getDealerResults(Map<String, Result> results) {
    //     EnumMap<Result, Integer> result = new EnumMap<>(Result.class);
    //
    //     for (Result playerResult : results.values()) {
    //         judgeResult(result, playerResult);
    //     }
    //     return result;
    // }
    //
    // private void judgeResult(EnumMap<Result, Integer> result, Result playerResult) {
    //     if (playerResult.equals(Result.WIN)) {
    //         result.put(Result.LOSE, result.getOrDefault(Result.LOSE, 0) + 1);
    //     }
    //     if (playerResult.equals(Result.TIE)) {
    //         result.put(Result.TIE, result.getOrDefault(Result.TIE, 0) + 1);
    //     }
    //     if (playerResult.equals(Result.LOSE)) {
    //         result.put(Result.WIN, result.getOrDefault(Result.WIN, 0) + 1);
    //     }
    // }
    //
    // public Map<String, Result> getPlayerResults() {
    //     Dealer dealer = findDealer();
    //     Map<String, Result> playerResults = new LinkedHashMap<>();
    //     for (Player player : findPlayers()) {
    //         compareHandValue(dealer, playerResults, player);
    //     }
    //
    //     return playerResults;
    // }
    //
    // private void compareHandValue(Dealer dealer, Map<String, Result> playerResults, Player player) {
    //     int dealerHandValue = getParticipantHandValue(dealer);
    //     int playerHandValue = getParticipantHandValue(player);
    //
    //     if (playerHandValue != dealerHandValue) {
    //         Result result = Result.compareHandValue(playerHandValue, dealerHandValue);
    //         playerResults.put(player.getName(), result);
    //         return;
    //     }
    //     compareAtTieValue(dealer, playerResults, player, playerHandValue);
    // }
    //
    // private void compareAtTieValue(Dealer dealer, Map<String, Result> playerResults, Player player,
    //     int playerHandValue) {
    //     if (playerHandValue == BUST_HAND_VALUE) {
    //         playerResults.put(player.getName(), Result.TIE);
    //         return;
    //     }
    //     compareHandCount(dealer, playerResults, player);
    // }
    //
    // private int getParticipantHandValue(Participant participant) {
    //     int participantHandValue = participant.getHandValue();
    //     if (participantHandValue > BUST_BOUNDARY_VALUE) {
    //         participantHandValue = BUST_HAND_VALUE;
    //     }
    //     return participantHandValue;
    // }
    //
    // private void compareHandCount(Dealer dealer, Map<String, Result> playerResults, Player player) {
    //     int playerHandCount = player.getCardNames().size();
    //     int dealerHandCount = dealer.getCardNames().size();
    //     if (playerHandCount != dealerHandCount) {
    //         Result result = Result.compareHandCount(playerHandCount, dealerHandCount);
    //         playerResults.put(player.getName(), result);
    //         return;
    //     }
    //     playerResults.put(player.getName(), Result.TIE);
    // }

    public List<Participant> getParticipants() {
        return participants;
    }
}
