package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 *
 *    @author AnHyungJu, ParkDooWon
 */
@SuppressWarnings("NonAsciiCharacters")
public class DeckTest {
	@Test
	void 카드_나누기() {
		assertThat(new Deck().deal()).isInstanceOf(Card.class);
	}
}
