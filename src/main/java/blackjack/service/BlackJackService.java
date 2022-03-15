package blackjack.service;

import blackjack.domain.game.Compete;
import blackjack.domain.game.Deck;
import blackjack.domain.game.RedrawChoice;
import blackjack.domain.role.Dealer;
import blackjack.domain.role.Hand;
import blackjack.domain.role.Player;
import blackjack.domain.role.PlayerTurns;
import blackjack.domain.role.Role;
import blackjack.dto.DealerTableDto;
import blackjack.dto.DealerTurnDto;
import blackjack.dto.FinalResultDto;
import blackjack.dto.PlayerStatusDto;
import blackjack.dto.PlayerTableDto;
import blackjack.dto.PlayerTurnsDto;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class BlackJackService {

	private Deck deck;
	private Role dealer;
	private List<Role> players;

	public void initBlackJackGame(Deck deck, Role dealer) {
		this.deck = deck;
		this.dealer = dealer;
	}

	public void joinPlayers(final List<String> names) {
		players = names.stream()
				.map(name -> new Player(name, new Hand()))
				.collect(Collectors.toList());
	}

	public DealerTableDto distributeCardToDealer() {
		dealer.draw(deck.draw());
		dealer.draw(deck.draw());
		return DealerTableDto.from(dealer);
	}

	public List<PlayerTableDto> distributeCardToPlayers() {
		List<PlayerTableDto> playerStatuses = new ArrayList<>();
		for (Role player : players) {
			player.draw(deck.draw());
			player.draw(deck.draw());
			playerStatuses.add(PlayerTableDto.from(player));
		}
		return playerStatuses;
	}

	public PlayerTurnsDto startPlayerDrawPhase() {
		if (dealer.isBlackJack()) {
			return PlayerTurnsDto.from(PlayerTurns.createEmpty());
		}
		return PlayerTurnsDto.from(new PlayerTurns(players));
	}

	public PlayerStatusDto drawPlayer(final RedrawChoice answer, final String name) {
		Role player = getPlayerByName(name);
		if (answer == RedrawChoice.NO) {
			return PlayerStatusDto.from(false, player);
		}
		player.draw(deck.draw());
		return PlayerStatusDto.from(player.canDraw(), player);
	}

	private Role getPlayerByName(String name) {
		return players.stream()
				.filter(player -> player.getName().equals(name))
				.findFirst()
				.orElseThrow(NoSuchElementException::new);
	}

	public DealerTurnDto drawDealer() {
		if (!dealer.canDraw()) {
			return DealerTurnDto.from(dealer, false, Dealer.CAN_NOT_DRAW_STANDARD);
		}
		dealer.draw(deck.draw());
		return DealerTurnDto.from(dealer, true, Dealer.CAN_DRAW_STANDARD);
	}

	public FinalResultDto calculateFinalResult() {
		Compete compete = new Compete();
		for (Role player : players) {
			compete.judgeCompete(player, dealer);
		}
		return FinalResultDto.from(dealer, players, compete);
	}
}
