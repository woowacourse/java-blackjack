package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.card.DrawStrategy;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamers;
import blackjack.domain.gamer.Player;
import blackjack.domain.result.BlackJackResult;
import blackjack.dto.GameResultDto;
import blackjack.dto.GamerDto;

public class BlackJackGame {

	public static final int INIT_DISTRIBUTION_COUNT = 2;
	private static final int DEFAULT_EARNING = 0;

	private final Gamers gamers;
	private final DrawStrategy deck;

	public BlackJackGame(List<String> names, Function<String, Integer> betting, DrawStrategy deck) {
		this.gamers = new Gamers(names, betting);
		this.deck = deck;
	}

	public void start(Function<String, Boolean> answerReceiver, BiConsumer<String, List<Card>> cardChecker) {
		giveFirstCards();
		checkFirstCardsOfGamers(cardChecker);
		askPlayerHitOrStay(answerReceiver, cardChecker);
	}

	private void giveFirstCards() {
		for (int i = 0; i < INIT_DISTRIBUTION_COUNT; i++) {
			gamers.giveCardToAllGamers(this.deck);
		}
	}

	private void checkFirstCardsOfGamers(BiConsumer<String, List<Card>> cardChecker) {
		GamerDto dealerDto = getDealerDto();
		cardChecker.accept(dealerDto.getName(), getDealerFirstCard(dealerDto));
		getPlayerDtos().forEach(p -> cardChecker.accept(p.getName(), p.getCards()));
	}

	private List<Card> getDealerFirstCard(GamerDto dealerDto) {
		return dealerDto.getCards().stream()
			.limit(1)
			.collect(Collectors.toList());
	}

	private void askPlayerHitOrStay(Function<String, Boolean> answerReceiver,
		BiConsumer<String, List<Card>> cardChecker) {
		for (GamerDto player : getPlayerDtos()) {
			hitOrStay(player, answerReceiver, cardChecker);
		}
	}

	private void hitOrStay(GamerDto player, Function<String, Boolean> answerReceiver,
		BiConsumer<String, List<Card>> cardChecker) {
		while (!isBust(player.getName()) && answerReceiver.apply(player.getName())) {
			String name = player.getName();
			gamers.giveCardToPlayer(name, deck);
			cardChecker.accept(name, findPlayerDtoByName(name).getCards());
		}
	}

	private boolean isBust(String name) {
		return gamers.checkPlayerDrawPossible(name);
	}

	private GamerDto findPlayerDtoByName(String name) {
		return new GamerDto(gamers.findPlayerByName(name));
	}

	public GameResultDto createResult() {
		Dealer dealer = gamers.getDealer();
		List<Player> players = gamers.getPlayers();
		return makeResult(dealer, players);
	}

	private GameResultDto makeResult(Dealer dealer, List<Player> players) {
		int dealerEarning = DEFAULT_EARNING;
		Map<String, Integer> playerEarnings = new LinkedHashMap<>();

		for (Player player : players) {
			BlackJackResult result = player.match(dealer);
			int playerEarning = result.calculateEarning(player.getBet());
			playerEarnings.put(player.getName(), playerEarning);
			dealerEarning += result.getReverseEarning(playerEarning);
		}
		return new GameResultDto(askDealerHitOrStay(), dealerEarning, playerEarnings);
	}

	public int askDealerHitOrStay() {
		int count = 0;
		while (gamers.checkDealerDrawPossible()) {
			gamers.giveCardToDealer(deck);
			count++;
		}
		return count;
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
