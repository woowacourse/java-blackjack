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
	private static final String NEW_LINE = "\n";
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

	public String gameResult() {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(getDealer().showCards()).append(" - ")
			.append(getDealer().score()).append(NEW_LINE);
		for (PlayerDto player : getPlayers()) {
			stringBuilder.append(player.showCards()).append(" - ")
				.append(player.score()).append(NEW_LINE);
		}
		return stringBuilder.toString();
	}

	public String matchResult() {
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("## 최종 승패\n");
		for (PlayerDto playerDto : players) {
			stringBuilder.append(findMatchResult(playerDto))
				.append(NEW_LINE);
		}
		return stringBuilder.toString();
	}

	private String findMatchResult(PlayerDto playerDto) {
		if (playerDto.win(dealer.score())) {
			return playerDto.getName() + " : 승";
		}
		if (playerDto.isPush(dealer.score())) {
			return playerDto.getName() + " : 무";
		}
		return playerDto.getName() + " : 패";
	}

	public List<PlayerDto> getPlayers() {
		return players;
	}

	public DealerDto getDealer() {
		return dealer;
	}
}
