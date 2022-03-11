package blackjack.controller;

import blackjack.controller.dto.GameResultDto;
import blackjack.controller.dto.GamerDto;
import blackjack.domain.Answer;
import blackjack.domain.BlackJackGame;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.result.BlackJackReferee;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class BlackJackController {

	private final BlackJackGame blackJackGame;

	public BlackJackController(List<String> names) {
		blackJackGame = new BlackJackGame(names);
		blackJackGame.distributeFirstCards();
	}

	public GamerDto getDealerDto() {
		Dealer dealer = blackJackGame.getDealer();
		return new GamerDto(dealer);
	}

	public void askHitOrStay(List<String> names,
							UnaryOperator<String> operator,
							Consumer<GamerDto> consumer) {
		for (String name : names) {
			hitOrStay(operator, consumer, name);
		}
	}

	private void hitOrStay(UnaryOperator<String> operator, Consumer<GamerDto> consumer, String name) {
		while (!blackJackGame.isBurst(name) && Answer.from(operator.apply(name)).isYes()) {
			requestPlayerDrawCard(name);
			consumer.accept(findPlayerByName(name));
		}
	}

	private GamerDto findPlayerByName(String name) {
		Player player = blackJackGame.findPlayerByName(name);
		return new GamerDto(player);
	}

	public List<GamerDto> getPlayerDtos() {
		List<Player> players = blackJackGame.getPlayers();
		return players.stream()
			.map((GamerDto::new))
			.collect(Collectors.toUnmodifiableList());
	}

	public void requestPlayerDrawCard(String name) {
		blackJackGame.distributeCardToPlayer(name);
	}

	public int getDealerAdditionalCardCount() {
		return blackJackGame.distributeAdditionalToDealer();
	}

	public GameResultDto getGamerResult() {
		BlackJackReferee result = blackJackGame.createResult();
		return new GameResultDto(result);
	}
}
