package dto;

import domain.participant.Participant;
import java.util.List;
import view.message.RankView;
import view.message.ShapeView;

public record ParticipantDto(String name, List<String> cards, int totalSum) {

    public static ParticipantDto from(final Participant participant) {
        final List<String> cards = participant.getCards().stream()
                .map(card -> RankView.from(card.getRank()) + ShapeView.from(card.getShape()))
                .toList();

        return new ParticipantDto(participant.getName(), cards, participant.handsSum());
    }
}
