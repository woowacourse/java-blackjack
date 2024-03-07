package domain.gamer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;

public class PlayerTest {

	@DisplayName("이름의 길이가 1 이상 5 이하라면 정상적으로 생성된다.")
	@ParameterizedTest
	@ValueSource(strings = {"a", "abcde"})
	void playerNameLengthSuccessTest(String name) {
		assertThatCode(() -> Player.newInstance(name))
			.doesNotThrowAnyException();
	}

	@DisplayName("이름의 길이가 1 미만 또는 5 초과이면 예외를 발생시킨다.")
	@ParameterizedTest
	@ValueSource(strings = {"", "abcdef"})
	void playerNameLengthErrorTest(String name) {
		assertThatThrownBy(() -> Player.newInstance(name))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("이름의 길이는 1 이상 5 이하이어야 합니다.");
	}

	@DisplayName("초기 카드 2장을 받는다.")
	@Test
	void receiveInitCardsTest() {
		// given
		Player player = Player.newInstance("a");

		// when
		player.receiveInitCards(List.of(new Card(Suit.HEART, Rank.ACE), new Card(Suit.HEART, Rank.JACK)));

		// then
		assertThat(player.getCardHand()).hasSize(2);
	}

	@DisplayName("카드 1장을 받는다.")
	@Test
	void receiveCardTest() {
		// given
		Player player = Player.newInstance("a");

		// when
		player.receiveCard(new Card(Suit.HEART, Rank.ACE));

		// then
		assertThat(player.getCardHand()).hasSize(1);
	}

	@DisplayName("가지고 있는 패의 총 합이 21을 초과하면 true를 반환한다.")
	@Test
	void cardValueSumTest() {
		// given
		Player player = Player.newInstance("a");
		player.receiveCard(new Card(Suit.HEART, Rank.KING));
		player.receiveCard(new Card(Suit.HEART, Rank.JACK));
		player.receiveCard(new Card(Suit.HEART, Rank.QUEEN));

		// when & then
		assertThat(player.isBust()).isTrue();
	}
}
