package domain.card;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

	@Test
	@DisplayName("DTO 테스트")
	void getInfo() {
		Card card = new Card(Rank.RANK_ACE, Suit.CLOVER);

		assertThat(card.getInfo().getRank()).isEqualTo(Rank.RANK_ACE);
		assertThat(card.getInfo().getSuit()).isEqualTo(Suit.CLOVER);
	}
}
