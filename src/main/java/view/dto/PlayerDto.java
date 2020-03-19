package view.dto;

import domain.card.Hands;
import domain.gamer.Player;

/**
 *    플레이어 DTO 클래스입니다.
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class PlayerDto implements GamerDto {
	private String name;
	private Hands hands;
	private int totalScore;

	private PlayerDto(Player player) {
		this.name = player.getName();
		this.hands = player.getHands();
		this.totalScore = hands.calculateTotalScore();
	}

	public static PlayerDto from(Player player) {
		return new PlayerDto(player);
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
}
