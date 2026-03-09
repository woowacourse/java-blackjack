package model;

import java.util.List;
import model.dto.Card;
import model.dto.PlayerResult;

public class Scorer {
    private static final Integer ADDITIONAL_ACE_SCORE = 10;
    private static final Integer MAX_ACE_SCORE = 11;

    public static Integer calculate(Card card) {
        return card.cardNumber().getScore();
    }

    public static void updateFinalScore(Participant participant) {
        boolean hasAce = hasAceCard(participant.getResult());
        Integer score = participant.getResult().score();

        if(hasAce && score <= MAX_ACE_SCORE) {
            participant.addScore(ADDITIONAL_ACE_SCORE);
        }
    }

    private static boolean hasAceCard(PlayerResult playerResult) {
        List<Card> cards = playerResult.deck();

        return cards.stream()
                .anyMatch(card -> card.cardNumber().equals(CardNumber.ACE));
    }
}
