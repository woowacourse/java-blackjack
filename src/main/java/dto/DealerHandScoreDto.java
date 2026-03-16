package dto;

import domain.participant.Dealer;
import util.HandCardProcessor;

import java.util.List;

public record DealerHandScoreDto(List<String> handCards, int score, boolean isBust, boolean isBlackJack) {

    public static DealerHandScoreDto from(Dealer dealer) {
        return new DealerHandScoreDto(
                HandCardProcessor.processHandCards(dealer.getHandCards()),
                dealer.getScore(),
                dealer.isBust(),
                dealer.isBlackJack()
        );
    }
}
