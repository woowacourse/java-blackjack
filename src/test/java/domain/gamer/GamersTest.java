package domain.gamer;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GamersTest {
	@Test
	@DisplayName("게이머들 생성자 제대로 생성되는지 테스트")
	public void gamersInitTest() {
		String names = "pobi, json";
		Dealer dealer = new Dealer();
		Gamers gamers = new Gamers(names, dealer);

		assertThat(gamers).isNotNull();
	}
}