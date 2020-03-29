package blackjack;

import java.util.ArrayList;
import java.util.List;

import domain.YesOrNo;
import domain.betting.FinalMoney;
import domain.card.CardDivider;
import domain.result.DealerResult;
import domain.result.MatchResult;
import domain.result.UserResult;
import domain.result.score.DealerFinalScore;
import domain.result.score.PlayerFinalScore;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import domain.user.PlayersFactory;
import domain.user.User;
import view.InputView;
import view.OutputView;

public class BlackjackGame {
	private final CardDivider cardDivider;
	private final RuleChecker ruleChecker;
	private final Players players;
	private final Dealer dealer;

	public BlackjackGame() {
		this.cardDivider = new CardDivider();
		ruleChecker = new RuleChecker();
		dealer = new Dealer();
		players = PlayersFactory.create(InputView.inputNames(), InputView::inputBettingMoney);
	}

	public void play() {
		initCards();
		printBlackjackUsers();
		checkCanDraw();
		OutputView.printUserResult(players, dealer);
		showGameResult();
	}

	private void initCards() {
		players.drawFirst(cardDivider);
		dealer.drawFirst(cardDivider);
		OutputView.printInitialResult(players, dealer);
	}

	private void printBlackjackUsers() {
		List<User> blackjackUsers = players.findByBlackjack(ruleChecker);
		if (ruleChecker.isBlackjack(dealer)) {
			blackjackUsers.add(dealer);
		}
		OutputView.printBlackJackUser(blackjackUsers);
	}

	private void checkCanDraw() {
		if (ruleChecker.isBlackjack(dealer)) {
			return;
		}
		drawPlayersCard(players);
		drawDealerCard(dealer);
	}

	private void drawPlayersCard(Players players) {
		for (Player player : players.getPlayers()) {
			drawPlayerCard(player);
		}
	}

	private void drawPlayerCard(Player player) {
		if (ruleChecker.isBlackjack(player)) {
			return;
		}
		while (isPlayerNotBust(player) && isContinuousFromInput(player)) {
			player.draw(cardDivider);
			OutputView.printUserCard(player);
		}
	}

	private boolean isPlayerNotBust(Player player) {
		if (ruleChecker.isBust(player) == false) {
			return true;
		}
		OutputView.printUserBust(player);
		return false;
	}

	private boolean isContinuousFromInput(Player player) {
		YesOrNo yesOrNo = new YesOrNo(InputView.inputYesORNo(player.getName()));
		return yesOrNo.isContinue();
	}

	private void drawDealerCard(Dealer dealer) {
		while (dealer.isDrawable()) {
			dealer.draw(cardDivider);
			OutputView.printDealerDraw();
		}
	}

	private void showGameResult() {
		DealerFinalScore dealerFinalScore = new DealerFinalScore(dealer);

		List<UserResult> userResults = createUserResults(dealerFinalScore);
		DealerResult dealerResult = new DealerResult(userResults);

		OutputView.printGameResult(userResults, dealerResult);
	}

	private List<UserResult> createUserResults(DealerFinalScore dealerFinalScore) {
		List<UserResult> userResults = new ArrayList<>();
		for (Player player : players.getPlayers()) {
			MatchResult matchResult = MatchResult.findMatchResult(new PlayerFinalScore(player), dealerFinalScore);
			FinalMoney finalMoney = matchResult.makeMoneyResult(player);
			userResults.add(new UserResult(player.getName(), finalMoney.compare(player)));
		}
		return userResults;
	}
}
