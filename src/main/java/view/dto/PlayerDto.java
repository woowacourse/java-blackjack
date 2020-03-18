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
	private int score;

	private PlayerDto(String name, Hands hands) {
		this.name = name;
		this.hands = hands;
		this.score = hands.calculateTotalScore();
	}

	public static PlayerDto from(Player player) {
		return new PlayerDto(player.getName(), player.getHands());
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Hands getHands() {
		return hands;
	}

	public int getScore() {
		return score;
	}
}
