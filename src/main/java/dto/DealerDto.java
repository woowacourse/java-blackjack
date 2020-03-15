package dto;

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

	private DealerDto(String name, Hands hands, DealerGameResult gameResult) {
		this.name = name;
		this.hands = hands;
		this.gameResult = gameResult;
		this.totalScore = hands.calculateTotalScore();
	}

	public static DealerDto from(Dealer dealer) {
		return new DealerDto(dealer.getName(), dealer.getHands(), dealer.getDealerGameResult());
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
