package blackjack.domain.card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardStatusTest {

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