package dto;

import java.util.List;

import card.Card;
import participants.Name;
import participants.Participant;

public class PlayerResultDto {
    private final Name name;
    private final List<Card> cards;
    private final int score;

    private PlayerResultDto(Name name, List<Card> cards, int score) {
        this.name = name;
        this.cards = cards;
        this.score = score;
    }


    public static PlayerResultDto from(Participant participant) {
        return new PlayerResultDto(participant.getName(), participant.showCards(), participant.calculateScore().getScore());
    }

    public Name getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }
}
