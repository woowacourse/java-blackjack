package blackjack.dto;

import blackjack.model.participant.Participant;
import blackjack.model.participant.Participants;

import java.util.ArrayList;
import java.util.List;

public class FinishedParticipantDto {

    private final String name;
    private final List<String> cards;
    private final String score;
    private final boolean isBlackjack;

    public FinishedParticipantDto(String name, List<String> cards, String score, boolean isBlackjack) {
        this.name = name;
        this.cards = cards;
        this.score = score;
        this.isBlackjack = isBlackjack;
    }

    public static FinishedParticipantDto from(Participant participant) {
        ParticipantDto participantDto = ParticipantDto.from(participant);
        String score = Integer.toString(participant.getScore());
        boolean isBlackjack = participant.isBlackjack();
        return new FinishedParticipantDto(participantDto.getName(), participantDto.getCards(), score, isBlackjack);
    }

    public static List<FinishedParticipantDto> of(List<Participant> participants) {
        List<FinishedParticipantDto> finishedParticipantsDto = new ArrayList<>();
        for (Participant participant : participants) {
            finishedParticipantsDto.add(from(participant));
        }
        return finishedParticipantsDto;
    }

    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return cards;
    }

    public String getScore() {
        return score;
    }

    public boolean isBlackjack() {
        return isBlackjack;
    }
}
