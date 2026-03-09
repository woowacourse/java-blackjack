package model;

import java.util.List;
import dto.Card;
import dto.PlayerResult;

public class Scorer {
    private static final int ADDITIONAL_ACE_SCORE = 10;
    private static final int MAX_ACE_SCORE = 11;

    public static void updateAceScore(Participant participant) {
        boolean hasAce = hasAceCard(participant.getResult());
        Integer score = participant.getScore();

        if(hasAce && score <= MAX_ACE_SCORE) {
            participant.addScore(ADDITIONAL_ACE_SCORE);
        }
    }

    private static boolean hasAceCard(PlayerResult playerResult) {
        List<Card> cards = playerResult.hand();

        return cards.stream()
                .anyMatch(card -> card.cardNumber().equals(CardNumber.ACE));
    }
}
