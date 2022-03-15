package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.DealerDrawable;
import blackjack.domain.Deck;
import blackjack.domain.Hand;
import blackjack.domain.RedrawChoice;
import blackjack.dto.DealerTableDto;
import blackjack.dto.DealerTurnDto;
import blackjack.dto.FinalResultDto;
import blackjack.dto.PlayerStatusDto;
import blackjack.dto.PlayerTableDto;
import blackjack.dto.PlayerTurnsDto;
import blackjack.service.BlackJackService;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class BlackJackController {

	private final BlackJackService blackJackService;

	public BlackJackController(final BlackJackService blackJackService) {
		this.blackJackService = blackJackService;
	}

	public void run(InputView inputView, OutputView outputView) {
		initBlackJackGame();
		addPlayers(inputView::requestPlayerName);
		distributeCard(outputView::printInitialStatus);
		takePlayersTurn(inputView::drawOneMoreCard, outputView::printPlayerHand);
		takeDealerTurn(outputView::printDealerStatus);
		getFinalResult(outputView::printFinalResult);
	}

	private void initBlackJackGame() {
		blackJackService.initBlackJackGame(new Deck(), new Dealer(new Hand(), DealerDrawable::chooseDraw));
	}

	private void addPlayers(Supplier<List<String>> playerNames) {
		blackJackService.joinPlayers(playerNames.get());
	}

	private void distributeCard(BiConsumer<DealerTableDto, List<PlayerTableDto>> printerTable) {
		DealerTableDto dealerTable = blackJackService.distributeCardToDealer();
		List<PlayerTableDto> playersTable = blackJackService.distributeCardToPlayers();
		printerTable.accept(dealerTable, playersTable);
	}

	private void takePlayersTurn(UnaryOperator<String> answerFunction,
								 Consumer<PlayerTableDto> printerPlayerStatus) {
		PlayerTurnsDto playerTurns = blackJackService.startPlayerDrawPhase();
		for (String player : playerTurns.getNames()) {
			drawPlayer(answerFunction, printerPlayerStatus, player);
		}
	}

	private void drawPlayer(UnaryOperator<String> answerFunction,
							Consumer<PlayerTableDto> printerPlayerStatus,
							String player) {
		String answer = answerFunction.apply(player);
		PlayerStatusDto playerStatus = blackJackService.drawPlayer(RedrawChoice.of(answer), player);
		printerPlayerStatus.accept(playerStatus.getTableStatusDto());
		if (playerStatus.isDraw()) {
			drawPlayer(answerFunction, printerPlayerStatus, player);
		}
	}

	private void takeDealerTurn(Consumer<DealerTurnDto> printerDealerTurn) {
		printerDealerTurn.accept(blackJackService.drawDealer());
	}

	private void getFinalResult(Consumer<FinalResultDto> printerFinalResult) {
		printerFinalResult.accept(blackJackService.calculateFinalResult());
	}
}
