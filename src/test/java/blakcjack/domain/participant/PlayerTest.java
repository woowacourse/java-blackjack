package blakcjack.domain.participant;

import blakcjack.domain.card.Card;
import blakcjack.domain.card.CardNumber;
import blakcjack.domain.card.CardSymbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
	@DisplayName("플레이어 객체 생성 성공")
	@Test
	void create() {
		final Player player = new Player("pobi");
		assertThat(player).isEqualTo(new Player("pobi"));
	}

	@DisplayName("카드 받기 성공")
	@Test
	void receiveCard() {
		final Player player = new Player("sakjung");
		final Card card = Card.of(CardSymbol.CLUB, CardNumber.ACE);
		player.receiveCard(card);

		assertThat(player.getCards()).isEqualTo(Collections.singletonList(Card.of(CardSymbol.CLUB, CardNumber.ACE)));
	}
}
