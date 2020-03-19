package blackjack.player.domain;

import static blackjack.card.domain.CardBundleHelper.*;
import static blackjack.player.domain.component.PlayerInfoHelper.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import blackjack.card.domain.Card;
import blackjack.card.domain.CardBundle;
import blackjack.card.domain.CardDeck;
import blackjack.card.domain.GameResult;
import blackjack.card.domain.component.CardNumber;
import blackjack.exception.InvalidFlowException;
import blackjack.player.domain.report.GameReport;
import blackjack.player.domain.report.GameReports;

class PlayersTest {

	@DisplayName("Players 생성시 비어있는 리스트 혹은 Null로 생성시 Exception")
	@ParameterizedTest
	@NullAndEmptySource
	void test(List<Player> players) {
		assertThatThrownBy(() -> new Players(players))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("딜러와 갬블러들을 비교해서 게임 결과 리스트를 받아오는 메서드")
	@Test
	void getReports() {
		//given
		Player dealer = new Dealer(aCardBundle(CardNumber.KING, CardNumber.KING));
		Player gambler1 = new Gambler(aCardBundle(CardNumber.ACE, CardNumber.KING),
			aPlayerInfo("bebop"));
		Player gambler2 = new Gambler(aCardBundle(CardNumber.TWO, CardNumber.KING, CardNumber.NINE),
			aPlayerInfo("muni"));
		Player gambler3 = new Gambler(aCardBundle(CardNumber.KING, CardNumber.KING),
			aPlayerInfo("pobi"));
		Player gambler4 = new Gambler(aCardBundle(CardNumber.KING, CardNumber.NINE),
			aPlayerInfo("allen"));
		Players players = new Players(Arrays.asList(dealer, gambler1, gambler2, gambler3, gambler4));

		//when
		GameReports reports = players.getReports();

		//then
		assertThat(reports).isEqualTo(new GameReports(Arrays.asList(
			new GameReport(aPlayerInfo("bebop"), GameResult.BLACKJACK_WIN),
			new GameReport(aPlayerInfo("muni"), GameResult.WIN),
			new GameReport(aPlayerInfo("pobi"), GameResult.DRAW),
			new GameReport(aPlayerInfo("allen"), GameResult.LOSE)))
		);
	}

	@DisplayName("찾아온 플레이어가 갬블러이다.")
	@Test
	void findGamblers() {
		//given
		Players players = new Players(
			Arrays.asList(new Gambler(new CardBundle(), aPlayerInfo("allen")),
				new Dealer(new CardBundle())));

		//when
		List<Player> gamblers = players.findGamblers();
		Player player = gamblers.get(0);

		//then
		assertThat(player.getClass()).isEqualTo(Gambler.class);
	}

	@DisplayName("찾아온 플레이어가 딜러이다.")
	@Test
	void findDealer() {
		//given
		Players players = new Players(
			Arrays.asList(new Gambler(new CardBundle(), aPlayerInfo("allen")),
				new Dealer(new CardBundle())));

		//when
		Player dealer = players.findDealer();

		//then
		assertThat(dealer.getClass()).isEqualTo(Dealer.class);
	}

	@DisplayName("스타팅 카드가 2번이상 호출되면 Exception")
	@Test
	void drawStartingCard() {
		Players players = new Players(
			Arrays.asList(new Gambler(new CardBundle(), aPlayerInfo("allen")),
				new Dealer(new CardBundle())));

		CardDeck cardDeck = CardDeck.getInstance(Card.getAllCards());
		players.drawStartingCard(cardDeck);

		assertThatThrownBy(() -> {
			players.drawStartingCard(cardDeck);
		}).isInstanceOf(InvalidFlowException.class);

	}
}