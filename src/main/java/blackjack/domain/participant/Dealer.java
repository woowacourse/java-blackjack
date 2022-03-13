package blackjack.domain.participant;

import blackjack.dto.CurrentCardsDto;
import blackjack.dto.DealerResultDto;
import blackjack.dto.PlayerResultDto;

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

    public DealerResultDto computeResult(List<PlayerResultDto> totalPlayerResult) {
        int loseCount = (int) totalPlayerResult.stream()
                .filter(PlayerResultDto::isWin)
                .count();
        int winCount = totalPlayerResult.size() - loseCount;

        return new DealerResultDto(getName(), winCount, loseCount);
    }

    @Override
    public CurrentCardsDto generateCurrentCardsDTO() {
        return new CurrentCardsDto(getName(), Collections.unmodifiableList(getCards().subList(0, 1)));
    }

}
