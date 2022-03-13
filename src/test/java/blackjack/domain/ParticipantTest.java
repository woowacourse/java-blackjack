package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.CardDeck;

public class ParticipantTest {

	@Test
	@DisplayName("이름이 공백이면 예외가 발생한다")
	void nameException_Empty() {
		assertThatThrownBy(() ->
			Participant.createPlayer("")
		).isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 이름은 공백일 수 없습니다.");
	}

	@Test
	@DisplayName("이름에 특수문자가 포한되면 예외가 발생한다")
	void nameException_SpacialChar() {
		assertThatThrownBy(() ->
			Participant.createPlayer("@as!")
		).isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 이름에 특수문자가 포함될 수 없습니다.");
	}

	@Test
	@DisplayName("카드를 한장 받으면, 플레이어의 카드가 한장 추가된다")
	void receiveCard() {
		Participant player = Participant.createPlayer("yaho");
		player.receiveCard(CardDeck.pick());
		assertThat(player.getCards().size()).isEqualTo(1);
	}
}
