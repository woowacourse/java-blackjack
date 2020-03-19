package view.dto;

import java.util.List;
import java.util.stream.Collectors;

import domain.gamer.Players;

/**
 *   class description
 *
 *   @author ParkDooWon, AnHyungJu  
 */
public class PlayersDto {
	private List<PlayerDto> players;

	private PlayersDto(Players players) {
		this.players = players.getPlayers().stream()
			.map(PlayerDto::from)
			.collect(Collectors.toList());
	}

	public static PlayersDto from(Players players) {
		return new PlayersDto(players);
	}

	public List<PlayerDto> getPlayers() {
		return players;
	}
}
