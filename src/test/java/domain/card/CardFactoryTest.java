package domain.card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 *
 *    @author AnHyungJu, ParkDooWon
 */
@SuppressWarnings("NonAsciiCharacters")
public class CardFactoryTest {
	@Test
	void 생성_사이즈() {
		assertThat(CardFactory.create().size()).isEqualTo(52);
	}
}
