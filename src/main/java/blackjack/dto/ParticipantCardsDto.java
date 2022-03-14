package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.game.Score;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import java.util.Objects;
import java.util.Set;

public class ParticipantCardsDto {

    private final String name;
    private final CardsDto cardsDto;

    private ParticipantCardsDto(String name, Set<Card> cards, Score score) {
        this.name = name;
        this.cardsDto = new CardsDto(cards, score);
    }

    public static ParticipantCardsDto of(Participant participant) {
        String name = participant.getName();
        Set<Card> cards = participant.getCards();
        Score score = participant.getCurrentScore();

        return new ParticipantCardsDto(name, cards, score);
    }

    public static ParticipantCardsDto ofInitialDealer(Dealer dealer) {
        String name = dealer.getName();
        Set<Card> openCardInfo = dealer.getInitialOpenCards();
        Score score = dealer.getCurrentScore();

        return new ParticipantCardsDto(name, openCardInfo, score);
    }

    public boolean isPlayer() {
        return !Objects.equals(name, Dealer.UNIQUE_NAME);
    }

    public String getName() {
        return name;
    }

    public Set<Card> getCards() {
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
