package controller;

import java.util.Arrays;
import java.util.List;

import domain.YesOrNo;
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
		users.add(new Dealer());
		initCards(users);
	}

	private void initCards(List<User> users) {
		for (User user : users) {
			user.addCards(Arrays.asList(cardDivider.divide(), cardDivider.divide()));
		}
	}

	private void drawCard(List<User> users) {
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
			//[Feat] 카드 추가 분배 기능 추가
		}

	}
}
