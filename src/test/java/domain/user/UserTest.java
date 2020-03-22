package domain.user;

import domain.card.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.junit.jupiter.api.Assertions.*;

import static domain.user.User.NULL_CAN_NOT_BE_A_PARAMETER_EXCEPTION_MESSAGE;

public class UserTest {

	@DisplayName("addCard() 정상 동작 확인")
	@Test
	void addCard() {
		Card card = new Card(Symbol.ACE, Type.CLOVER);
		User user = User.getInstance();
		user.addCard(card);

		assertEquals(user, User.of(Collections.singletonList(card)));
	}

	@DisplayName("addInitialCards가 두장의 카드를 나눠주는지 확인")
	@Test
	void addInitialCards_User_Should_Have_Two_Cards() {
		Deck deck = new Deck(CardRepository.toList());
		User user = User.getInstance();
		user.addInitialCards(deck);

		assertThat(user.cards.toList().size()).isEqualTo(2);
	}

	@DisplayName("addCard가 정상적인 Deck에 대해 한장의 카드를 뽑아서 User에 추가하는지 확인")
	@Test
	void addCard_Add_One_Card_When_Valid_Deck() {
		Deck deck = new Deck(CardRepository.toList());
		User user = User.getInstance();
		user.addCard(deck);

		assertThat(user.cards.toList().size()).isEqualTo(1);
	}

	@DisplayName("addCard가 null인 Deck에 대해 예외처리")
	@ParameterizedTest
	@NullSource
	void addCard_Add_One_Card_When_Invalid_Deck(Deck nullDeck) {
		User user = User.getInstance();

		assertThatNullPointerException().isThrownBy(() -> {
			user.addCard(nullDeck);
		}).withMessage(NULL_CAN_NOT_BE_A_PARAMETER_EXCEPTION_MESSAGE);
	}

	@DisplayName("openAllCards가 두장의 카드를 반환하는지 확인")
	@Test
	void openAllCards_When_User_Has_Ace_Heart_And_Two_Heart_Return_Both() {
		User user = new User();
		Card card1 = new Card(Symbol.ACE, Type.HEART);
		Card card2 = new Card(Symbol.TWO, Type.HEART);
		Cards cards = new Cards(new ArrayList<>());
		cards.add(card1);
		cards.add(card2);

		user.addCard(card1);
		user.addCard(card2);

		assertEquals(cards, user.openAllCards());
	}

	@DisplayName("플레이어가 17점일 경우 canDrawMore()가 true 반환")
	@Test
	void canDrawMore_When_Player_Score_Is_17_Return_True() {
		Card card1 = new Card(Symbol.QUEEN, Type.HEART);
		Card card2 = new Card(Symbol.SEVEN, Type.HEART);

		User player = new Player("toney", 10);
		player.addCard(card1);
		player.addCard(card2);

		assertTrue(player.canDrawMore());
	}

	@DisplayName("플레이어가 22점일 경우 canDrawMore()가 false 반환")
	@Test
	void canDrawMore_When_Player_Score_Is_22_Return_False() {
		Card card1 = new Card(Symbol.QUEEN, Type.HEART);
		Card card2 = new Card(Symbol.SEVEN, Type.HEART);
		Card card3 = new Card(Symbol.FIVE, Type.HEART);

		User player = new Player("toney", 10);
		player.addCard(card1);
		player.addCard(card2);
		player.addCard(card3);

		assertFalse(player.canDrawMore());
	}

	@DisplayName("딜러가 16점일 경우 canDrawMore()가 true 반환")
	@Test
	void canDrawMore_When_Dealer_Score_Is_16_Return_True() {
		Card card1 = new Card(Symbol.QUEEN, Type.HEART);
		Card card2 = new Card(Symbol.SIX, Type.HEART);

		User dealer = new Dealer();
		dealer.addCard(card1);
		dealer.addCard(card2);

		assertTrue(dealer.canDrawMore());
	}

	@DisplayName("딜러가 17점일 경우 canDrawMore()가 false 반환")
	@Test
	void canDrawMore_When_Dealer_Score_Is_17_Return_False() {
		Card card1 = new Card(Symbol.QUEEN, Type.HEART);
		Card card2 = new Card(Symbol.SEVEN, Type.HEART);

		User dealer = new Dealer();
		dealer.addCard(card1);
		dealer.addCard(card2);

		assertFalse(dealer.canDrawMore());
	}

	@DisplayName("유저가 21점이고 두장의 카드를 가질 경우 isNotBlackjack()가 false 반환")
	@Test
	void isNotBlackjack_When_User_Score_21_And_Two_Cards_Return_False() {
		Card card1 = new Card(Symbol.QUEEN, Type.HEART);
		Card card2 = new Card(Symbol.ACE, Type.HEART);

		User dealer = new Dealer();
		dealer.addCard(card1);
		dealer.addCard(card2);

		assertFalse(dealer.isNotBlackjack());
	}

	@DisplayName("유저가 21점이고 세장의 카드를 가질 경우 isNotBlackjack()가 true 반환")
	@Test
	void isNotBlackjack_When_User_Score_21_And_Three_Cards_Return_True() {
		Card card1 = new Card(Symbol.QUEEN, Type.HEART);
		Card card2 = new Card(Symbol.KING, Type.HEART);
		Card card3 = new Card(Symbol.ACE, Type.HEART);

		User dealer = new Dealer();
		dealer.addCard(card1);
		dealer.addCard(card2);
		dealer.addCard(card3);

		assertTrue(dealer.isNotBlackjack());
	}

	@DisplayName("유저가 20점이고 두장의 카드를 가질 경우 isNotBlackjack()가 true 반환")
	@Test
	void isNotBlackjack_When_User_Score_20_And_Two_Cards_Return_True() {
		Card card1 = new Card(Symbol.QUEEN, Type.HEART);
		Card card2 = new Card(Symbol.KING, Type.HEART);

		User dealer = new Dealer();
		dealer.addCard(card1);
		dealer.addCard(card2);

		assertTrue(dealer.isNotBlackjack());
	}
}
