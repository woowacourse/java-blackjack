package blackjack.domain.gamer;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;

class BlackJackGamerTest {

	BlackJackGamer player;

	@BeforeEach
	void setUP() {
		player = new Player();
	}

	@Test
	@DisplayName("ACE가 없을때의 숫자 합을 계산한다.")
	void sumWithoutAceTest() {
		player.addCard(new Card(CardShape.CLOVER, CardNumber.KING));
		player.addCard(new Card(CardShape.HEART, CardNumber.FIVE));

		assertThat(player.sumCardNumbers()).isEqualTo(15);
	}

	@Test
	@DisplayName("숫자의 합이 21을 초과하는 경우, ACE를 1로 계산한다.")
	void sumWithOneAceTest() {
		player.addCard(new Card(CardShape.HEART, CardNumber.ACE));
		player.addCard(new Card(CardShape.CLOVER, CardNumber.KING));
		player.addCard(new Card(CardShape.DIAMOND, CardNumber.KING));

		assertThat(player.sumCardNumbers()).isEqualTo(21);
	}

	@Test
	@DisplayName("숫자의 합이 21을 초과하지 않으면, ACE를 11로 계산한다.")
	void sumWithElevenAceTest() {
		player.addCard(new Card(CardShape.HEART, CardNumber.ACE));
		player.addCard(new Card(CardShape.CLOVER, CardNumber.KING));

		assertThat(player.sumCardNumbers()).isEqualTo(21);
	}
}
