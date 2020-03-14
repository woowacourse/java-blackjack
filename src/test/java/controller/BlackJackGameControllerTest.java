package controller;

import domain.card.CardDeck;
import domain.player.Dealer;
import domain.player.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class BlackJackGameControllerTest {
	@DisplayName("입력된 이름들로 유저가 올바르게 생성되는지 테스트")
	@Test
	void makeUsers() {
		String names = "a,b,c";
		List<User> result = new ArrayList<>();
		result.add(new User("a"));
		result.add(new User("b"));
		result.add(new User("c"));

		assertThat(BlackJackGameController.makeUsers(names)).isEqualTo(result);
	}

	@DisplayName("공란입력시 에러 테스트")
	@Test
	void makeUsersErrorTest() {
		String names = "a,,b";
		assertThatThrownBy(() -> BlackJackGameController.makeUsers(names))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("이름은 공란이 될 수 없습니다.");

	}

	@DisplayName("딜러가 16이하일 경우 계속해서 카드를 받는지")
	@Test
	void dealerHit() {
		CardDeck cardDeck = new CardDeck();
		Dealer dealer = new Dealer();

		BlackJackGameController.dealerHit(dealer, cardDeck);

		assertThat(dealer.calculateScore() > 16).isTrue();
	}
}
