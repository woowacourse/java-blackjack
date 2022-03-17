package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;
import blackjack.domain.participant.Participant;
import java.util.List;

public class ParticipantCardsDto {

    private final String name;
    private final CardsDto cardsDto;

    private ParticipantCardsDto(final String name, final List<Card> cards, final Score score) {
        this.name = name;
        this.cardsDto = new CardsDto(cards, score);
    }

    public static ParticipantCardsDto of(final Participant participant) {
        String name = participant.getName();
        List<Card> cards = participant.getCards();
        Score score = participant.getScore();

        return new ParticipantCardsDto(name, cards, score);
    }

    public static ParticipantCardsDto ofInitial(final Participant participant) {
        String name = participant.getName();
        List<Card> openCardInfo = participant.getInitialOpenCards();
        Score score = participant.getScore();

        return new ParticipantCardsDto(name, openCardInfo, score);
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cardsDto.getCards();
    }

    public int getScoreValue() {
        return cardsDto.getScoreValue();
    }

    @Override
    public String toString() {
        return "ParticipantCardsDto{" +
                "name='" + name + '\'' +
                ", cardsDto=" + cardsDto +
                '}';
    }
}
