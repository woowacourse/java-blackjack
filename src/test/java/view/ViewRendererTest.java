package view;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Result;
import domain.Score;
import domain.card.Card;
import domain.card.Cards;
import domain.Player;
import domain.card.Denomination;
import domain.card.Suit;

class ViewRendererTest {

	@Test
	@DisplayName("모든 카드와 점수를 문자열로 가지는 DTO가 생성되어야 한다.")
	void toNameCardScoreTest() {
		Cards cards = Cards.of(
			new Card(Suit.DIAMOND, Denomination.ACE),
			new Card(Suit.HEART, Denomination.TEN));
		Player player = new Player("player", cards);
		NameCardScoreDto nameCardScoreDto = ViewRenderer.toNameCardScore(player);

		assertThat(nameCardScoreDto.getName()).isEqualTo("player");
		assertThat(nameCardScoreDto.getCards().get(0)).isEqualTo("A다이아몬드");
		assertThat(nameCardScoreDto.getCards().get(1)).isEqualTo("10하트");
		assertThat(nameCardScoreDto.getScore()).isEqualTo("21");
	}

	@Test
	@DisplayName("이름과 단일 결과를 가지는 DTO가 생성되어야 한다.")
	void toSingleResultsTest() {
		Cards cards1 = Cards.of(
			new Card(Suit.DIAMOND, Denomination.TEN),
			new Card(Suit.HEART, Denomination.TEN));
		Player player = new Player("dealer", cards1);
		Cards cards2 = Cards.of(
			new Card(Suit.DIAMOND, Denomination.ACE),
			new Card(Suit.HEART, Denomination.TEN));
		Result result = Result.decide(Score.of(cards1), Score.of(cards2));


		List<SingleResultDto> singleResults = ViewRenderer.toSingleResults(Map.of(player, result));

		Assertions.assertThat(singleResults.get(0).getResult()).isEqualTo("패");
	}

	@Test
	@DisplayName("이름과 여러 결과를 가지는 DTO가 생성되어야 한다.")
	void toMultiResultsTest() {
		Cards cards1 = Cards.of(
			new Card(Suit.DIAMOND, Denomination.TEN),
			new Card(Suit.HEART, Denomination.TEN));
		Player player = new Player("dealer", cards1);
		Cards cards2 = Cards.of(
			new Card(Suit.DIAMOND, Denomination.ACE),
			new Card(Suit.HEART, Denomination.TEN));
		Cards cards3 = Cards.of(
			new Card(Suit.DIAMOND, Denomination.NINE),
			new Card(Suit.HEART, Denomination.TEN));
		Result result = Result.decide(Score.of(cards1), List.of(Score.of(cards2), Score.of(cards3)));

		MultiResultsDto multiResults = ViewRenderer.toMultiResults(player, result);

		Assertions.assertThat(multiResults.getResults().size()).isEqualTo(2);
		Assertions.assertThat(multiResults.getResults().get(0)).isEqualTo("1승");
		Assertions.assertThat(multiResults.getResults().get(1)).isEqualTo("1패");
	}
}
