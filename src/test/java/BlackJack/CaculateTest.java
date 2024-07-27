package BlackJack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CaculateTest {

	@Test
	void changeTest() {
		
		Calculate calculate = new Calculate();
		String c = "스페이스5";
		int result = calculate.change(c);
		 assertThat(result).isEqualTo(5);
		
	}

}
