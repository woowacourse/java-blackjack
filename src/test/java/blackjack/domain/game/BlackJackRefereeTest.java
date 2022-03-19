package blackjack.domain.game;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.result.BettingResult;

class BlackJackRefereeTest {

	@ParameterizedTest
	@CsvSource(value = {"TEN:0:0", "ACE:-1500:1500", "NINE:1000:-1000"}, delimiter = ':')
	@DisplayName("배팅 결과 생성")
	void createResultLose(String cardNumber, int dealerResult, int playerResult) {
		Dealer dealer = new Dealer();
		dealer.addCard(Card.getInstance(CardShape.CLOVER, CardNumber.TEN));
		dealer.addCard(Card.getInstance(CardShape.CLOVER, CardNumber.TEN));

		Player player = new Player("does", 1000);
		player.addCard(Card.getInstance(CardShape.CLOVER, CardNumber.TEN));
		player.addCard(Card.getInstance(CardShape.CLOVER, CardNumber.valueOf(cardNumber)));

		BettingResult bettingResult = BlackJackReferee.create(dealer, List.of(player));

		int dealerEarning = bettingResult.getDealerEarning();
		assertThat(dealerEarning).isEqualTo(dealerResult);

		Integer playerEarning = bettingResult.getPlayerEarnings().get("does");
		assertThat(playerEarning).isEqualTo(playerResult);
	}

}
