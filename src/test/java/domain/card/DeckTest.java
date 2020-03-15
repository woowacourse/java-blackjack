package domain.card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class DeckTest {
	@Test
	void deal_isInstanceOfCardClass_Pass() {
		assertThat(new Deck().deal()).isInstanceOf(Card.class);
	}
}
