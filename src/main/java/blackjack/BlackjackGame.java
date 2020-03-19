package blackjack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import domain.YesOrNo;
import domain.betting.BettingBoard;
import domain.betting.FinalMoney;
import domain.betting.Money;
import domain.card.CardDivider;
import domain.result.DealerResult;
import domain.result.MatchResult;
import domain.result.UserResult;
import domain.result.score.DealerFinalScore;
import domain.result.score.PlayerFinalScore;
import domain.user.Dealer;
import domain.user.PlayerFactory;
import domain.user.User;
import view.InputView;
import view.OutputView;

public class BlackjackGame {
	private final CardDivider cardDivider;
	private final RuleChecker ruleChecker;
	private final List<User> users;
	private final Dealer dealer;

	public BlackjackGame() {
		this.cardDivider = new CardDivider();
		ruleChecker = new RuleChecker();
		dealer = new Dealer();
		users = PlayerFactory.create(InputView.inputNames());
	}

	public void run() {
		BettingBoard bettingBoard = betPlayers();
		List<User> allUsers = initAllUsers();
		initCards(allUsers);
		printBlackjackUsers(allUsers);
		checkCanDraw();
		OutputView.printUserResult(allUsers);
		showGameResult(bettingBoard);
	}

	private BettingBoard betPlayers() {
		List<Money> moneys = users.stream()
			.map(user -> Money.from(InputView.inputBettingMoney(user.getName())))
			.collect(Collectors.toList());
		return new BettingBoard(users, moneys);
	}

	private List<User> initAllUsers() {
		List<User> allUsers = new ArrayList<>(users);
		allUsers.add(dealer);
		return allUsers;
	}

	private void initCards(List<User> allUsers) {
		for (User user : allUsers) {
			user.addCards(Arrays.asList(cardDivider.divide(), cardDivider.divide()));
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
		drawCard(users, dealer);
	}

	private void drawCard(List<User> users, Dealer dealer) {
		drawPlayersCard(users);
		drawDealerCard(dealer);
	}

	private void drawPlayersCard(List<User> users) {
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

	private void showGameResult(BettingBoard bettingBoard) {
		DealerFinalScore dealerFinalScore = new DealerFinalScore(dealer);

		List<UserResult> userResults = createUserResults(dealerFinalScore, bettingBoard);
		DealerResult dealerResult = new DealerResult(userResults);

		OutputView.printGameResult(userResults, dealerResult);
	}

	private List<UserResult> createUserResults(DealerFinalScore dealerFinalScore, BettingBoard bettingBoard) {
		List<UserResult> userResults = new ArrayList<>();
		for (User user : users) {
			Money betMoney = bettingBoard.get(user);
			MatchResult matchResult = MatchResult.findMatchResult(new PlayerFinalScore(user), dealerFinalScore);
			FinalMoney finalMoney = matchResult.makeMoneyResult(betMoney);
			userResults.add(new UserResult(user.getName(), finalMoney.compare(betMoney)));
		}
		return userResults;
	}
}
