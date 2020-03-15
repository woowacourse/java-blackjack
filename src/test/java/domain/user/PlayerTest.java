package domain.user;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;

public class PlayerTest {

	@Test
	void create_with_name() {
		assertThat(new Player("toney")).isInstanceOf(Player.class);
	}

	@Test
	void create_with_empty_name() {
		assertThatIllegalArgumentException().isThrownBy(() ->
				new Player(""))
				.withMessage("이름이 빈 문자열입니다.");
	}

	@Test
	void canDrawMore_When_Player_Has_20_Return_True() {
		Player player = new Player("a");
		player.receive(new Card(Symbol.ACE, Type.CLOVER));
		player.receive(new Card(Symbol.NINE, Type.CLOVER));
		assertThat(player.canDrawMore()).isTrue();
	}

	@Test
	void canDrawMore_When_Player_Has_21_Return_False() {
		Player player = new Player("a");
		player.receive(new Card(Symbol.ACE, Type.CLOVER));
		player.receive(new Card(Symbol.KING, Type.CLOVER));
		assertThat(player.canDrawMore()).isFalse();
	}
}
