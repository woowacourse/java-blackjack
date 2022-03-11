package blackjack.dto;

import java.util.ArrayList;
import java.util.List;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;

public class ParticipantDto {

    private final String name;
    private final List<String> cardNames;
    private final int score;

    private ParticipantDto(final String name, final List<String> cardNames, final int score) {
        this.name = name;
        this.cardNames = new ArrayList<>(cardNames);
        this.score = score;
    }

    public static ParticipantDto toDto(final Participant participant) {
        return new ParticipantDto(participant.getName(), participant.getCardNames(), participant.getScore());
    }

    public static ParticipantDto toDtoOfDealer(final Dealer dealer) {
        return new ParticipantDto(dealer.getName(), dealer.getCardNames().subList(0,1), dealer.getScore());
    }

    public String getName() {
        return name;
    }

    public List<String> getCardNames() {
        return List.copyOf(cardNames);
    }

    public int getScore() {
        return score;
    }

}