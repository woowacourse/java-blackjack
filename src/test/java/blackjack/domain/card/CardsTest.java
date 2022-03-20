package blackjack.domain.card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CardsTest {

	@Test
	@DisplayName("Ace와 TEN을 받으면 합이 21이다.")
	void sum() {
		Cards cards = new Cards();
		cards.add(getAce());
		cards.add(getTen());

		assertThat(cards.sum()).isEqualTo(21);
	}

	@Test
	@DisplayName("숫자 합이 더 큰 Cards를 판별한다.")
	void greaterThan() {
		Cards cards1 = new Cards();
		cards1.add(getAce());
		cards1.add(getTen());

		Cards cards2 = new Cards();
		cards2.add(getAce());
		cards2.add(getAce());
		cards2.add(getTen());

		assertThat(cards2.isGreaterThan(cards1)).isTrue();
	}

	@Test
	@DisplayName("카드가 2장이고 번호 합이 21이면 블랙잭이다.")
	void isBlackJack() {
		Cards cards = new Cards();
		cards.add(getAce());
		cards.add(getTen());

		assertThat(cards.isBlackJack()).isTrue();
	}

	@Test
	@DisplayName("번호 합이 21을 초과하면 버스트이다.")
	void isBust() {
		Cards cards = new Cards();
		cards.add(getAce());
		cards.add(getAce());
		cards.add(getTen());

		assertThat(cards.isBust()).isTrue();
	}

	@Test
	@DisplayName("번호 합이 21 이하이면 노말이다..")
	void isNormal() {
		Cards cards = new Cards();
		cards.add(getAce());
		cards.add(getAce());

		assertThat(cards.isBust()).isFalse();
		assertThat(cards.isBlackJack()).isFalse();
	}

	@Test
	@DisplayName("번호 합이 21이어도 3장이면 노말이다.")
	void isNormalOver2() {
		Cards cards = new Cards();
		cards.add(getAce());
		cards.add(getFive());
		cards.add(getFive());

		assertThat(cards.isBust()).isFalse();
		assertThat(cards.isBlackJack()).isFalse();
	}

	@ParameterizedTest
	@CsvSource(value = {"1:1", "2:2", "3:3"}, delimiter = ':')
	@DisplayName("인자로 받은 개수만큼 카드를 보여준다.")
	void open(int count, int result) {
		Cards cards = new Cards();
		cards.add(getAce());
		cards.add(getFive());
		cards.add(getFive());

		assertThat(cards.open(count).size()).isEqualTo(result);
	}

	private Card getAce() {
		return Card.getInstance(CardShape.CLOVER, CardNumber.ACE);
	}

	private Card getTen() {
		return Card.getInstance(CardShape.CLOVER, CardNumber.TEN);
	}

	private Card getFive() {
		return Card.getInstance(CardShape.CLOVER, CardNumber.FIVE);
	}
}