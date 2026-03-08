package domain.participant;

import static constant.GameRule.DEALER_HIT_CRITERION;

import dto.CardDto;
import java.util.List;

public class Dealer extends Participant {

    @Override
    public boolean checkScoreUnderCriterion() {
        return cardBoard.calculateScore() < DEALER_HIT_CRITERION;
    }


    public List<CardDto> getDealerCards() {
        return cardBoard.createDto();
    }

    public int calculateScore() {
        return cardBoard.calculateScore();
    }

    public boolean isBurst() {
        return cardBoard.isBust();
    }
}
