package domain.gamer;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardSuit;
import exception.NameFormatException;

public class GamerTest {
	@Test
	@DisplayName("잘못된 이름 입력시 예외처리")
	void isValidNameTest() {
		assertThatThrownBy(() -> new Player("po/bi", "50")).isInstanceOf(NameFormatException.class);
	}

	@Test
	@DisplayName("점수가 제대로 들어가는지 확인하는 테스트")
	public void calculateTest() {
		Player player = new Player("pobi", "50");
		player.addCard(Arrays.asList(
			new Card(CardSuit.CLOVER, CardNumber.SEVEN),
			new Card(CardSuit.CLOVER, CardNumber.TEN))
		);
		assertThat(player.calculateScore()).isEqualTo(17);
	}

	@Test
	@DisplayName("에이스를 11로 계산했을때 21이 넘지 않은 경우 에이스를 11로 계산하는 테스트")
	void isAceHiddenCardTest() {
		Player player = new Player("pobi", "50");
		player.addCard(Arrays.asList(
			new Card(CardSuit.CLOVER, CardNumber.ACE),
			new Card(CardSuit.CLOVER, CardNumber.JACK))
		);
		assertThat(player.calculateScore()).isEqualTo(21);
	}

	@Test
	@DisplayName("에이스를 11로 계산했을때 21이 넘어서 에이스를 1로 계산하는 테스트")
	void name() {
		Player player = new Player("pobi", "50");
		player.addCard(Arrays.asList(
			new Card(CardSuit.CLOVER, CardNumber.ACE),
			new Card(CardSuit.CLOVER, CardNumber.JACK),
			new Card(CardSuit.HEART, CardNumber.SIX))
		);
		assertThat(player.calculateScore()).isEqualTo(17);
	}
}
