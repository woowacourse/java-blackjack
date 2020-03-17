package view.dto;

import java.util.List;
import java.util.stream.Collectors;

import domain.BlackjackGame;
import domain.gamer.Dealer;
import domain.gamer.Player;

/**
 *   class 블랙잭 게임 DTO입니다.
 *
 *   @author ParkDooWon, AnHyungJu  
 */
public class BlackjackGameDto {
	private List<PlayerDto> players;
	private DealerDto dealer;

	private BlackjackGameDto(List<Player> players, Dealer dealer) {
		this.players = players.stream()
			.map(PlayerDto::from)
			.collect(Collectors.toList());
		this.dealer = DealerDto.from(dealer);
	}

	public static BlackjackGameDto from(BlackjackGame blackjackGame) {
		return new BlackjackGameDto(blackjackGame.getPlayers(), blackjackGame.getDealer());
	}

	public List<PlayerDto> getPlayers() {
		return players;
	}

	public DealerDto getDealer() {
		return dealer;
	}
}
