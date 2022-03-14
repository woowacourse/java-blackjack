package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.game.Score;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import java.util.List;
import java.util.Objects;

public class ParticipantCardsDto {

    private final String name;
    private final CardsDto cardsDto;

    private ParticipantCardsDto(String name, List<Card> cards, Score score) {
        this.name = name;
        this.cardsDto = new CardsDto(cards, score);
    }

    public static ParticipantCardsDto of(Participant participant) {
        String name = participant.getName();
        List<Card> cards = participant.getCards();
        Score score = participant.getCurrentScore();

        return new ParticipantCardsDto(name, cards, score);
    }

    public static ParticipantCardsDto ofInitial(Participant participant) {
        String name = participant.getName();
        List<Card> openCardInfo = participant.getInitialOpenCards();
        Score score = participant.getCurrentScore();

        return new ParticipantCardsDto(name, openCardInfo, score);
    }

    public boolean isPlayer() {
        return !Objects.equals(name, Dealer.UNIQUE_NAME);
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cardsDto.getCards();
    }

    public Score getScore() {
        return cardsDto.getScore();
    }

    @Override
    public String toString() {
        return "ParticipantCardsDto{" +
                "name='" + name + '\'' +
                ", cardsDto=" + cardsDto +
                '}';
    }
}
