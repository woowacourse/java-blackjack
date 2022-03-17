package blackjack.domain.result;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;

public class BettingBoxTest {

	@Test
	@DisplayName("최종 수익이 정상적으로 나오는지 확인")
	void profit_result_test() {
		//given
		final int pobiBettingMoney = 10000;
		Player pobi = new Player("pobi", pobiBettingMoney);
		Dealer dealer = new Dealer();
		pobi.addCard(Card.of(Denomination.JACK, Suit.CLOVER));
		pobi.addCard(Card.of(Denomination.QUEEN, Suit.CLOVER));
		dealer.addCard(Card.of(Denomination.QUEEN, Suit.DIAMOND));
		dealer.addCard(Card.of(Denomination.NINE, Suit.DIAMOND));
		//when
		BettingBox bettingBox = new BettingBox();
		Map<Player, Money> profits =  bettingBox.profitResult(List.of(pobi), dealer);
		Money profit = profits.get(pobi);
		//then
		assertThat(profit).isEqualTo(new Money(20000));
	}
}
