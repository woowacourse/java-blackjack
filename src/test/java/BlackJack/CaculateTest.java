package BlackJack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CaculateTest {

	@Test
	void changeTest() {
		
		Card card = new Card();
		Calculate calculate = new Calculate(card);
		String c = "스페이스K";
		int result = calculate.change(c);
		 assertThat(result).isEqualTo(10);
		
	}
	
	@Test
	void sumTest() {
		Card cards = new Card();
		cards.addCard("스페이스K");
		cards.addCard("하트A");
		cards.addCard("클로버8");
		
		Calculate calculate = new Calculate(cards);
		int sum = calculate.sum();
		
		assertThat(sum).isEqualTo(19);
	}

}
