package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

	private Deck deck;

	@BeforeEach
	void setUp() {
		deck = Deck.initializeDeck();
	}

	@Test
	@DisplayName("Deck에서 카드를 한 장 뽑는다.")
	void drawCard() {
		assertThat(deck.draw()).isInstanceOf(Card.class);
	}

	@Test
	@DisplayName("Deck에 카드가 없을 때 에러가 발생한다.")
	void drawCardExceptionWhenNoCard() {
		IntStream.range(0, 52)
			.forEach(index -> deck.draw());

		assertThatThrownBy(() -> deck.draw())
			.isInstanceOf(IllegalStateException.class)
			.hasMessage("[ERROR] 더이상 카드를 뽑을 수 없습니다.");
	}
}
