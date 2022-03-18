package blackjack.dto2;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import blackjack.domain.card.Score;
import blackjack.domain.participant2.Participant;
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
        CardBundle cardBundle = participant.getHand().getCardBundle();
        List<Card> cards = cardBundle.getCards();
        Score score = cardBundle.getScore();

        return new ParticipantCardsDto(name, cards, score);
    }

    public static ParticipantCardsDto ofInitial(final Participant participant) {
        String name = participant.getName();
        List<Card> openCardInfo = participant.openInitialCards();
        CardBundle cardBundle = participant.getHand().getCardBundle();
        Score score = cardBundle.getScore();

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
