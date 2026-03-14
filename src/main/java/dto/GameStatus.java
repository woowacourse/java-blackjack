package dto;

import domain.Card;
import domain.Hand;
import domain.ParticipantsRole;
import java.util.List;

public record GameStatus(ParticipantsRole role, String name, Hand hand) {
    public int score() {
        return hand.scoreSum();
    }

    public boolean isBusted() {
        return hand.isBusted();
    }

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }

    public List<Card> cards() {
        return hand.cards();
    }
}
