package blackjack.player;

import static blackjack.player.card.CardBundleHelper.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.GameReport;
import blackjack.player.card.GameResult;
import blackjack.player.card.component.CardNumber;

class PlayersTest {

	@DisplayName("딜러와 갬블러들을 비교해서 게임 결과 리스트를 받아오는 메서드")
	@Test
	void getReports() {
		//given
		Player dealer = new Dealer(aCardBundle(CardNumber.KING, CardNumber.KING));
		Player gambler1 = new Gambler(aCardBundle(CardNumber.ACE, CardNumber.KING), "bebop");
		Player gambler2 = new Gambler(aCardBundle(CardNumber.KING, CardNumber.KING), "pobi");
		Player gambler3 = new Gambler(aCardBundle(CardNumber.KING, CardNumber.NINE), "allen");
		Players players = new Players(Arrays.asList(dealer, gambler1, gambler2, gambler3));

		//when
		List<GameReport> reports = players.getReports();

		//then
		assertThat(reports).hasSize(3);
		assertThat(reports).contains(
			new GameReport("bebop", GameResult.WIN),
			new GameReport("pobi", GameResult.DRAW),
			new GameReport("allen", GameResult.LOSE)
		);
	}
}