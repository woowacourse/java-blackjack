package dto;

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

	private PlayerDto(String name, Hands hands, PlayerGameResult playerGameResult) {
		this.name = name;
		this.hands = hands;
		this.playerGameResult = playerGameResult;
		this.totalScore = hands.calculateTotalScore();
	}

	public static PlayerDto from(Player player) {
		return new PlayerDto(player.getName(), player.getHands(), player.getPlayerGameResult());
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
