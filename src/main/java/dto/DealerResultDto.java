package dto;

import domain.model.Card;
import domain.model.Dealer;

import java.util.List;

public record DealerResultDto(
        List<CardDto> cardDtos,
        int sum
) {
    public static DealerResultDto of(Dealer dealer, int dealerFinalSum) {
        List<Card> cards = dealer.getDeck().getCards();
        List<CardDto> dealerCards = cards.stream()
                .map(CardDto::of)
                .toList();

        return new DealerResultDto(dealerCards, dealerFinalSum);
    }
}
