package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Participant;
import java.util.List;

public class PlayerDto {

    private final String name;
    private final List<Card> cards;

    private PlayerDto(final String name, final List<Card> cards) {
        this.name = name;
        this.cards = cards;
    }

    public static PlayerDto from(final Participant participant) {
        return new PlayerDto(
                participant.getName(),
                participant.getInitCards()
        );
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }
}
