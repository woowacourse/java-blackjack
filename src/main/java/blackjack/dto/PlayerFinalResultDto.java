package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;
import java.util.List;

public class PlayerFinalResultDto {

    private final String name;
    private final List<Card> cards;
    private final int score;

    private PlayerFinalResultDto(final String name, final List<Card> cards, final int score) {
        this.name = name;
        this.cards = cards;
        this.score = score;
    }

    public static PlayerFinalResultDto from(final Participant participant) {
        return new PlayerFinalResultDto(participant.getName(), participant.getCards(), participant.calculateScore());
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }
}
