package domain.card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class CardFactoryTest {
	@Test
	void create_CardsSizeIs52_PassToCreate() {
		assertThat(CardFactory.create().size()).isEqualTo(52);
	}
}
