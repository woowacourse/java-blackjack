package domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;

class ScoreByCardsTest {

	@Test
	@DisplayName("ACE를 11로 고려한 21 이하의 점수 생성을 테스트")
	public void testScoreOfUnder21() {
		//given
		ScoreByCards scoreByCards = ScoreByCards.empty();
		scoreByCards.add(new Card(Suit.CLOVER, Denomination.ACE));

		//when
		int value = scoreByCards.getScore();

		//then
		assertThat(value).isEqualTo(11);
	}

	@Test
	@DisplayName("ACE를 1개를 1로 고려한 포함된 21점 생성을 테스트")
	public void testScoreOf21() {
		//given
		ScoreByCards scoreByCards = ScoreByCards.empty();
		List<Card> cards = List.of(
			new Card(Suit.CLOVER, Denomination.ACE),
			new Card(Suit.CLOVER, Denomination.TEN),
			new Card(Suit.DIAMOND, Denomination.TEN));
		cards.forEach(scoreByCards::add);

		//when
		int value = scoreByCards.getScore();

		//then
		assertThat(value).isEqualTo(21);
	}

	@Test
	@DisplayName("ACE 2개를 1로 고려한 21점 생성을 테스트")
	public void testScoreContainsTwoAceOf21() {
		//given
		ScoreByCards scoreByCards = ScoreByCards.empty();
		List<Card> cards = List.of(
			new Card(Suit.CLOVER, Denomination.ACE),
			new Card(Suit.DIAMOND, Denomination.ACE),
			new Card(Suit.DIAMOND, Denomination.TEN),
			new Card(Suit.CLOVER, Denomination.NINE));
		cards.forEach(scoreByCards::add);

		//when
		int value = scoreByCards.getScore();

		//then
		assertThat(value).isEqualTo(21);
	}

	@Test
	@DisplayName("ACE 2개를 1로, 1개를 11로 고려한 21점 생성을 테스트")
	public void testScoreContainsThreeAceOf21() {
		//given
		ScoreByCards scoreByCards = ScoreByCards.empty();
		List<Card> cards = List.of(
			new Card(Suit.CLOVER, Denomination.ACE),
			new Card(Suit.DIAMOND, Denomination.ACE),
			new Card(Suit.SPADE, Denomination.ACE),
			new Card(Suit.CLOVER, Denomination.EIGHT));
		cards.forEach(scoreByCards::add);

		//when
		int value = scoreByCards.getScore();

		//then
		assertThat(value).isEqualTo(21);
	}

	@Test
	@DisplayName("ACE 3개를 1로, 1개를 11로 고려한 21점 생성을 테스트")
	public void testScoreContainsFourAceOf21() {
		//given
		ScoreByCards scoreByCards = ScoreByCards.empty();
		List<Card> cards = List.of(
			new Card(Suit.SPADE, Denomination.ACE),
			new Card(Suit.DIAMOND, Denomination.ACE),
			new Card(Suit.CLOVER, Denomination.ACE),
			new Card(Suit.HEART, Denomination.ACE),
			new Card(Suit.CLOVER, Denomination.SEVEN));
		cards.forEach(scoreByCards::add);

		//when
		int value = scoreByCards.getScore();

		//then
		assertThat(value).isEqualTo(21);
	}

	@Test
	@DisplayName("ACE 4개를 1로 고려한 21점 초과를 테스트")
	public void testScoreContainsFourAceOf24() {
		//given
		ScoreByCards scoreByCards = ScoreByCards.empty();
		List<Card> cards = List.of(
			new Card(Suit.SPADE, Denomination.ACE),
			new Card(Suit.DIAMOND, Denomination.ACE),
			new Card(Suit.CLOVER, Denomination.ACE),
			new Card(Suit.HEART, Denomination.ACE),
			new Card(Suit.SPADE, Denomination.TEN),
			new Card(Suit.DIAMOND, Denomination.TEN));
		cards.forEach(scoreByCards::add);

		//when
		int value = scoreByCards.getScore();

		//then
		assertThat(value).isEqualTo(24);
	}

	@Test
	@DisplayName("처음 두 장의 점수가 21일 때 BlackJack인지 테스트")
	public void testFirstTwo21IsBlackJack() {
		//given
		ScoreByCards scoreByCards = ScoreByCards.empty();
		List<Card> cards = List.of(
			new Card(Suit.SPADE, Denomination.ACE),
			new Card(Suit.SPADE, Denomination.TEN));
		cards.forEach(scoreByCards::add);

		//when & then
		assertTrue(scoreByCards.isBlackJack());
	}
}
