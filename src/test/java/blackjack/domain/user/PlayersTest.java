package blackjack.domain.user;

import blackjack.domain.card.*;
import blackjack.domain.user.exceptions.PlayersException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
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

	private static Card aceClub;
	private static Card twoHeart;
	private static Card threeDiamond;
	private static Card fourSpade;
	private static Card fiveClub;
	private static Card sixHeart;

	@BeforeAll
	static void beforeAll() {
		aceClub = Card.of(Symbol.ACE, Type.CLUB);
		twoHeart = Card.of(Symbol.TWO, Type.HEART);
		threeDiamond = Card.of(Symbol.THREE, Type.DIAMOND);
		fourSpade = Card.of(Symbol.FOUR, Type.SPADE);
		fiveClub = Card.of(Symbol.FIVE, Type.CLUB);
		sixHeart = Card.of(Symbol.SIX, Type.HEART);
	}

	static Stream<Arguments> giveTwoCardsEachPlayer_ReceiveThreeCard_EachPlayerHasItselfsCards() {
		return Stream.of(Arguments.of(0, Arrays.asList(sixHeart, fiveClub)),
				Arguments.of(1, Arrays.asList(fourSpade, threeDiamond)),
				Arguments.of(2, Arrays.asList(twoHeart, aceClub)));
	}

}