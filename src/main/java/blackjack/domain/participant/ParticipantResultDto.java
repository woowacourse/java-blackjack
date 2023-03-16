package blackjack.domain.participant;

import blackjack.domain.card.Card;
import java.util.List;

public class ParticipantResultDto {
    private final Name name;
    private final List<Card> cards;
    private final int score;

    private ParticipantResultDto(Name name, List<Card> cards, int score) {
        this.name = name;
        this.cards = cards;
        this.score = score;
    }

    public static ParticipantResultDto from(Participant participant) {
        return new ParticipantResultDto(participant.getName(), participant.showCards(),
                participant.calculateScore().getValue());
    }

    public Name getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }
}
