package dto;

import domain.model.Card;
import domain.model.Dealer;

import java.util.List;

public record DealerResultDto(
        List<CardDto> cardDtos,
        int sum,
        double profit
) {
    public static DealerResultDto of(Dealer dealer) {
        List<Card> cards = dealer.getDeck().getCards();
        List<CardDto> dealerCards = cards.stream()
                .map(CardDto::of)
                .toList();

        return new DealerResultDto(dealerCards, dealer.getFinalDeckSum(), dealer.getProfit());
    }
}
