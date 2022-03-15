package blackjack.view.dto;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.participant.Participant;
import java.util.stream.Collectors;

public class ParticipantDto {

    private final String name;
    private final Cards cards;
    private final int score;

    private ParticipantDto(String name, Cards cards, int score) {
        this.name = name;
        this.cards = cards;
        this.score = score;
    }

    public static ParticipantDto of(Participant participant) {
        return new ParticipantDto(participant.getName().getValue(), participant.getCards(),
                participant.calculateBestScore());
    }

    public String showOneCard() {
        return cards.getCardHand().get(0).toString();
    }

    public String showCards() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(
                cards.getCardHand().stream()
                .map(Card::toString)
                .collect(Collectors.joining(", ")));
        return stringBuilder.toString();
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
