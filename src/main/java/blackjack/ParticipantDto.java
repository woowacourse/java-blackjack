package blackjack;

import blackjack.model.player.Participant;
import java.util.List;

public class ParticipantDto {
    private final String name;
    private final List<String> cards;
    private final int score;

    private ParticipantDto(String name, List<String> cards, int score) {
        this.name = name;
        this.cards = cards;
        this.score = score;
    }

    public static ParticipantDto from(Participant participant) {
        return new ParticipantDto(participant.getName(), participant.getCards(), participant.getScore());
    }

    public String getName() {
        return name;
    }

    public List<String> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }
}
