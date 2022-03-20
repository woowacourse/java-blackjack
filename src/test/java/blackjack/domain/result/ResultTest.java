package blackjack.domain.result;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class ResultTest {

	@DisplayName("딜러가 블랙잭이고 플레이어도 블랙잭이면 무승부다")
	@Test
	void dealerBlackjack_playerBlackjack() {
		Dealer dealer = new Dealer();
		Player player = new Player("yaho", 10);
		dealer.receiveCard(new Card(CardSymbol.SPADE, CardNumber.ACE));
		dealer.receiveCard(new Card(CardSymbol.HEART, CardNumber.KING));
		player.receiveCard(new Card(CardSymbol.SPADE, CardNumber.ACE));
		player.receiveCard(new Card(CardSymbol.HEART, CardNumber.KING));

		Result result = Result.of(dealer, player);

		assertThat(result).isEqualTo(Result.DRAW);
	}

	@DisplayName("딜러가 블랙잭이고 플레이어도 블랙잭이 아니면 패배다")
	@Test
	void dealerBlackjack_playerNotBlackjack() {
		Dealer dealer = new Dealer();
		Player player = new Player("yaho", 10);
		dealer.receiveCard(new Card(CardSymbol.SPADE, CardNumber.ACE));
		dealer.receiveCard(new Card(CardSymbol.HEART, CardNumber.KING));
		player.receiveCard(new Card(CardSymbol.SPADE, CardNumber.ACE));
		player.receiveCard(new Card(CardSymbol.HEART, CardNumber.TWO));

		Result result = Result.of(dealer, player);

		assertThat(result).isEqualTo(Result.LOSE);
	}

	@DisplayName("플레이어만 블랙잭이면 블랙잭 승리다")
	@Test
	void onlyPlayerBlackjack() {
		Dealer dealer = new Dealer();
		Player player = new Player("yaho", 10);
		dealer.receiveCard(new Card(CardSymbol.SPADE, CardNumber.ACE));
		dealer.receiveCard(new Card(CardSymbol.HEART, CardNumber.TWO));
		player.receiveCard(new Card(CardSymbol.SPADE, CardNumber.ACE));
		player.receiveCard(new Card(CardSymbol.HEART, CardNumber.KING));

		Result result = Result.of(dealer, player);

		assertThat(result).isEqualTo(Result.BLACKJACK);
	}

	@DisplayName("플레이어가 버스트면 패배다")
	@Test
	void playerBust() {
		Dealer dealer = new Dealer();
		Player player = new Player("yaho", 10);
		dealer.receiveCard(new Card(CardSymbol.SPADE, CardNumber.ACE));
		dealer.receiveCard(new Card(CardSymbol.HEART, CardNumber.TWO));
		player.receiveCard(new Card(CardSymbol.SPADE, CardNumber.KING));
		player.receiveCard(new Card(CardSymbol.HEART, CardNumber.KING));
		player.receiveCard(new Card(CardSymbol.DIAMOND, CardNumber.KING));

		Result result = Result.of(dealer, player);

		assertThat(result).isEqualTo(Result.LOSE);
	}

	@DisplayName("딜러가 버스트고 플레이어가 버스트면 패배이다")
	@Test
	void dealerBust_playerBust() {
		Dealer dealer = new Dealer();
		Player player = new Player("yaho", 10);
		dealer.receiveCard(new Card(CardSymbol.SPADE, CardNumber.KING));
		dealer.receiveCard(new Card(CardSymbol.HEART, CardNumber.KING));
		dealer.receiveCard(new Card(CardSymbol.DIAMOND, CardNumber.KING));
		player.receiveCard(new Card(CardSymbol.SPADE, CardNumber.KING));
		player.receiveCard(new Card(CardSymbol.HEART, CardNumber.KING));
		player.receiveCard(new Card(CardSymbol.DIAMOND, CardNumber.KING));

		Result result = Result.of(dealer, player);

		assertThat(result).isEqualTo(Result.LOSE);
	}

	@DisplayName("딜러 점수보다 플레이어 점수가 높으면 승리다")
	@Test
	void playerScoreWin() {
		Dealer dealer = new Dealer();
		Player player = new Player("yaho", 10);
		dealer.receiveCard(new Card(CardSymbol.SPADE, CardNumber.TWO));
		dealer.receiveCard(new Card(CardSymbol.HEART, CardNumber.TWO));
		player.receiveCard(new Card(CardSymbol.SPADE, CardNumber.ACE));
		player.receiveCard(new Card(CardSymbol.HEART, CardNumber.TWO));
		player.receiveCard(new Card(CardSymbol.DIAMOND, CardNumber.THREE));

		Result result = Result.of(dealer, player);

		assertThat(result).isEqualTo(Result.WIN);
	}

	@DisplayName("딜러 점수와 플레이어 점수가 같으면 무승부다")
	@Test
	void playerScoreDraw() {
		Dealer dealer = new Dealer();
		Player player = new Player("yaho", 10);
		dealer.receiveCard(new Card(CardSymbol.SPADE, CardNumber.TWO));
		dealer.receiveCard(new Card(CardSymbol.HEART, CardNumber.TWO));
		player.receiveCard(new Card(CardSymbol.HEART, CardNumber.TWO));
		player.receiveCard(new Card(CardSymbol.DIAMOND, CardNumber.TWO));

		Result result = Result.of(dealer, player);

		assertThat(result).isEqualTo(Result.DRAW);
	}

	@DisplayName("딜러 점수보다 플레이어 점수가 낮으면 패배다")
	@Test
	void playerScoreLose() {
		Dealer dealer = new Dealer();
		Player player = new Player("yaho", 10);
		dealer.receiveCard(new Card(CardSymbol.SPADE, CardNumber.ACE));
		dealer.receiveCard(new Card(CardSymbol.HEART, CardNumber.TWO));
		player.receiveCard(new Card(CardSymbol.HEART, CardNumber.TWO));
		player.receiveCard(new Card(CardSymbol.DIAMOND, CardNumber.TWO));

		Result result = Result.of(dealer, player);

		assertThat(result).isEqualTo(Result.LOSE);
	}
}
