package domain;

import static constant.BlackjackConstant.DEALER_NAME;
import static constant.BlackjackConstant.MAXIMUM_PLAYER_BOUND;
import static constant.BlackjackConstant.MINIMUM_PLAYER_BOUND;
import static exception.ErrorMessage.DEALER_NOT_FOUND_ERROR;
import static exception.ErrorMessage.PLAYER_COUNT_OUT_OF_RANGE;

import java.util.ArrayList;
import java.util.List;

public class Participants {

    private final List<Participant> participants;

    public Participants(List<Participant> participants) {
        validatePlayerCounts(participants);
        participants.add(new Participant(new Name(DEALER_NAME), new HandCards(new ArrayList<>()), true));
        this.participants = participants;
    }

    public Participant getDealer() {
        for (Participant participant : participants) {
            if (participant.isDealer()) {
                return participant;
            }
        }

        throw new IllegalStateException(DEALER_NOT_FOUND_ERROR.getMessage());
    }

    public List<Participant> getPlayers() {
        List<Participant> participants = new ArrayList<>();
        for (Participant participant : this.participants) {
            if (!participant.isDealer()) {
                participants.add(participant);
            }
        }

        return participants;
    }

    public List<CardResult> getCardResults() {
        List<CardResult> cardResults = new ArrayList<>();
        for (Participant participant : participants) {
            cardResults.add(new CardResult(participant.getName(), participant.getHandCards(), participant.getScore()));
        }
        return cardResults;
    }

    private static void validatePlayerCounts(List<Participant> participants) {
        if (participants.size() < MINIMUM_PLAYER_BOUND || participants.size() > MAXIMUM_PLAYER_BOUND) {
            throw new IllegalStateException(PLAYER_COUNT_OUT_OF_RANGE.getMessage());
        }
    }
}