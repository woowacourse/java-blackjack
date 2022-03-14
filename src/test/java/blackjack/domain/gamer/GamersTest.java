package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.domain.BlackJackGame;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.dto.GamerDto;

class GamersTest {

	@Test
	@DisplayName("중복된 이름이 있으면 예외가 발생한다.")
	void validateDuplicationName() {
		assertThatThrownBy(() -> new Gamers(List.of("a", "a"), s -> 1))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("중복된 이름이 존재합니다.");
	}

	@Test
	@DisplayName("이름으로 플레이어를 찾아낸다.")
	void findPlayerByName() {
		Gamers gamers = new Gamers(List.of("pobi", "jason"), s -> 1);
		Player jason = gamers.findPlayerByName("jason");
		assertThat(jason.isSameName("jason")).isTrue();
	}

	@Test
	@DisplayName("모든 플레이어의 이름을 찾아낸다.")
	void findPlayerNames() {
		Gamers gamers = new Gamers(List.of("pobi", "jason"), s -> 1);
		List<String> names = gamers.findPlayerNames();
		assertThat(names).containsExactly("pobi", "jason");
	}

	@ParameterizedTest
	@CsvSource(value = {"TWO:true", "ACE:false"}, delimiter = ':')
	@DisplayName("이름을 입력 받아, 해당 플레이어가 카드 총 합이 21이 넘는 버스트 상태인지 확인한다.")
	void isBurst(String input, boolean result) {
		Gamers gamers = new Gamers(List.of("pobi", "jason"), s -> 1);
		Player pobi = gamers.findPlayerByName("pobi");

		pobi.addCard(Card.getInstance(CardShape.DIAMOND, CardNumber.KING));
		pobi.addCard(Card.getInstance(CardShape.CLOVER, CardNumber.KING));
		pobi.addCard(Card.getInstance(CardShape.HEART, CardNumber.valueOf(input)));

		assertThat(pobi.isOverThan(21)).isEqualTo(result);
	}
}