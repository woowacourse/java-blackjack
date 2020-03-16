package blackjack.domain.user;

import blackjack.domain.card.*;
import blackjack.domain.user.exceptions.PlayersException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayersTest {
	private static final String KUENI = "그니";
	private static final String KUENI_POBI = "그니,포비";
	private static final String KUENI_POBI_SUMMER = "그니,포비, 서머";
	private static final String ENGLISH_SIX = "a,b,c, d,e,f";
	private static final String NULL = null;
	private static final String HAS_DUPLICATION = "포비, 그니, 서머, 그니, 씨유";

	@ParameterizedTest
	@ValueSource(strings = {KUENI, KUENI_POBI, KUENI_POBI_SUMMER, ENGLISH_SIX})
	void of_IsNotNull(String playerNames) {
		assertThat(Players.of(playerNames)).isNotNull();
	}

	@ParameterizedTest
	@MethodSource("of_ThrowPlayersException")
	void of_ThrowPlayersException(String invalidNames) {
		assertThatThrownBy(() -> Players.of(invalidNames))
				.isInstanceOf(PlayersException.class);
	}

	static Stream<String> of_ThrowPlayersException() {
		return Stream.of(NULL, HAS_DUPLICATION);
	}

	@Test
	void giveCardsEachPlayer() {
		// given
		Players players = Players.of("포비, 그니, 씨유");
		Card aceClub = Card.of(Symbol.ACE, Type.CLUB);
		Card jackHeart = Card.of(Symbol.JACK, Type.HEART);
		Card kingDiamond = Card.of(Symbol.KING, Type.DIAMOND);
		Drawable deck = Deck.of(Arrays.asList(aceClub, jackHeart, kingDiamond));

		// when
		players.giveCardEachPlayer(deck);

		// then
		assertThat(players.getPlayers().get(0).getHand().get(0))
				.isEqualTo(kingDiamond);
		assertThat(players.getPlayers().get(1).getHand().get(0))
				.isEqualTo(jackHeart);
		assertThat(players.getPlayers().get(2).getHand().get(0))
				.isEqualTo(aceClub);
	}

	@ParameterizedTest
	@CsvSource(value = {KUENI + ":1", KUENI_POBI + ":2", KUENI_POBI_SUMMER + ":3", ENGLISH_SIX + ":6"},
			delimiter = ':')
	void memberSize(String members, int size) {
		Players players = Players.of(members);
		assertThat(players.memberSize()).isEqualTo(size);
	}
}