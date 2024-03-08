package dto;

import domain.participant.Participant;
import java.util.List;

public class ParticipantDto {
    private final String name;
    private final List<String> cards;
    private final int totalSum;

    public ParticipantDto(final String name, final List<String> cards, final int totalSum) {
        this.name = name;
        this.cards = cards;
        this.totalSum = totalSum;
    }

    public static ParticipantDto from(final Participant player) {
        return new ParticipantDto(player.getName(), player.getCardNames(), player.handsSum());
    }

    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return cards;
    }

    public int getTotalSum() {
        return totalSum;
    }
}
