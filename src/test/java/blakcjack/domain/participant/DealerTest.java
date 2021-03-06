package blakcjack.domain.participant;

import blakcjack.domain.card.Card;
import blakcjack.domain.card.CardNumber;
import blakcjack.domain.card.CardSymbol;
import blakcjack.domain.name.Name;
import blakcjack.domain.outcome.Outcome;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class DealerTest {
	private Player player;
	private Dealer dealer;

	@BeforeEach
	void setUp() {
		player = new Player(new Name("pobi"));
		dealer = new Dealer();
	}

	@DisplayName("딜러 객체 생성 제대로 하는지")
	@Test
	void create() {
		final Dealer dealer = new Dealer();
		assertThat(dealer).isEqualTo(new Dealer());
	}

	@DisplayName("카드 제대로 받는지")
	@Test
	void receiveCard() {
		final Dealer dealer = new Dealer();
		final Card card = Card.of(CardSymbol.CLUB, CardNumber.ACE);
		dealer.receiveCard(card);

		assertThat(dealer.getCards()).isEqualTo(Collections.singletonList(Card.of(CardSymbol.CLUB, CardNumber.ACE)));
	}

	@DisplayName("딜러 점수가 17점 미만이면 true 17점 이상이면 false")
	@Test
	void isScoreLowerThanSevenTeen() {
		final Dealer dealer = new Dealer();
		dealer.receiveCard(Card.of(CardSymbol.CLUB, CardNumber.TEN));
		dealer.receiveCard(Card.of(CardSymbol.CLUB, CardNumber.SIX));
		assertThat(dealer.isScoreLowerThanMaximumDrawCriterion()).isTrue();

		dealer.receiveCard(Card.of(CardSymbol.CLUB, CardNumber.ACE));
		assertThat(dealer.isScoreLowerThanMaximumDrawCriterion()).isFalse();
	}

	@DisplayName("플레이어 버스트시 플레이어 패")
	@Test
	void judgeOutcome_playerBust_returnLose() {
		player.receiveCard(Card.of(CardSymbol.SPADE, CardNumber.TWO));
		player.receiveCard(Card.of(CardSymbol.SPADE, CardNumber.JACK));
		player.receiveCard(Card.of(CardSymbol.SPADE, CardNumber.QUEEN));

		dealer.receiveCard(Card.of(CardSymbol.HEART, CardNumber.TWO));
		dealer.receiveCard(Card.of(CardSymbol.HEART, CardNumber.JACK));
		dealer.receiveCard(Card.of(CardSymbol.HEART, CardNumber.QUEEN));

		Outcome outcome = dealer.judgeOutcomeOf(player);
		assertThat(outcome).isEqualTo(Outcome.LOSE);
	}

	@DisplayName("딜러만 버스트시 플레이어 승")
	@Test
	void judgeOutcome_onlyDealerBust_returnWin() {
		player.receiveCard(Card.of(CardSymbol.SPADE, CardNumber.ACE));
		player.receiveCard(Card.of(CardSymbol.SPADE, CardNumber.JACK));
		player.receiveCard(Card.of(CardSymbol.SPADE, CardNumber.QUEEN));

		dealer.receiveCard(Card.of(CardSymbol.HEART, CardNumber.TWO));
		dealer.receiveCard(Card.of(CardSymbol.HEART, CardNumber.JACK));
		dealer.receiveCard(Card.of(CardSymbol.HEART, CardNumber.QUEEN));

		Outcome outcome = dealer.judgeOutcomeOf(player);
		assertThat(outcome).isEqualTo(Outcome.WIN);
	}

	@DisplayName("둘다 버스트 아닐 시 딜러와 플레이어의 점수 비교로 결과 판단")
	@Test
	void judgeOutcome_noOneBust_compareScore() {
		player.receiveCard(Card.of(CardSymbol.SPADE, CardNumber.JACK));
		player.receiveCard(Card.of(CardSymbol.SPADE, CardNumber.QUEEN));

		dealer.receiveCard(Card.of(CardSymbol.HEART, CardNumber.ACE));
		dealer.receiveCard(Card.of(CardSymbol.HEART, CardNumber.JACK));
		dealer.receiveCard(Card.of(CardSymbol.HEART, CardNumber.QUEEN));

		Outcome outcome = dealer.judgeOutcomeOf(player);
		assertThat(outcome).isEqualTo(Outcome.LOSE);
	}
}
