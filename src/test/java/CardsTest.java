import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

	@Test
	@DisplayName("K클로버와 2하트가 손에 있으면 12를 반환하는지")
	void Calculate_Correct_Summation_Of_K_And_2() {
		Cards cards = new Cards();
		cards.addCard(Map.of("K클로버", 10, "2하트", 2));
		assertThat(cards.sum()).isEqualTo(12);
	}

	@Test
	@DisplayName("에이스를 11로 적용하는지")
	void Select_Ace_Value_11(){
		Cards cards = new Cards();
		cards.addCard(Map.of("A클로버", 1, "2하트", 2, "7스페이드", 7));
		assertThat(cards.sum()).isEqualTo(20);
	}

	@Test
	@DisplayName("에이스를 1로 적용하는지")
	void Select_Ace_Value_1(){
		Cards cards = new Cards();
		cards.addCard(Map.of("A클로버", 1, "8하트", 8, "7스페이드", 7));
		assertThat(cards.sum()).isEqualTo(16);
	}
}
