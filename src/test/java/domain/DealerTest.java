package domain;

import static domain.Denomination.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

	private Dealer dealer;

	@BeforeEach
	void setDealer() {
		dealer = new Dealer("딜러");
	}

	@DisplayName("카드 점수가 17보다 작으면 카드를 받아야 한다")
	@Test
	void hit_WhenScoreUnder21() {
		addCards(List.of(SIX, JACK));

		assertThat(dealer.isHittable()).isTrue();
	}

	@DisplayName("카드 점수가 17 이상이면 카드를 받을 수 없다")
	@Test
	void stay_WhenScoreOver21() {
		addCards(List.of(SEVEN, JACK));

		assertThat(dealer.isHittable()).isFalse();
	}

	private void addCards(List<Denomination> denominations) {
		for (Denomination denomination : denominations) {
			dealer.hit(new Card(denomination, Suits.HEART));
		}
	}
}
