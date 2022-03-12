package blackjack.controller;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

import blackjack.domain.Answer;
import blackjack.domain.BlackJackGame;
import blackjack.dto.GameResultDto;
import blackjack.dto.GamerDto;

public class BlackJackController {

	private final BlackJackGame blackJackGame;

	public BlackJackController(List<String> names) {
		blackJackGame = new BlackJackGame(names);
		blackJackGame.distributeFirstCards();
	}

	public GamerDto getDealerDto() {
		return blackJackGame.getDealerDto();
	}

	public void askHitOrStay(UnaryOperator<String> operator, Consumer<GamerDto> consumer) {
		for (String name : blackJackGame.getPlayerNames()) {
			hitOrStay(operator, consumer, name);
		}
	}

	private void hitOrStay(UnaryOperator<String> operator, Consumer<GamerDto> consumer, String name) {
		while (!blackJackGame.isBurst(name) && Answer.from(operator.apply(name)).isYes()) {
			blackJackGame.distributeCardToPlayer(name);
			consumer.accept(findPlayerByName(name));
		}
	}

	private GamerDto findPlayerByName(String name) {
		return blackJackGame.getPlayerDto(name);
	}

	public List<GamerDto> getPlayerDtos() {
		return blackJackGame.getPlayerDtos();
	}

	public int requestAdditionalDrawToDealer() {
		return blackJackGame.distributeAdditionalToDealer();
	}

	public GameResultDto getGamerResult() {
		return blackJackGame.createResult();
	}
}
