package domain.card;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CardFactoryTest {

	@Test
	void createTest() {
<<<<<<< HEAD
=======

>>>>>>> [Feat] Card 객체 추가
		List<Card> cards = new ArrayList<>();
		for (Type type : Type.values()) {
			for (Symbol symbol : Symbol.values()) {
				cards.add(new Card(symbol, type));
			}
		}

		assertThat(CardFactory.create()).isEqualTo(cards);
	}
<<<<<<< HEAD

	@Test
	void sizeTest() {
		int size = CardFactory.create().size();

		assertThat(size).isEqualTo(52);
	}

=======
>>>>>>> [Feat] Card 객체 추가
}
