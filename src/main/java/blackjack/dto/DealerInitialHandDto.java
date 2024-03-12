package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.Dealer;

public record DealerInitialHandDto(String name, CardDto firstCard) {

	public static DealerInitialHandDto fromDealer(Dealer dealer) {
		String dealerName = dealer.getName().value();
		Card dealerFirstCard = dealer.getFirstCard();

		return new DealerInitialHandDto(dealerName, CardDto.fromCard(dealerFirstCard));
	}
}
