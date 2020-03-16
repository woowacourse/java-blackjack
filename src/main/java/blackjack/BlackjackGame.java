package blackjack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import domain.YesOrNo;
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
	private final List<User> users;
	private final Dealer dealer;

	public BlackjackGame() {
		users = PlayerFactory.create(InputView.inputNames());
		dealer = new Dealer();
		this.cardDivider = new CardDivider();
	}

	public void run() {
		List<User> allUsers = initAllUsers();
		initCards(allUsers);
		printBlackjackUsers(allUsers);
		checkCanDraw();
		OutputView.printUserResult(allUsers);
		showGameResult();
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
			.filter(User::isBlackjack)
			.collect(Collectors.toList());
		OutputView.printBlackJackUser(blackjackUsers);
	}

	private void checkCanDraw() {
		if (dealer.isBlackjack()) {
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
		if (user.isBlackjack()) {
			return;
		}
		while (isUserNotBust(user) && isContinuousFromInput(user)) {
			user.addCards(Arrays.asList(cardDivider.divide()));
			OutputView.printUserCard(user);
		}
	}

	private boolean isUserNotBust(User user) {
		if (!user.isBust()) {
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
		return users.stream()
			.map(user -> new UserResult(user, MatchResult
				.findMatchResult(new PlayerFinalScore(user), dealerFinalScore)))
			.collect(Collectors.toList());
	}
}
