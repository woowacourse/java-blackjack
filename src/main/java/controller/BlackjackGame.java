package controller;

import java.util.Arrays;
import java.util.List;

import domain.card.CardDivider;
import domain.user.Dealer;
import domain.user.PlayerFactory;
import domain.user.User;
import view.InputView;

public class BlackjackGame {
	private final CardDivider cardDivider;

	public BlackjackGame(CardDivider cardDivider) {
		this.cardDivider = cardDivider;
	}

	public void run() {
		List<User> users = PlayerFactory.create(InputView.inputNames());
		User dealer = new Dealer();
		initCards(users, dealer);
	}

	private void initCards(List<User> users, User dealer) {
		for (User user : users) {
			user.addCards(Arrays.asList(cardDivider.divide(), cardDivider.divide()));
		}
		dealer.addCards(Arrays.asList(cardDivider.divide(), cardDivider.divide()));
	}
}
