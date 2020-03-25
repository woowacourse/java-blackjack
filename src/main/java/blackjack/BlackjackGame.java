package blackjack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
import domain.user.PlayerFactory;
import domain.user.User;
import view.InputView;
import view.OutputView;

public class BlackjackGame {
	private final CardDivider cardDivider;
	private final RuleChecker ruleChecker;
	private final List<Player> players;
	private final Dealer dealer;

	public BlackjackGame() {
		this.cardDivider = new CardDivider();
		ruleChecker = new RuleChecker();
		dealer = new Dealer();
		players = PlayerFactory.create(InputView.inputNames(), InputView::inputBettingMoney);
	}

	public void play() {
		List<User> allUsers = initAllUsers();
		initCards(allUsers);
		printBlackjackUsers(allUsers);
		checkCanDraw();
		OutputView.printUserResult(allUsers);
		showGameResult();
	}

	private List<User> initAllUsers() {
		List<User> allUsers = new ArrayList<>(players);
		allUsers.add(dealer);
		return allUsers;
	}

	private void initCards(List<User> allUsers) {
		for (User user : allUsers) {
			user.drawFirst(cardDivider);
		}
		OutputView.printInitialResult(allUsers);
	}

	private void printBlackjackUsers(List<User> allUsers) {
		List<User> blackjackUsers = allUsers.stream()
			.filter(user -> ruleChecker.isBlackjack(user))
			.collect(Collectors.toList());
		OutputView.printBlackJackUser(blackjackUsers);
	}

	private void checkCanDraw() {
		if (ruleChecker.isBlackjack(dealer)) {
			return;
		}
		drawCard(players, dealer);
	}

	private void drawCard(List<Player> users, Dealer dealer) {
		drawPlayersCard(users);
		drawDealerCard(dealer);
	}

	private void drawPlayersCard(List<Player> users) {
		for (User user : users) {
			drawPlayerCard(user);
		}
	}

	private void drawPlayerCard(User user) {
		if (ruleChecker.isBlackjack(user)) {
			return;
		}
		while (isUserNotBust(user) && isContinuousFromInput(user)) {
			user.addCards(Arrays.asList(cardDivider.divide()));
			OutputView.printUserCard(user);
		}
	}

	private boolean isUserNotBust(User user) {
		if (ruleChecker.isBust(user) == false) {
			return true;
		}
		OutputView.printUserBust(user);
		return false;
	}

	private boolean isContinuousFromInput(User user) {
		YesOrNo yesOrNo = new YesOrNo(InputView.inputYesORNo(user.getName()));
		return yesOrNo.isContinue();
	}

	private void drawDealerCard(Dealer dealer) {
		while (dealer.isDrawable()) {
			dealer.addCards(Arrays.asList(cardDivider.divide()));
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
		for (Player player : players) {
			MatchResult matchResult = MatchResult.findMatchResult(new PlayerFinalScore(player), dealerFinalScore);
			FinalMoney finalMoney = matchResult.makeMoneyResult(player);
			userResults.add(new UserResult(player.getName(), finalMoney.compare(player)));
		}
		return userResults;
	}
}
