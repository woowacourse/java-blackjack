package dto;

import java.util.stream.Collectors;

import domain.card.Card;
import domain.card.Hands;
import domain.gamer.Dealer;
import domain.gamer.DealerGameResult;

/**
 *    딜러 DTO 클래스입니다.
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class DealerDto {
	private String name;
	private Hands hands;
	private DealerGameResult gameResult;

	private DealerDto(String name, Hands hands, DealerGameResult gameResult) {
		this.name = name;
		this.hands = hands;
		this.gameResult = gameResult;
	}

	public static DealerDto from(Dealer dealer) {
		return new DealerDto(dealer.getName(), dealer.getHands(), dealer.getDealerGameResult());
	}

	public String showDealerInitialCard() {
		return name
			+ ": "
			+ "HIDDEN, "
			+ hands.getCards()
			.get(1)
			.shape();
	}

	public String showCards() {
		return this.name
			+ ": "
			+ hands.getCards().stream()
			.map(Card::shape)
			.collect(Collectors.joining(", "));
	}

	public String getName() {
		return name;
	}

	public Hands getHands() {
		return hands;
	}

	public DealerGameResult getGameResult() {
		return gameResult;
	}
}
