package blackjack.domain.user;

import static blackjack.util.StringUtil.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import blackjack.domain.exceptions.InvalidPlayerException;

class PlayerFactoryTest {
	@Test
	void create_PlayerNames_GeneratePlayerList() {
		List<Player> players = Arrays.asList(
			new Player("pobi"),
			new Player("sony"),
			new Player("stitch"));

		assertThat(PlayerFactory.create(parsingPlayerNames("pobi, sony, stitch"))).isEqualTo(players);
	}

	@Test
	void validateDuplication_DuplicatePlayerNames_InvalidPlayerExceptionThrown() {
		assertThatThrownBy(() -> PlayerFactory.create(parsingPlayerNames("pobi, pobi, stitch")))
			.isInstanceOf(InvalidPlayerException.class)
			.hasMessage(InvalidPlayerException.DUPLICATE_PLAYER);
	}

	@Test
	void validateDuplication_DuplicateDealerName_InvalidPlayerExceptionThrown() {
		assertThatThrownBy(() -> PlayerFactory.create(parsingPlayerNames("Dealer, pobi, stitch")))
			.isInstanceOf(InvalidPlayerException.class)
			.hasMessage(InvalidPlayerException.DUPLICATE_DEALER);
	}

	@Test
	void validateSize_PlayerNamesOverMaximumSize_InvalidPlayerExceptionThrown() {
		assertThatThrownBy(() -> PlayerFactory.create(
			parsingPlayerNames("sony, pobi, stitch, alt, toney, bibab, turtle, jjinto, kouz")))
			.isInstanceOf(InvalidPlayerException.class)
			.hasMessage(InvalidPlayerException.SIZE);
	}
}
