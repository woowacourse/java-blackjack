package domain;

import view.ErrorMessage;

import java.util.*;
import java.util.stream.Collectors;

public class Participants {
    private static final int BUST_HAND_VALUE = 0;
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

    public void deal(Deck deck) {
        for (Participant participant : participants) {
            participant.receiveCard(deck.draw());
        }
    }

    public Participant findDealer() {
        return participants.stream()
            .filter(participant -> participant.getClass().equals(Dealer.class))
            .findFirst()
            .orElseThrow(() -> new IllegalStateException(ErrorMessage.NO_DEALER.getMessage())
            );
    }

    public List<Participant> findPlayers() {
        ArrayList<Participant> players = new ArrayList<>();
        for (Participant participant : participants) {
            addParticipantIfPlayer(players, participant);
        }
        return players;
    }

    private void addParticipantIfPlayer(ArrayList<Participant> players, Participant participant) {
        if (participant.getClass().equals(Player.class)) {
            players.add(participant);
        }
    }

    public Map<Participant, Boolean> getIsBust() {
        Map<Participant, Boolean> scores = new LinkedHashMap<>();
        for (Participant participant : participants) {
            scores.put(participant, participant.isBust());
        }

        return scores;
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

    public Map<String, Result> getPlayerResults() {
        Participant dealer = findDealer();
        Map<String, Result> playerResults = new LinkedHashMap<>();
        for (Participant player : findPlayers()) {
            compareHandValue(dealer, playerResults, player);
        }

        return playerResults;
    }

    private void compareHandValue(Participant dealer, Map<String, Result> playerResults, Participant player) {
        int dealerHandValue = getParticipantHandValue(dealer);
        int playerHandValue = getParticipantHandValue(player);

        if (playerHandValue != dealerHandValue) {
            playerResults.put(player.getName(), Result.isHigherPlayerHandValue(playerHandValue, dealerHandValue));
            return;
        }
        compareAtTieValue(dealer, playerResults, player, playerHandValue);
    }

    private void compareAtTieValue(Participant dealer, Map<String, Result> playerResults, Participant player, int playerHandValue) {
        if (playerHandValue == BUST_HAND_VALUE) {
            playerResults.put(player.getName(), Result.TIE);
            return;
        }
        playerResults.put(player.getName(), compareHandCount(dealer, player));
    }

    private int getParticipantHandValue(Participant participant) {
        if (participant.isBust()) {
            return 0;
        }
        return participant.getHandValue();
    }

    private Result compareHandCount(Participant dealer, Participant player) {
        int playerHandCount = player.getCardNames().size();
        int dealerHandCount = dealer.getCardNames().size();
        return Result.isGreaterPlayerHandCount(playerHandCount, dealerHandCount);
    }

    public List<String> getPlayersName() {
        return findPlayers().stream()
                .map(Participant::getName)
                .collect(Collectors.toList());
    }
}
