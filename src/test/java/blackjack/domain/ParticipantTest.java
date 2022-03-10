package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.CardDeck;

public class ParticipantTest {

	@Test
	@DisplayName("이름이 공백이면 예외 발생")
	void Blank_Name_Exception() {
		assertThatThrownBy(() ->
			Participant.createPlayer("")
		).isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 이름은 공백일 수 없습니다.");
	}

	@Test
	@DisplayName("이름에 특수문자가 포한되면 예외 발생")
	void Unavailable_Name_Exception() {
		assertThatThrownBy(() ->
			Participant.createPlayer("@as!")
		).isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 이름에 특수문자가 포함될 수 없습니다.");
	}

	@Test
	@DisplayName("카드가 한장 추가되는지")
	void Receive_Card() {
		Participant player = Participant.createPlayer("yaho");
		player.receiveCard(CardDeck.pickCard());
		assertThat(player.getCards().size()).isEqualTo(1);
	}
}
