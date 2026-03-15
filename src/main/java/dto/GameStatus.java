package dto;

import domain.Card;
import domain.Participant;
import java.util.List;

public record GameStatus(boolean isDealer, String name, List<Card> cards, int score) {
    public static GameStatus from(Participant participant) {
        return new GameStatus(participant.isDealer(), participant.name(), participant.cards(),
                participant.score());
    }
}
