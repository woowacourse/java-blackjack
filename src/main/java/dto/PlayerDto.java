package dto;

import java.util.stream.Collectors;

import domain.card.Card;
import domain.card.Hands;
import domain.gamer.Player;
import domain.gamer.PlayerGameResult;

/**
 *    플레이어 DTO 클래스입니다.
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class PlayerDto {
	private String name;
	private Hands hands;
	private PlayerGameResult playerGameResult;

	private PlayerDto(String name, Hands hands, PlayerGameResult playerGameResult) {
		this.name = name;
		this.hands = hands;
		this.playerGameResult = playerGameResult;
	}

	public static PlayerDto from(Player player) {
		return new PlayerDto(player.getName(), player.getHands(), player.getPlayerGameResult());
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

	public PlayerGameResult getPlayerGameResult() {
		return playerGameResult;
	}
}
