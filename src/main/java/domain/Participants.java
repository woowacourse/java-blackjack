package domain;

import static constant.BlackjackConstant.DEALER_NAME;

import java.util.ArrayList;
import java.util.List;

public class Participants {

    private final List<Participant> participants;

    public Participants(List<Participant> participants) {
        participants.add(new Participant(DEALER_NAME, new HandCards(new ArrayList<>()), true));
        this.participants = participants;
    }

    public Participant getDealer() {
        for (Participant participant : participants) {
            if (participant.isDealer()) {
                return participant;
            }
        }

        throw new IllegalStateException("딜러가 존재하지 않습니다.");
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
}
