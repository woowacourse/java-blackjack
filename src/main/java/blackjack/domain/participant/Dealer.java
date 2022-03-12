package blackjack.domain.participant;

import blackjack.dto.CurrentCardsDTO;
import blackjack.dto.DealerResultDTO;
import blackjack.dto.PlayerResultDTO;

import java.util.Collections;
import java.util.List;

public class Dealer extends Participant {

    public static final int BOUND_FOR_ADDITIONAL_CARD = 16;
    private static final String NAME_OF_DEALER = "딜러";

    public Dealer() {
        super(NAME_OF_DEALER);
    }

    public boolean isAbleToAddCard() {
        return getScore() <= BOUND_FOR_ADDITIONAL_CARD;
    }

    public DealerResultDTO computeResult(List<PlayerResultDTO> totalPlayerResult) {
        int loseCount = (int) totalPlayerResult.stream()
                .filter(PlayerResultDTO::isWin)
                .count();
        int winCount = totalPlayerResult.size() - loseCount;

        return new DealerResultDTO(getName(), winCount, loseCount);
    }

    @Override
    public CurrentCardsDTO generateCurrentCardsDTO() {
        return new CurrentCardsDTO(getName(), Collections.unmodifiableList(getCards().subList(0, 1)));
    }

}
