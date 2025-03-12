package domain.participant;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import domain.card.Deck;
import domain.paticipant.Player;
import domain.paticipant.Players;

public class PlayersTest {

	@Nested
	@DisplayName("Players 생성")
	class ConstructPlayers {

		@DisplayName("이름들로 플레이어들을 생성한다.")
		@Test
		void constructPlayers() {
			// given
			final List<String> names = List.of("부기", "구구", "파랑");

			// when
			final var players = Players.from(names);

			// then
			assertThat(players.getPlayers()).hasSize(3);
		}
	}

	@Nested
	@DisplayName("주어진 수만큼, 플레이어들에게 덱에서 카드를 분배한다.")
	class PickCards {

		@DisplayName("주어진 수만큼, 플레이어들에게 덱에서 카드를 분배해준다.")
		@Test
		void pickCards() {
			// given
			final List<String> names = List.of("부기", "구구", "파랑");
			final var players = Players.from(names);
			final var deck = Deck.createShuffledDeck();
			final var pickedCardCount = 2;

			// when
			players.pickCards(deck, pickedCardCount);

			// then
			assertSoftly(s -> {
				for (final Player player : players.getPlayers()) {
					s.assertThat(player.getParticipant().getCardHand().getCards()).hasSize(pickedCardCount);
				}
			});

		}
	}
}
