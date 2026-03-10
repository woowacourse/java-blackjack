package dto;

import domain.participant.Dealer;
import util.CardMapper;

public record DealerInitialHandDTO(String firstHandCard) {
    public static DealerInitialHandDTO from(Dealer dealer) {
        return new DealerInitialHandDTO(CardMapper.cardToKorean(dealer.getFirstCard()));
    }
}
