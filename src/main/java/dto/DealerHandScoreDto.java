package dto;

import domain.participant.Dealer;
import util.CardMapper;

import java.util.List;

public record DealerHandScoreDto(List<String> handCards, int score, boolean isBust, boolean isBlackJack) {

    public static DealerHandScoreDto from(Dealer dealer) {
        return new DealerHandScoreDto(
                dealer.getHandCards().stream().map(CardMapper::cardToKorean).toList(),
                dealer.getScore(),
                dealer.getStatus().getCards().isBust(),
                dealer.getStatus().getCards().isBlackJack());
    }
}
