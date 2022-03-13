package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackTest {

	@Test
	@DisplayName("참가자의 수가 8명을 초과하면 예외가 발생한다")
	void playerNumberException_8() {
		assertThatThrownBy(() -> {
			List<String> playerNames = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i");
			BlackJack.createFrom(playerNames);
		}).isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 참가자의 수는 8명을 초과할 수 없습니다.");
	}

	@Test
	@DisplayName("카드를 목표 플레이어에게만 한 장 나누어 준다")
	void handOutCardTo() {
		List<String> playerNames = Arrays.asList("a", "b");
		BlackJack blackJack = BlackJack.createFrom(playerNames);
		blackJack.handOutCardTo(blackJack.getPlayers().get(0));
		assertThat(blackJack.getPlayers().get(0).getCards().getCards().size()).isEqualTo(1);
		assertThat(blackJack.getPlayers().get(1).getCards().getCards().size()).isEqualTo(0);
	}

	@Test
	@DisplayName("starting card는 2장씩 나누어준다")
	void handOutStartingCards() {
		List<String> playerNames = Arrays.asList("a", "b");
		BlackJack blackJack = BlackJack.createFrom(playerNames);
		blackJack.handOutStartingCards();
		assertThat(blackJack.getDealer().getCards().getCards().size()).isEqualTo(2);
		assertThat(blackJack.getPlayers().get(0).getCards().getCards().size()).isEqualTo(2);
		assertThat(blackJack.getPlayers().get(1).getCards().getCards().size()).isEqualTo(2);
	}
}
