package domain.result;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Player;
import domain.card.Card;
import domain.card.Cards;
import domain.card.Denomination;
import domain.card.Suit;

class PlayerResultTest {

	@Test
	@DisplayName("점수가 21인 플레이어가 승리해야 한다.")
	public void playerVictoryTest() {
		Player score21Player = getScore21Player();
		Player score19Player = getScore19Player();

		PlayerResult winResult = PlayerResult.decide(score21Player, score19Player);
		PlayerResult lossResult = PlayerResult.decide(score19Player, score21Player);

		Assertions.assertThat(winResult.getResultState()).isEqualTo(ResultState.WIN);
		Assertions.assertThat(lossResult.getResultState()).isEqualTo(ResultState.LOSS);
	}

	@Test
	@DisplayName("동일 점수끼리는 무승부의 결과를 가져야 한다.")
	public void playerDrawTest() {
		Player player1 = getScore21Player();
		Player player2 = getScore21Player();

		PlayerResult result = PlayerResult.decide(player1, player2);

		Assertions.assertThat(result.getResultState()).isEqualTo(ResultState.DRAW);
	}

	private static Player getScore19Player() {
		Cards player2Cards = Cards.of(
			new Card(Suit.DIAMOND, Denomination.NINE),
			new Card(Suit.HEART, Denomination.TEN));
		return new Player("playerScore19", player2Cards);
	}

	private static Player getScore21Player() {
		Cards player1Cards = Cards.of(
			new Card(Suit.DIAMOND, Denomination.ACE),
			new Card(Suit.HEART, Denomination.TEN));
		return new Player("playerScore21", player1Cards);
	}
}
