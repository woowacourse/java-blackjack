package participant;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.*;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import card.Deck;
import duel.DuelResult;
import paticipant.Player;
import paticipant.Players;
import value.Count;

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

		@Nested
		@DisplayName("추가 카드 뽑기")
		class PickCardIfNotBust {

			@DisplayName("플레이어의 점수가 21점 이하라면, 플레이어의 응답에 따라 카드를 추가로 뽑는다.")
			@Test
			void pickCardIfNotBust() {
				// given
				final Players players = Players.from(List.of("부기"));
				final Function<String, Boolean> playerAnswer = (name) -> true;
				final Deck shuffledDeck = Deck.createShuffledDeck();

				// when
				players.pickCardPlayersIfNotBust(playerAnswer, x -> {
				}, shuffledDeck);

				// then
				assertThat(
					players.getPlayers()
						.getFirst()
						.getParticipant()
						.isBust()).isTrue();
			}
		}

		@Nested
		@DisplayName("딜러와의 듀얼")
		class Duel {

			@DisplayName("딜러와 듀얼을 진행한다.")
			@Test
			void duel() {
				// given
				final Players players = Players.from(List.of("부기", "구구", "파랑"));
				final Consumer<Player> duel = player -> player.writeDuelResult(DuelResult.WIN);
				final Count expected = Count.from(1);

				// when
				players.duelVsDealer(duel);

				// then
				assertSoftly(s -> {
					for (final Player player : players.getPlayers()) {
						s.assertThat(player.getParticipant().getDuelHistory().getWinCount()).isEqualTo(expected);
					}
				});
			}
		}
	}
}
