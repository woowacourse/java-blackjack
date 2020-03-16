package view.dto;

import domain.card.Hands;
import domain.gamer.Player;

/**
 *    플레이어 DTO 클래스입니다.
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class PlayerDto {
	private String name;
	private Hands hands;

	private PlayerDto(String name, Hands hands) {
		this.name = name;
		this.hands = hands;
	}

	public static PlayerDto from(Player player) {
		return new PlayerDto(player.getName(), player.getHands());
	}

	public String getName() {
		return name;
	}

	public Hands getHands() {
		return hands;
	}
}
