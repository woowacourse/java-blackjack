package view.dto;

import java.util.List;
import java.util.stream.Collectors;

import domain.gamer.Players;

/**
 *   Players에 대한 DTO입니다.
 *
 *   @author ParkDooWon, AnHyungJu  
 */
public class PlayersDto {
	private List<GamerDto> players;

	private PlayersDto(Players players) {
		this.players = players.getPlayers().stream()
			.map(GamerDto::from)
			.collect(Collectors.toList());
	}

	public static PlayersDto from(Players players) {
		return new PlayersDto(players);
	}

	public List<GamerDto> getPlayers() {
		return players;
	}
}
