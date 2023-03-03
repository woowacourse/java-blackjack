package domain;

import domain.user.Dealer;
import domain.user.Participant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

public class Participants {

    private final Map<Participant, GameStatus> participantStatuses = new LinkedHashMap<>();

    private Participants(List<Participant> participants) {
        initializeParticipantStatues(participants);
    }

    private void initializeParticipantStatues(List<Participant> participants) {
        participants.forEach(
            (participant) -> participantStatuses.put(participant, new GameStatus(ParticipantStatus.NOT_BUST, 0)));
        participantStatuses.put(new Dealer(), new GameStatus(ParticipantStatus.NOT_BUST, 0));
    }

    public static Participants of(String participantNames) {
        String[] split = participantNames.split(",");
        return new Participants(Arrays.stream(split).map(Participant::new).collect(Collectors.toList()));
    }

    public void update(Participant participant) {
        int score = participant.calculateScore();
        ParticipantStatus statusByScore = getStatusByScore(score);
        if (participant.hasAce() && score == 11) {
            participantStatuses.put(participant, new GameStatus(ParticipantStatus.BLACK_JACK, 21));
            return;
        }
        GameStatus prevGameStatus = participantStatuses.get(participant);
        GameStatus newGameStatus = new GameStatus(statusByScore, score);
        if (prevGameStatus.equals(newGameStatus)) {
            newGameStatus = new GameStatus(ParticipantStatus.STAND, score);
        }
        participantStatuses.put(participant, newGameStatus);
    }

    public GameStatus getGameStatusByParticipant(Participant participant) {
        return participantStatuses.get(participant);
    }

    public ParticipantStatus getStatusByScore(int score) {
        if (score > 21) {
            return ParticipantStatus.BUST;
        }
        if (score == 21) {
            return ParticipantStatus.BLACK_JACK;
        }
        return ParticipantStatus.NOT_BUST;
    }

    public List<Participant> getAllParticipantsDealerInLastIndex() {
        return new ArrayList<>(participantStatuses.keySet());
    }

    public Participant getCurrentParticipant() {
        Optional<Entry<Participant, GameStatus>> currentParticipantEntry = participantStatuses.entrySet()
            .stream()
            .filter((entry) -> entry.getValue().isAbleToHit())
            .findFirst();
        if (currentParticipantEntry.isEmpty()) {
            throw new IllegalStateException();
        }
        return currentParticipantEntry.get().getKey();
    }

    public GameResult compareWithDealer(Participant participant) {
        GameStatus dealerGameStatus = participantStatuses.get(new Dealer());
        GameStatus playerGameStatus = participantStatuses.get(participant);
        return GameResult.from(playerGameStatus.compareTo(dealerGameStatus));
    }
}
