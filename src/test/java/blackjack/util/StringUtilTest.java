package blackjack.util;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.result.ResultType;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.User;

class StringUtilTest {
	@Test
	void joinPlayerNames_PlayerList_JoinPlayerNamesToString() {
		List<User> users = Arrays.asList(
			new Dealer("dealer"),
			new Player("pobi"),
			new Player("sony"),
			new Player("stitch"));

		String expected = "pobi, sony, stitch";
		assertThat(StringUtil.joinPlayerNames(users)).isEqualTo(expected);
	}

	@Test
	void joinCards_CardList_JoinCardsToString() {
		List<Card> cards = Arrays.asList(
			new Card(Symbol.ACE, Type.CLUB),
			new Card(Symbol.TWO, Type.HEART),
			new Card(Symbol.EIGHT, Type.DIAMOND)
		);

		String expected = "A클럽, 2하트, 8다이아몬드";
		assertThat(StringUtil.joinCards(cards)).isEqualTo(expected);
	}

	@Test
	void joinDealerResult_MapOfResultTypeAndCount_JoinDealerResultToString() {
		Map<ResultType, Long> dealerResult = new EnumMap<>(ResultType.class);
		dealerResult.put(ResultType.WIN, 2L);
		dealerResult.put(ResultType.LOSE, 1L);

		String expected = "Dealer: 2승 1패";
		assertThat(StringUtil.joinDealerResult(dealerResult)).isEqualTo(expected);
	}
}
