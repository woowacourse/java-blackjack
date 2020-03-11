package domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import domain.CardFactory;

public class CardFactoryTest {
	@Test
	void create() {
		assertThat(CardFactory.create().size()).isEqualTo(52);
	}
}
