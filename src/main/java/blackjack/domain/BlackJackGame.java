package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import blackjack.domain.card.DrawStrategy;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamers;
import blackjack.domain.gamer.Player;
import blackjack.domain.result.BlackJackResult;
import blackjack.dto.GameResultDto;
import blackjack.dto.GamerDto;

public class BlackJackGame {

	private static final int INIT_DISTRIBUTION_COUNT = 2;
	private static final int DEFAULT_COUNT = 0;
	private static final int INCREASE_COUNT = 1;

	private final Gamers gamers;
	private final DrawStrategy deck;

	public BlackJackGame(List<String> names, Function<String, Integer> betting, DrawStrategy deck) {
		this.gamers = new Gamers(names, betting);
		this.deck = deck;
	}

	public void start(BiConsumer<GamerDto, List<GamerDto>> gamerSender) {
		giveFirstCards();
		gamerSender.accept(getDealerDto(), getPlayerDtos());
	}

	private void giveFirstCards() {
		for (int i = 0; i < INIT_DISTRIBUTION_COUNT; i++) {
			gamers.giveCardToAllGamers(this.deck);
		}
	}

	public void askPlayerHitOrStay(Function<String, Boolean> answerReceiver, Consumer<GamerDto> cardsSender) {
		for (String name : gamers.findPlayerNames()) {
			hitOrStay(name, answerReceiver, cardsSender);
		}
	}

	private void hitOrStay(String name,
		Function<String, Boolean> answerReceiver,
		Consumer<GamerDto> cardsSender) {
		while (!isBust(name) && answerReceiver.apply(name)) {
			gamers.giveCardToPlayer(name, deck);
			cardsSender.accept(getPlayerDto(name));
		}
	}

	private boolean isBust(String name) {
		return gamers.checkPlayerDrawPossible(name);
	}

	public int askDealerHitOrStay() {
		int count = 0;
		while (gamers.checkDealerDrawPossible()) {
			gamers.giveCardToDealer(deck);
			count++;
		}
		return count;
	}

	public GameResultDto createResult() {
		Dealer dealer = gamers.getDealer();
		List<Player> players = gamers.getPlayers();
		return makeResult(dealer, players);
	}

	private GameResultDto makeResult(Dealer dealer, List<Player> players) {
		int dealerEarning = 0;
		Map<String, Double> playerEarnings = new LinkedHashMap<>();

		for (Player player : players) {
			BlackJackResult result = player.match(dealer);
			double playerEarning = result.calculateEarning(player.getBet());
			playerEarnings.put(player.getName(), playerEarning);
			dealerEarning += result.getReverseEarning(playerEarning);
		}
		return new GameResultDto(dealerEarning, playerEarnings);
	}

	public GamerDto getDealerDto() {
		return new GamerDto(gamers.getDealer());
	}

	public List<GamerDto> getPlayerDtos() {
		return gamers.getPlayers().stream()
			.map(GamerDto::new)
			.collect(Collectors.toList());
	}

	private GamerDto getPlayerDto(String name) {
		return new GamerDto(gamers.findPlayerByName(name));
	}
}
