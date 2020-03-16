package blackjack.domain.user;

import blackjack.domain.card.*;
import blackjack.domain.user.exceptions.PlayersException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
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
	void of_ValidNames_IsNotNull(String playerNames) {
		assertThat(Players.of(playerNames)).isNotNull();
	}

	@Test
	void of_Null_ThrowPlayersException() {
		assertThatThrownBy(() -> Players.of(NULL))
				.isInstanceOf(PlayersException.class);
	}

	@Test
	void of_HasDuplication_ThrowPlayersException() {
		assertThatThrownBy(() -> Players.of(HAS_DUPLICATION))
				.isInstanceOf(PlayersException.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"0,2", "1,1", "2,0"})
	void giveCardsEachPlayer_ReceiveThreeCard_EachPlayerHasItselfsCard(int playerIndex, int cardIndex) {
		// given
		Players players = Players.of(KUENI_POBI_SUMMER);
		Card aceClub = Card.of(Symbol.ACE, Type.CLUB);
		Card jackHeart = Card.of(Symbol.JACK, Type.HEART);
		Card kingDiamond = Card.of(Symbol.KING, Type.DIAMOND);

		List<Card> cards = Arrays.asList(aceClub, jackHeart, kingDiamond);
		Drawable deck = Deck.of(cards);

		// when
		players.giveCardEachPlayer(deck);

		// then
		assertThat(players.getPlayers().get(playerIndex).getHand().get(0))
				.isEqualTo(cards.get(cardIndex));
	}

	@ParameterizedTest
	@CsvSource(value = {KUENI + ":1", KUENI_POBI + ":2", KUENI_POBI_SUMMER + ":3", ENGLISH_SIX + ":6"},
			delimiter = ':')
	void memberSize_PlayerNames_HasMemberSize(String PlayerNames, int memberSize) {
		Players players = Players.of(PlayerNames);
		assertThat(players.memberSize()).isEqualTo(memberSize);
	}
}