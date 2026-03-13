
package dto;

import domain.participant.Dealer;
import util.CardMapper;

public record DealerInitialHandDto(String firstHandCard) {
    public static DealerInitialHandDto from(Dealer dealer) {
        return new DealerInitialHandDto(CardMapper.cardToKorean(dealer.getFirstCard()));
    }
}

