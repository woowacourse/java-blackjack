package controller;

import java.util.Arrays;
import java.util.List;

import domain.YesOrNo;
import domain.card.CardDivider;
import domain.user.Dealer;
import domain.user.PlayerFactory;
import domain.user.User;
import view.InputView;
import view.OutputView;

public class BlackjackGame {
	private final CardDivider cardDivider;

	public BlackjackGame(CardDivider cardDivider) {
		this.cardDivider = cardDivider;
	}

	public void run() {
		List<User> users = PlayerFactory.create(InputView.inputNames());
		Dealer dealer = new Dealer();
		initCards(users, dealer);
		drawCard(users, dealer);
	}

	private void initCards(List<User> users, Dealer dealer) {
		for (User user : users) {
			user.addCards(Arrays.asList(cardDivider.divide(), cardDivider.divide()));
		}
		dealer.addCards(Arrays.asList(cardDivider.divide(), cardDivider.divide()));
		OutputView.
	}

	private void drawCard(List<User> users, Dealer dealer) {
		for (User user : users) {
			if (user.isBlackjack()) {
				continue;
			}
			while (!user.isBust()) {
				YesOrNo yesOrNo = new YesOrNo(InputView.inputYesORNo(user.getName()));
				if (yesOrNo.isEnd()) {
					break;
				}
				user.addCards(Arrays.asList(cardDivider.divide()));
			}
			//출력
		}

		while (dealer.isDrawable()) {
			dealer.addCards(Arrays.asList(cardDivider.divide()));
			//출력
		}
	}
}
