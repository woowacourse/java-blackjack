package dto;

import domain.Dealer;
import java.util.List;

public record DealerResultDto(
        List<String> cards,
        int score
) {
    public static DealerResultDto from(Dealer dealer) {
        // TODO 분리? -> 현재 PlayerResultDto와 중복됨.
        List<String> cardInfo = dealer.getHandCards().stream()
                .map(card -> card.getCardNumber().getSymbol() + card.getCardShape().getName())
                .toList();

        return new DealerResultDto(cardInfo, dealer.getHand().getScore().getValue());
    }
}
