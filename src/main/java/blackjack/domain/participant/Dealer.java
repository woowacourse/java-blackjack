package blackjack.domain.participant;

import blackjack.dto.DealerResultDto;
import blackjack.dto.PlayerResultDto;

import java.util.List;

public class Dealer extends Participant {

    public static final int BOUND_FOR_ADDITIONAL_CARD = 16;
    private static final String NAME_OF_DEALER = "딜러";

    public Dealer() {
        super(NAME_OF_DEALER);
    }

    public boolean isHittable() {
        return score <= BOUND_FOR_ADDITIONAL_CARD;
    }

    public DealerResultDto computeResult(List<PlayerResultDto> totalPlayerResult) {
        int loseCount = (int) totalPlayerResult.stream()
                .filter(PlayerResultDto::isWin)
                .count();
        int winCount = totalPlayerResult.size() - loseCount;

        return new DealerResultDto(name, winCount, loseCount);
    }
}
