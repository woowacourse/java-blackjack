package blackjack.service;

import blackjack.domain.game.Betting;
import blackjack.domain.game.Deck;
import blackjack.domain.game.Money;
import blackjack.domain.game.Revenue;
import blackjack.domain.role.Dealer;
import blackjack.domain.role.Player;
import blackjack.domain.role.PlayerDrawChoice;
import blackjack.domain.role.PlayerTurns;
import blackjack.domain.role.Players;
import blackjack.domain.role.Role;
import blackjack.domain.state.Ready;
import blackjack.dto.BettingDto;
import blackjack.dto.DealerTableDto;
import blackjack.dto.DealerTurnDto;
import blackjack.dto.FinalResultDto;
import blackjack.dto.PlayerStatusDto;
import blackjack.dto.PlayerTableDto;
import blackjack.dto.PlayerTurnsDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackService {

	private Deck deck;
	private Role dealer;
	private Players players;
	private Betting betting;

	public void initBlackJackGame(Deck deck, Role dealer) {
		this.deck = deck;
		this.dealer = dealer;
	}

	public void joinPlayers(final List<String> names) {
		final List<Role> joinPlayers = names.stream()
				.map(name -> new Player(name, new Ready()))
				.collect(Collectors.toList());
		players = new Players(joinPlayers);
	}

	public PlayerTurnsDto startBettingPhase() {
		return PlayerTurnsDto.from(players.getPlayerTurn());
	}

	public void betMoney(List<BettingDto> bettingMoneys) {
		Map<String, Money> betting = new HashMap<>();
		for (BettingDto bettingDto : bettingMoneys) {
			Money money = new Money(bettingDto.getMoney());
			betting.put(bettingDto.getName(), money);
		}
		this.betting = new Betting(betting);
	}

	public DealerTableDto distributeCardToDealer() {
		dealer.ready(deck);
		return DealerTableDto.from(dealer);
	}

	public List<PlayerTableDto> distributeCardToPlayers() {
		return players.ready(deck).stream()
				.map(PlayerTableDto::from)
				.collect(Collectors.toList());
	}

	public PlayerTurnsDto startPlayerDrawPhase() {
		if (dealer.isFinished()) {
			return PlayerTurnsDto.from(PlayerTurns.createEmpty());
		}
		return PlayerTurnsDto.from(players.getPlayerTurn());
	}

	public PlayerStatusDto drawPlayer(final PlayerDrawChoice answer, final String name) {
		Role player = players.getPlayerByName(name);
		if (answer == PlayerDrawChoice.NO) {
			player.stay();
			return PlayerStatusDto.from(false, player);
		}
		player.draw(deck);
		return PlayerStatusDto.from(!player.isFinished(), player);
	}

	public DealerTurnDto drawDealer() {
		if (dealer.isFinished()) {
			return DealerTurnDto.from(dealer, false, Dealer.CAN_NOT_DRAW_STANDARD);
		}
		dealer.draw(deck);
		dealer.stay();
		return DealerTurnDto.from(dealer, true, Dealer.CAN_DRAW_STANDARD);
	}

	public FinalResultDto calculateFinalResult() {
		final Revenue revenue = new Revenue(players.competeToDealer(dealer, betting));
		return FinalResultDto.from(dealer, players, revenue);
	}
}
