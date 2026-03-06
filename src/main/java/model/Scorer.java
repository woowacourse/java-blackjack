package model;

import java.util.List;
import model.dto.Card;
import model.dto.PlayerResult;

public class Scorer {
    public static Integer calculate(Card card) {
        return card.cardNumber().getScore();
    }

    public static void updateFinalScore(Participant participant) {
        Boolean hasAce = hasAceCard(participant.getResult());
        Integer score = participant.getResult().score();

        if(hasAce && score <= 11) {
            participant.addScore(10);
        }
    }

    private static boolean hasAceCard(PlayerResult playerResult) {
        List<Card> cards = playerResult.deck();

        return !cards.stream()
                .filter((c) -> c.cardNumber().equals(CardNumber.ACE))
                .toList()
                .isEmpty();
    }
}
