package blackjack.view.dto;

import blackjack.domain.participant.state.Score;
import java.util.List;

public class ParticipantDto {

    public static final String DEALER_NAME = "딜러";

    private final String name;
    private final List<CardDto> cards;
    private final int score;

    public ParticipantDto(final List<CardDto> cards, final int score) {
        this(DEALER_NAME, cards, score);
    }

    public ParticipantDto(final String name, final List<CardDto> cards, final int score) {
        this.name = name;
        this.cards = cards;
        this.score = score;
    }

    public String getName() {
        return this.name;
    }

    public List<CardDto> getCards() {
        return this.cards;
    }

    public int getScore() {
        return this.score;
    }
}
