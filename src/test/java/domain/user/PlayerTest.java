package domain.user;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import domain.card.Card;

public class PlayerTest {

	@Test
	void name() {
		Player player = new Player("동글");
		player.addCards(new ArrayList<Card>());

		assertThat(player.isBlackjack()).isTrue();
	}

}
