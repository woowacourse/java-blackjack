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

    public Participants(final List<Participant> participants) {
        validatePlayerCounts(participants);
        // FIXME: 사이드 이펙트가 발생할 수 있다
        participants.add(new Participant(new Name(DEALER_NAME), new HandCards(new ArrayList<>()), true));
        this.participants = participants;
    }

    // FIXME: indent가 2인 부분인데, 어떻게 개선할 수 있을까?
    public Participant getDealer() {
        for (final Participant participant : participants) {
            if (participant.isDealer()) {
                return participant;
            }
        }

        // TODO: IllegalState가 적합한가? 의미상 IllegalArgument가 더 적합한가?
        throw new IllegalStateException(DEALER_NOT_FOUND_ERROR.getMessage());
    }

    public List<Participant> getPlayers() {
        final List<Participant> participants = new ArrayList<>();
        for (final Participant participant : this.participants) {
            if (!participant.isDealer()) {
                participants.add(participant);
            }
        }

        return participants;
    }

    public List<CardResult> getCardResults() {
        final List<CardResult> cardResults = new ArrayList<>();
        for (final Participant participant : participants) {
            cardResults.add(new CardResult(participant.getName(), participant.getHandCards(), participant.getScore()));
        }
        return cardResults;
    }

    // TODO: 검증에 대한 테스트 필요
    private static void validatePlayerCounts(final List<Participant> participants) {
        if (participants.size() < MINIMUM_PLAYER_BOUND || participants.size() > MAXIMUM_PLAYER_BOUND) {
            throw new IllegalStateException(PLAYER_COUNT_OUT_OF_RANGE.getMessage());
        }
    }
}
