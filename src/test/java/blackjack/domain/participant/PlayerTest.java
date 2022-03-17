package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.Money;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardSymbol;

public class PlayerTest {

	@Test
	@DisplayName("이름이 공백이면 예외가 발생한다")
	void nameException_Empty() {
		assertThatThrownBy(() ->
			new Player(Name.from(""), Money.from("10"))
		).isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 이름은 공백일 수 없습니다.");
	}

	@Test
	@DisplayName("이름에 특수문자가 포한되면 예외가 발생한다")
	void nameException_SpacialChar() {
		assertThatThrownBy(() ->
			new Player(Name.from("@as!"), Money.from("10"))
		).isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 이름에 특수문자가 포함될 수 없습니다.");
	}

	@Test
	@DisplayName("카드를 한장 받으면, 플레이어의 카드가 한장 추가된다")
	void receiveCard() {
		CardDeck cardDeck = CardDeck.create();
		Player player = new Player(Name.from("yaho"), Money.from("10"));
		player.receiveCard(cardDeck.pick());
		assertThat(player.getCards().getCards().size()).isEqualTo(1);
	}

	@Test
	@DisplayName("카드가 2장이고 점수가 21점이면 블랙잭이다")
	void isBlackjack() {
		Player player = new Player(Name.from("yaho"), Money.from("10"));
		player.receiveCard(new Card(CardSymbol.SPADE, CardNumber.ACE));
		player.receiveCard(new Card(CardSymbol.SPADE, CardNumber.KING));
		assertThat(player.isBlackjack()).isTrue();
	}
}
