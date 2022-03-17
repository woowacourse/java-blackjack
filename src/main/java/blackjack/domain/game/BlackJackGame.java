package blackjack.domain.game;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.card.DrawStrategy;
import blackjack.domain.gamer.Bet;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamers;
import blackjack.domain.gamer.Player;
import blackjack.domain.result.BlackJackResult;
import blackjack.dto.GameResultDto;
import blackjack.dto.GamerDto;

public class BlackJackGame {

	public static final int INIT_DISTRIBUTION_COUNT = 2;
	private static final int DEFAULT_EARNING = 0;
	public static final int DEALER_OPEN_COUNT_FIRST = 1;

	private final Gamers gamers;
	private final DrawStrategy deck;

	public BlackJackGame(List<String> names, BettingInjector betting, DrawStrategy deck) {
		this.gamers = new Gamers(names, betting);
		this.deck = deck;
	}

	public GameResultDto play(HitRequester hitOrStay, CardsChecker cardChecker) {
		giveFirstCards();
		checkFirstCardsOfGamers(cardChecker);
		askPlayerHitOrStay(hitOrStay, cardChecker);
		askDealerHitOrStay();
		return createResult();
	}

	private void giveFirstCards() {
		for (int i = 0; i < INIT_DISTRIBUTION_COUNT; i++) {
			gamers.giveCardToAllGamers(this.deck);
		}
	}

	private void checkFirstCardsOfGamers(CardsChecker cardChecker) {
		GamerDto dealerDto = getDealerDto();
		cardChecker.check(dealerDto.getName(), getDealerFirstCard(dealerDto));
		getPlayerDtos().forEach(p -> cardChecker.check(p.getName(), p.getCards()));
	}

	private List<Card> getDealerFirstCard(GamerDto dealerDto) {
		return dealerDto.getCards()
			.subList(0, DEALER_OPEN_COUNT_FIRST);
	}

	private void askPlayerHitOrStay(HitRequester hitOrStay, CardsChecker cardChecker) {
		for (String name : gamers.findPlayerNames()) {
			hitOrStay(name, hitOrStay, cardChecker);
		}
	}

	private void hitOrStay(String name, HitRequester hitOrStay, CardsChecker cardChecker) {
		while (!isBust(name) && hitOrStay.request(name)) {
			gamers.giveCardToPlayer(name, deck);
			cardChecker.check(name, findPlayerDtoByName(name).getCards());
		}
	}

	private boolean isBust(String name) {
		return gamers.checkPlayerDrawPossible(name);
	}

	private GamerDto findPlayerDtoByName(String name) {
		return new GamerDto(gamers.findPlayerByName(name));
	}

	private void askDealerHitOrStay() {
		while (gamers.checkDealerDrawPossible()) {
			gamers.giveCardToDealer(deck);
		}
	}

	private GameResultDto createResult() {
		Dealer dealer = gamers.getDealer();
		List<Player> players = gamers.getPlayers();
		return makeResult(dealer, players);
	}

	private GameResultDto makeResult(Dealer dealer, List<Player> players) {
		int dealerEarning = DEFAULT_EARNING;
		Map<String, Integer> playerEarnings = new LinkedHashMap<>();

		for (Player player : players) {
			BlackJackResult result = player.match(dealer);
			int playerEarning = player.calculateEarning(result.getProfit());

			playerEarnings.put(player.getName(), playerEarning);
			dealerEarning += Bet.getMinusAmount(playerEarning);
		}
		return new GameResultDto(gamers.findDealerHitCount(), dealerEarning, playerEarnings);
	}

	public GamerDto getDealerDto() {
		return new GamerDto(gamers.getDealer());
	}

	public List<GamerDto> getPlayerDtos() {
		return gamers.getPlayers().stream()
			.map(GamerDto::new)
			.collect(Collectors.toList());
	}
}
