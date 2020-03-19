package domain.card;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.score.Score;

public class CardsTest {

	@DisplayName("ACE를 포함하지 않는 경우")
	@Test
	void calculateScoreTest() {
		Cards cards = createCards(Arrays.asList(10, 10));
		assertThat(cards.calculateScore()).isEqualTo(Score.ofValue(20));
	}

	@DisplayName("ACE를 포함하는 경우, 11인 경우")
	@Test
	void calculateScoreTest2() {
		Cards cards = createCards(Arrays.asList(1, 10));
		assertThat(cards.calculateScore()).isEqualTo(Score.ofValue(21));
	}

	@DisplayName("ACE를 포함하는 경우, 12인 경우")
	@Test
	void calculateScoreTest3() {
		Cards cards = createCards(Arrays.asList(1, 5, 6));
		assertThat(cards.calculateScore()).isEqualTo(Score.ofValue(12));
	}

	@DisplayName("ACE가 2개인 경우")
	@Test
	void calculateScoreTest4() {
		Cards cards = createCards(Arrays.asList(1, 1));
		assertThat(cards.calculateScore()).isEqualTo(Score.ofValue(12));
	}

	@DisplayName("여러장의 에이스가 포함된 경우, 나머지 점수의 합이 10점이 넘어간다면, 에이스는 1점을 가져온다.")
	@Test
	void calculateScoreTest5() {
		Cards cards = createCards(Arrays.asList(10, 1, 1, 1));
		assertThat(cards.calculateScore()).isEqualTo(Score.ofValue(13));
	}

	@DisplayName("여러장의 에이스가 포함된 경우, 나머지 점수의 합이 10점이 넘어가지 않는다면, 에이스중 한장은 11점을 가져온다.")
	@Test
	void calculateScoreTest6() {
		Cards cards = createCards(Arrays.asList(1, 1, 1));
		assertThat(cards.calculateScore()).isEqualTo(Score.ofValue(13));
	}

	@DisplayName("블랙잭인 경우, 블랙잭 참 반환")
	@Test
	void isBlackjackTest1() {
		Cards cards = createCards(Arrays.asList(1, 10));
		assertThat(cards.isBlackjack()).isEqualTo(true);
	}

	@DisplayName("블랙잭 아닌 경우, 블랙잭 유무 거짓 반환")
	@Test
	void isBlackjackTest2() {
		Cards cards = createCards(Arrays.asList(1, 10, 2));
		assertThat(cards.isBlackjack()).isEqualTo(false);
	}

	@DisplayName("블랙잭 아닌 경우, 블랙잭 유무 거짓 반환")
	@Test
	void isBlackjackTest3() {
		Cards cards = createCards(Arrays.asList(1, 5, 6));
		assertThat(cards.isBlackjack()).isEqualTo(false);
	}

	@Test
	void isBustTest() {
		Cards cards = createCards(Arrays.asList(2, 10, 10));
		assertThat(cards.isBust()).isTrue();
	}

	Cards createCards(List<Integer> numbers) {
		return numbers.stream()
			.map(num -> new Card(Type.CLOVER, Symbol.of(num)))
			.collect(Collectors.collectingAndThen(Collectors.toList(), Cards::new));
	}
}
