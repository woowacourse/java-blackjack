package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;

class PlayerStatusTest {

	@Test
	@DisplayName("21점에서는 Bust가 아닌 상태여야 한다.")
	public void score21IsNotBustTest() {
		//given
		List<Card> cards = List.of(
			new Card(Suit.SPADE, Denomination.NINE),
			new Card(Suit.DIAMOND, Denomination.NINE));
		PlayerStatus status = new PlayerStatus(0);
		cards.forEach(status::addCard);

		//when
		status.addCard(new Card(Suit.SPADE, Denomination.THREE));

		//then
		assertFalse(status.isBust());
	}

	@Test
	@DisplayName("22점에서는 Bust 상태여야 한다.")
	public void score22IsBustTest() {
		//given
		List<Card> cards = List.of(
			new Card(Suit.SPADE, Denomination.NINE),
			new Card(Suit.DIAMOND, Denomination.NINE));
		PlayerStatus status = new PlayerStatus(0);
		cards.forEach(status::addCard);

		//when
		status.addCard(new Card(Suit.SPADE, Denomination.FOUR));

		//then
		assertFalse(status.canReceiveCard());
	}

	@Test
	@DisplayName("21점에서는 카드를 추가로 수령 가능한 상태여야 한다.")
	public void score21receivableTest() {
		//given
		List<Card> cards = List.of(
			new Card(Suit.SPADE, Denomination.NINE),
			new Card(Suit.DIAMOND, Denomination.NINE));
		PlayerStatus status = new PlayerStatus(0);
		cards.forEach(status::addCard);

		//when
		status.addCard(new Card(Suit.SPADE, Denomination.THREE));

		//then
		assertTrue(status.canReceiveCard());
	}

	@Test
	@DisplayName("22점에서는 카드를 추가로 수령 불가능한 상태여야 한다.")
	public void score22NotReceivableTest() {
		//given
		List<Card> cards = List.of(
			new Card(Suit.SPADE, Denomination.NINE),
			new Card(Suit.DIAMOND, Denomination.NINE));
		PlayerStatus status = new PlayerStatus(0);
		cards.forEach(status::addCard);

		//when
		status.addCard(new Card(Suit.SPADE, Denomination.FOUR));

		//then
		assertFalse(status.canReceiveCard());
	}

	@Test
	@DisplayName("첫 손패가 21점인 경우 BlackJack 상태여야 한다.")
	public void initialCards21IsBlackJackTest() {
		//given
		List<Card> cards = List.of(
			new Card(Suit.SPADE, Denomination.ACE),
			new Card(Suit.SPADE, Denomination.TEN));
		PlayerStatus status = new PlayerStatus(0);

		//when
		cards.forEach(status::addCard);

		// then
		assertTrue(status.isBlackJack());
	}

	@Test
	@DisplayName("첫 손패 이후 추가 카드까지 21점인 경우 BlackJack 상태가 아니여야 한다.")
	public void score21WithAdditionalCardsIsNotBlackJackTest() {
		//given
		List<Card> cards = List.of(
			new Card(Suit.SPADE, Denomination.ACE),
			new Card(Suit.SPADE, Denomination.NINE));
		PlayerStatus status = new PlayerStatus(0);
		cards.forEach(status::addCard);

		//when
		Card thirdCard = new Card(Suit.SPADE, Denomination.ACE);
		status.addCard(thirdCard);

		// then
		assertFalse(status.isBlackJack());
	}
}
