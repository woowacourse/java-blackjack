package domain;

import view.ErrorMessage;

import java.util.*;
import java.util.stream.Collectors;

public class Participants {
    private static final int MINIMUM_PLAYER_COUNT = 1;
    private static final int MAXIMUM_PLAYER_COUNT = 7;
    private static final int BUST_HAND_VALUE = 0;
    private final List<Participant> participants;

    private Participants(List<Participant> participants) {
        this.participants = participants;
    }

    public static Participants from(List<String> names) {
        validate(names);
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

    public boolean shouldDealerHit() {
        final Dealer dealer = (Dealer) findDealer();
        return dealer.shouldHit();
    }

    public Map<Participant, Integer> makeParticipantFinalHandValue() {
        final LinkedHashMap<Participant, Integer> participantsHandValue = new LinkedHashMap<>();
        for (Participant participant : participants) {
            participantsHandValue.put(participant, participant.getHandValue());
        }
        return participantsHandValue;
    }

    public Map<Participant, Boolean> getParticipantsBustStatus() {
        Map<Participant, Boolean> scores = new LinkedHashMap<>();
        for (Participant participant : participants) {
            scores.put(participant, participant.isBust());
        }

        return scores;
    }

    public Map<Result, Integer> getDealerStatus(Map<String, Result> results) {
        EnumMap<Result, Integer> DealerWinningStatus = new EnumMap<>(Result.class);

        for (Result playerResult : results.values()) {
            judgeResult(DealerWinningStatus, playerResult);
        }
        return DealerWinningStatus;
    }

    public Map<String, Result> getPlayerStatus() {
        Participant dealer = findDealer();
        Map<String, Result> playerResults = new LinkedHashMap<>();
        for (Participant player : findPlayers()) {
            compareHandValue(dealer, playerResults, player);
        }

        return playerResults;
    }

    private static void validate(List<String> names) {
        validateNumberOfNames(names);
        validateNoDuplication(names);
    }

    private static void validateNumberOfNames(List<String> names) {
        if (names.size() < MINIMUM_PLAYER_COUNT || names.size() > MAXIMUM_PLAYER_COUNT) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_NUMBER_OF_PLAYER.getMessage());
        }
    }

    private static void validateNoDuplication(List<String> names) {
        if (names.stream().distinct().count() != names.size()) {
            throw new IllegalArgumentException(ErrorMessage.NAME_IS_DUPLICATED.getMessage());
        }
    }

    private void addParticipantIfPlayer(ArrayList<Participant> players, Participant participant) {
        if (participant.getClass().equals(Player.class)) {
            players.add(participant);
        }
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

    private void compareHandValue(Participant dealer, Map<String, Result> playerResults, Participant player) {
        int dealerHandValue = getParticipantHandValue(dealer);
        int playerHandValue = getParticipantHandValue(player);

        if (playerHandValue != dealerHandValue) {
            playerResults.put(player.getName(), Result.isHigherPlayerHandValue(playerHandValue, dealerHandValue));
            return;
        }
        compareAtTieValue(dealer, playerResults, player, playerHandValue);
    }

    private int getParticipantHandValue(Participant participant) {
        if (participant.isBust()) {
            return 0;
        }
        return participant.getHandValue();
    }

    private void compareAtTieValue(Participant dealer, Map<String, Result> playerResults, Participant player, int playerHandValue) {
        if (playerHandValue == BUST_HAND_VALUE) {
            playerResults.put(player.getName(), Result.TIE);
            return;
        }
        playerResults.put(player.getName(), compareHandCount(dealer, player));
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
