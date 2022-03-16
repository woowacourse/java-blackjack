package blackjack.service;

import blackjack.domain.game.Betting;
import blackjack.domain.game.Compete;
import blackjack.domain.game.Deck;
import blackjack.domain.game.Money;
import blackjack.domain.game.Revenue;
import blackjack.domain.role.Dealer;
import blackjack.domain.role.Hand;
import blackjack.domain.role.Player;
import blackjack.domain.role.PlayerDrawChoice;
import blackjack.domain.role.PlayerTurns;
import blackjack.domain.role.Players;
import blackjack.domain.role.Role;
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
				.map(name -> new Player(name, new Hand()))
				.collect(Collectors.toList());
		players = new Players(joinPlayers);
	}

	public PlayerTurnsDto startBettingPhase() {
		return PlayerTurnsDto.from(players.getPlayerTurn());
	}

	public void betMoney(List<BettingDto> bettingMoneys) {
		Map<Role, Money> betting = new HashMap<>();
		for (BettingDto bettingDto : bettingMoneys) {
			Role player = players.getPlayerByName(bettingDto.getName());
			Money money = new Money(bettingDto.getMoney());
			betting.put(player, money);
		}
		this.betting = new Betting(betting);
	}

	public DealerTableDto distributeCardToDealer() {
		dealer.draw(deck.draw());
		dealer.draw(deck.draw());
		return DealerTableDto.from(dealer);
	}

	public List<PlayerTableDto> distributeCardToPlayers() {
		return players.distributeCard(deck).stream()
				.map(PlayerTableDto::from)
				.collect(Collectors.toList());
	}

	public PlayerTurnsDto startPlayerDrawPhase() {
		if (dealer.isBlackJack()) {
			return PlayerTurnsDto.from(PlayerTurns.createEmpty());
		}
		return PlayerTurnsDto.from(players.getPlayerTurn());
	}

	public PlayerStatusDto drawPlayer(final PlayerDrawChoice answer, final String name) {
		Role player = players.getPlayerByName(name);
		if (answer == PlayerDrawChoice.NO) {
			return PlayerStatusDto.from(false, player);
		}
		player.draw(deck.draw());
		return PlayerStatusDto.from(player.canDraw(), player);
	}

	public DealerTurnDto drawDealer() {
		if (!dealer.canDraw()) {
			return DealerTurnDto.from(dealer, false, Dealer.CAN_NOT_DRAW_STANDARD);
		}
		dealer.draw(deck.draw());
		return DealerTurnDto.from(dealer, true, Dealer.CAN_DRAW_STANDARD);
	}

	public FinalResultDto calculateFinalResult() {
		final Compete compete = players.competeToDealer(dealer);
		final Revenue revenue = new Revenue(betting.settle(compete));
		return FinalResultDto.from(dealer, players, revenue);
	}
}
