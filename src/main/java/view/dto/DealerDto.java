package view.dto;

import domain.card.Hands;
import domain.gamer.Dealer;
import domain.gamer.DealerGameResult;

/**
 *    딜러 DTO 클래스입니다.
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class DealerDto implements GamerDto {
	private String name;
	private Hands hands;
	private DealerGameResult gameResult;
	private int totalScore;

	private DealerDto(Dealer dealer) {
		this.name = dealer.getName();
		this.hands = dealer.getHands();
		this.gameResult = dealer.getDealerGameResult();
		this.totalScore = hands.calculateTotalScore();
	}

	public static DealerDto from(Dealer dealer) {
		return new DealerDto(dealer);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Hands getHands() {
		return hands;
	}

	@Override
	public int getTotalScore() {
		return totalScore;
	}

	public DealerGameResult getGameResult() {
		return gameResult;
	}
}
