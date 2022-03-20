package blackjack.studyTest;

import static blackjack.util.Fixtures.CLOVER_ACE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.game.Money;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Map Key 학습 테스트")
class MapKeyTest {

	static class PlayerTest {
		public final String name;
		public List<Card> cards;

		public PlayerTest(final String name, final List<Card> cards) {
			this.name = name;
			this.cards = cards;
		}

		public void draw(Card card) {
			cards.add(card);
		}

		@Override
		public boolean equals(final Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			final PlayerTest that = (PlayerTest) o;
			return Objects.equals(name, that.name) && Objects.equals(cards, that.cards);
		}

		@Override
		public int hashCode() {
			return Objects.hash(name, cards);
		}
	}

	@Test
	@DisplayName("플레이어가 Map의 키로 이용될 때 플레이어의 상태 및 해시코드가 변경되면 해당 Key를 통한 접근에 예외 발생")
	void map_Key_Exception() {
		PlayerTest player = new PlayerTest("player", new ArrayList<>());
		Map<PlayerTest, Money> monies = new HashMap<>();
		monies.put(player, new Money(1000));
		player.draw(CLOVER_ACE);
		assertThat(monies.containsKey(player)).isFalse();
	}
}
