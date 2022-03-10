package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;

import java.util.ArrayList;
import java.util.List;

public class ParticipantDto {

    private final String name;
    private final List<Card> cards;
    private final int score;

    private ParticipantDto(final String name, final List<Card> cards, final int score) {
        this.name = name;
        this.cards = new ArrayList<>(cards);
        this.score = score;
    }

    public static ParticipantDto toDto(final Participant participant) {
        return new ParticipantDto(participant.getName(), participant.getCards(), participant.getScore());
    }

    public static ParticipantDto toDtoOfDealer(final Dealer dealer) {
        return new ParticipantDto(dealer.getName(), dealer.getCards().subList(0,1), dealer.getScore());
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