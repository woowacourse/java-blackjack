package blackjack.view;

import static blackjack.player.card.CardBundleHelper.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import blackjack.player.Dealer;
import blackjack.player.Gambler;
import blackjack.player.Player;
import blackjack.player.Players;
import blackjack.player.card.component.CardNumber;

class BundlesTest {

	@Test
	void getCardList() {
		//given
		String lineSeparator = System.lineSeparator();
		Player dealer = new Dealer(aCardBundle(CardNumber.KING, CardNumber.KING));
		Player gambler1 = new Gambler(aCardBundle(CardNumber.ACE, CardNumber.KING), "bebop");
		Player gambler2 = new Gambler(aCardBundle(CardNumber.KING, CardNumber.KING), "pobi");
		Player gambler3 = new Gambler(aCardBundle(CardNumber.KING, CardNumber.NINE), "allen");
		Players players = new Players(Arrays.asList(dealer, gambler1, gambler2, gambler3));
		String result = "딜러: K하트, K하트결과 - 20" + lineSeparator
			+ "bebop: A하트, K하트결과 - 21" + lineSeparator
			+ "pobi: K하트, K하트결과 - 20" + lineSeparator
			+ "allen: K하트, 9하트결과 - 19" + lineSeparator;

		//when
		String cardList = Bundles.getCardList(players);
		//than
		assertThat(cardList).isEqualTo(result);
	}
}