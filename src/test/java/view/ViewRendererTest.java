package view;

import static domain.TestData.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.result.DealerResult;
import domain.result.PlayerResult;
import domain.Player;

class ViewRendererTest {

	@Test
	@DisplayName("모든 카드와 점수를 문자열로 가지는 DTO가 생성되어야 한다.")
	void toNameCardScoreTest() {
		Player player = getScore21Player();

		NameCardScoreDto nameCardScoreDto = ViewRenderer.toNameCardScore(player);

		assertThat(nameCardScoreDto.getName()).isEqualTo("playerScore21");
		assertThat(nameCardScoreDto.getCards().get(0)).isEqualTo("A다이아몬드");
		assertThat(nameCardScoreDto.getCards().get(1)).isEqualTo("10하트");
		assertThat(nameCardScoreDto.getScore()).isEqualTo("21");
	}

	@Test
	@DisplayName("이름과 단일 결과를 가지는 DTO가 생성되어야 한다.")
	void toSingleResultsTest() {
		Player player1 = getScore20Player();
		Player player2 = getScore21Player();
		PlayerResult playerResult = PlayerResult.decide(player1, player2);

		List<SingleResultDto> singleResults = ViewRenderer.toSingleResults(List.of(playerResult));
		SingleResultDto singleResult = singleResults.get(0);

		assertThat(singleResult.getResult()).isEqualTo("패");
	}

	@Test
	@DisplayName("이름과 0이 아닌 승/무/패 결과를 가지는 DTO가 생성되어야 한다.")
	void toMultiResultsTest() {
		Player dealer = getScore20Player();
		Player player1 = getScore21Player();
		PlayerResult playerResult1 = PlayerResult.decide(player1, dealer);
		Player player2 = getScore19Player();
		PlayerResult playerResult2 = PlayerResult.decide(player2, dealer);

		DealerResult dealerResult = DealerResult.from(List.of(playerResult1, playerResult2));
		MultiResultsDto multiResults = ViewRenderer.toMultiResults(dealerResult);

		assertThat(multiResults.getResults().size()).isEqualTo(2);
		assertThat(multiResults.getResults().get(0)).isEqualTo("1승");
		assertThat(multiResults.getResults().get(1)).isEqualTo("1패");
	}

}
