package domain.card;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

	@DisplayName("ACE를 포함하지 않는 경우")
	@Test
	void calculateScoreTest() {
		Cards cards = createCards(Arrays.asList(10, 10));
		assertThat(cards.calculateScore()).isEqualTo(20);
	}

	@DisplayName("ACE를 포함하는 경우, 10을 더했을 때 21을 넘지 않는 경우")
	@Test
	void calculateScoreTest2() {
		Cards cards = createCards(Arrays.asList(1, 10));
		assertThat(cards.calculateScore()).isEqualTo(21);
	}

	@DisplayName("ACE를 포함하는 경우, 10을 더했을 때 21을 넘는 경우")
	@Test
	void calculateScoreTest3() {
		Cards cards = createCards(Arrays.asList(1, 5, 6));
		assertThat(cards.calculateScore()).isEqualTo(12);
	}

	@DisplayName("ACE가 2개인 경우")
	@Test
	void calculateScoreTest4() {
		Cards cards = createCards(Arrays.asList(1, 1));
		assertThat(cards.calculateScore()).isEqualTo(12);
	}

	@DisplayName("블랙잭인 경우")
	@Test
	void isBlackjackTest1() {
		Cards cards = createCards(Arrays.asList(1, 10));
		assertThat(cards.isBlackjack()).isEqualTo(true);
	}

	@DisplayName("블랙잭 아닌 경우")
	@Test
	void isBlackjackTest2() {
		Cards cards = createCards(Arrays.asList(1, 10, 10));
		assertThat(cards.isBlackjack()).isEqualTo(false);
	}

	@Test
	void isBustTest() {
		Cards bustCards = createCards(Arrays.asList(2, 10, 10));
		Cards notBustCards = createCards(Arrays.asList(10, 10, 1));

		assertThat(bustCards.isBust()).isTrue();
		assertThat(notBustCards.isBust()).isFalse();
	}

	Cards createCards(List<Integer> numbers) {
		return numbers.stream()
			.map(num -> new Card(Symbol.CLOVER, Type.of(num)))
			.collect(Collectors.collectingAndThen(Collectors.toList(), Cards::new));
	}
}
