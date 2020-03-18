package blackjack.view;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import blackjack.card.domain.CardBundle;
import blackjack.player.domain.Gambler;
import blackjack.player.domain.Player;
import blackjack.view.dto.NamesDTO;

class NamesDTOTest {

	@DisplayName("Names 에서 Player 리스트 만들기")
	@Test
	void toPlayers() {
		//given
		String inputNames = "allen,bebop";
		List<Player> result = Arrays.asList(
			new Gambler(new CardBundle(), "allen", playerInfo),
			new Gambler(new CardBundle(), "bebop", playerInfo)
		);

		//when
		NamesDTO namesDTO = new NamesDTO(inputNames);
		List<Player> players = namesDTO.toPlayers();

		//then
		assertThat(players).isEqualTo(result);
	}

	@DisplayName("Names 에 Null 혹은 비어있는 값 입력시 exception 발생")
	@ParameterizedTest
	@NullAndEmptySource
	void toPlayersException(String inputNames) {
		assertThatThrownBy(() -> new NamesDTO(inputNames))
			.isInstanceOf(IllegalArgumentException.class);
	}
}