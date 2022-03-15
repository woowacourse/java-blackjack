package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.DealerDrawable;
import blackjack.domain.Deck;
import blackjack.domain.Hand;
import blackjack.dto.DealerTableDto;
import blackjack.dto.PlayerTableDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

import blackjack.domain.RedrawChoice;
import blackjack.dto.DealerTurnDto;
import blackjack.dto.FinalResultDto;
import blackjack.dto.PlayerStatusDto;
import blackjack.dto.PlayerTurnDto;
import blackjack.service.BlackJackService;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

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

	private void takePlayersTurn(Function<PlayerTurnDto, String> answerFunction,
								 Consumer<PlayerTableDto> printerPlayerStatus) {
		while (true) {
			String answer = answerFunction.apply(getWhoseTurn());
			PlayerStatusDto playerStatus = drawPlayer(answer);
			printerPlayerStatus.accept(playerStatus.getTableStatusDto());
			if (playerStatus.isDraw()) {
				continue;
			}
			if (!playerStatus.isHasNextPlayer()) {
				break;
			}
		}
	}

	private PlayerTurnDto getWhoseTurn() {
		return blackJackService.whoseTurn();
	}

	private PlayerStatusDto drawPlayer(String answer) {
		return blackJackService.drawPlayer(RedrawChoice.of(answer));
	}

	private void takeDealerTurn(Consumer<DealerTurnDto> printerDealerTurn) {
		printerDealerTurn.accept(blackJackService.drawDealer());
	}

	private void getFinalResult(Consumer<FinalResultDto> printerFinalResult) {
		printerFinalResult.accept(blackJackService.calculateFinalResult());
	}
}
