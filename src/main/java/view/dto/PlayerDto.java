package view.dto;

import domain.card.Hands;
import domain.gamer.Player;
import domain.gamer.PlayerGameResult;

/**
 *    플레이어 DTO 클래스입니다.
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class PlayerDto implements GamerDto {
	private String name;
	private Hands hands;
	private PlayerGameResult playerGameResult;
	private int totalScore;

	private PlayerDto(Player player) {
		this.name = player.getName();
		this.hands = player.getHands();
		this.playerGameResult = player.getPlayerGameResult();
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

	public PlayerGameResult getPlayerGameResult() {
		return playerGameResult;
	}
}
