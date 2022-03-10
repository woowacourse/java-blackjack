package blackjack.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackGameTest {

	@Test
	@DisplayName("game의 승패를 계산한다.")
	void calculateResult() {
		// given
		Player dealer = new Dealer();
		dealer.receiveCard(new Card(Suit.DIAMOND, Denomination.FIVE));

		Gamer judy = new Gamer("judy");
		judy.receiveCard(new Card(Suit.CLOVER, Denomination.SIX));

		Gamer huni = new Gamer("huni");
		huni.receiveCard(new Card(Suit.DIAMOND, Denomination.FOUR));

		BlackJackGame blackJackGame = new BlackJackGame(dealer, List.of(judy, huni));

		//when
		Map<Gamer, Result> gameResult = blackJackGame.calculateResultBoard();

		//then
		assertAll(
			() -> assertThat(gameResult.get(judy)).isEqualTo(Result.WIN),
			() -> assertThat(gameResult.get(huni)).isEqualTo(Result.LOSE)
		);
	}

}
