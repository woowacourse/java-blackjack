package domain.user;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;

public class DealerTeat {

	@DisplayName("카드 점수 합계가 16 이하인 경우, 카드 뽑기 여부 테스트")
	@Test
	void isDrawableTest() {
		Dealer dealer = new Dealer();
		dealer.addCards(Arrays.asList(new Card(Symbol.HEART, Type.EIGHT),
			new Card(Symbol.CLOVER, Type.EIGHT)));
		assertThat(dealer.isDrawable()).isTrue();
	}

	@DisplayName("카드 점수 합계가 16 초과인 경우, 카드 뽑기 여부 테스트")
	@Test
	void isDrawableTest2() {
		Dealer dealer = new Dealer();
		dealer.addCards(Arrays.asList(new Card(Symbol.HEART, Type.EIGHT),
			new Card(Symbol.CLOVER, Type.NINE)));
		assertThat(dealer.isDrawable()).isFalse();
	}
}
