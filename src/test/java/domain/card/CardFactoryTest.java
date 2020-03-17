package domain.card;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CardFactoryTest {

	@Test
	void createTest() {
		List<Card> cards = new ArrayList<>();
		for (Symbol symbol : Symbol.values()) {
			for (Type type : Type.values()) {
				cards.add(new Card(type, symbol));
			}
		}

		assertThat(CardFactory.getInstance()).isEqualTo(cards);
	}

	@Test
	void sizeTest() {
		int size = CardFactory.getInstance().size();
		assertThat(size).isEqualTo(52);
	}

}
