package controller;

import java.util.ArrayList;
import java.util.List;

import domain.BlackJackGameRunner;
import domain.CardGenerator;
import domain.Player;
import view.InputView;
import view.NameCardScoreDto;
import view.NameProfitDto;
import view.OutputView;
import view.ViewRenderer;

public class BlackJackController {

	private final CardGenerator cardGenerator;
	private BlackJackGameRunner runner;

	public BlackJackController(CardGenerator cardGenerator) {
		this.cardGenerator = cardGenerator;
	}

	public void play() {
		runner = initGameRunner();
		giveInitialCards();
		givePlayerAdditionalCard();
		giveDealerAdditionalCard();
		printTotalCardState();
		printResult();
	}

	private BlackJackGameRunner initGameRunner() {
		List<String> names = ExecuteContext.RetryIfThrowsException(InputView::inputNames);
		List<Integer> bets = getBets(names);
		return BlackJackGameRunner.of(cardGenerator, names, bets);
	}

	private List<Integer> getBets(List<String> names) {
		List<Integer> bets = new ArrayList<>();
		for (String name : names) {
			int bet = ExecuteContext.RetryIfThrowsException(() -> InputView.inputBet(name));
			bets.add(bet);
		}
		return bets;
	}

	private void giveInitialCards() {
		List<NameCardScoreDto> nameCardScores = ViewRenderer.toNameCardScore(runner.givePlayersInitialCards());
		OutputView.printInitializedPlayers(nameCardScores);
		OutputView.printFirstCard(ViewRenderer.toNameCardScore(runner.giveDealerInitialCards()));
		OutputView.printCards(nameCardScores);
	}

	private void givePlayerAdditionalCard() {
		while (runner.isGameOnPlayersHitStage()) {
			Player player = runner.getCurrentPlayerOnHitStage();
			boolean hit = ExecuteContext.RetryIfThrowsException(() ->
				InputView.inputPlayerHitOrStand(ViewRenderer.toNameCardScore(player)));
			runner.handlePlayerHit(hit);
			OutputView.printCards(ViewRenderer.toNameCardScore(player));
		}
	}

	private void giveDealerAdditionalCard() {
		while (runner.isDealerReceivable()) {
			runner.giveDealerIfReceivable();
			OutputView.printDealerReceptionNotice();
		}
	}

	private void printTotalCardState() {
		List<NameCardScoreDto> nameCardScores = new ArrayList<>();
		nameCardScores.add(ViewRenderer.toNameCardScore(runner.getDealer()));
		List<Player> players = runner.getPlayers();
		players.forEach(player -> nameCardScores.add(ViewRenderer.toNameCardScore(player)));
		OutputView.printTotalCardState(nameCardScores);
	}

	private void printResult() {
		OutputView.printProfitMessage();
		NameProfitDto dealerProfit = ViewRenderer.toNameProfit(runner.getDealerResults());
		OutputView.printProfits(dealerProfit);
		List<NameProfitDto> playerProfits = ViewRenderer.toNameProfit(runner.getPlayerResults());
		OutputView.printProfits(playerProfits);
	}
}
