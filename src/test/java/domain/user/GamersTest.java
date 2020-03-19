package domain.user;

import static domain.card.Symbol.*;
import static domain.card.Type.*;
import static java.util.Arrays.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.TestCardDeck;
import domain.result.ScoreBoards;
import domain.result.UserResults;
import domain.score.Score;

class GamersTest {
	private static final String NON_ADDITIONAL_DRAW_RESPONSE = "n";

	private Gamers gamers;

	@BeforeEach
	void setup() {
		Dealer dealer = new Dealer();
		List<Player> players = initPlayers();
		CardDeck deck = new TestCardDeck(
			Card.of(DIAMOND, ACE), Card.of(SPADE, SEVEN), Card.of(HEART, EIGHT), Card.of(HEART, NINE),
			Card.of(CLOVER, NINE),
			Card.of(DIAMOND, KING), Card.of(SPADE, TWO), Card.of(HEART, ACE), Card.of(DIAMOND, EIGHT),
			Card.of(CLOVER, EIGHT));

		gamers = new Gamers(players, dealer, deck);
	}

	private List<Player> initPlayers() {
		Player name = getFirstPlayer();
		Player cache = getSecondPlayer();
		Player van = getThirdPlayer();
		Player peter = getFourthPlayer();
		return asList(name, cache, van, peter);
	}

	private Player getFirstPlayer() {
		return Player.fromNameAndMoney("name", 5_000);
	}

	private Player getSecondPlayer() {
		return Player.fromNameAndMoney("cache", 10_000);
	}

	private Player getThirdPlayer() {
		return Player.fromNameAndMoney("van", 100_000);
	}

	private Player getFourthPlayer() {
		return Player.fromNameAndMoney("peter", 1_000_000);
	}

	@DisplayName("4명의 플레이어에게 각각 1,6번 2,7번 3,8번 4,9번 카드가 제대로 분배되는지 테스트")
	@Test
	void drawFirstTiePlayerTest() {
		gamers.drawFirstTime(user -> {
		});
		List<Player> players = gamers.getPlayers();

		assertThat(players).extracting("cards").contains(
			asList(Card.of(DIAMOND, ACE), Card.of(DIAMOND, KING)),
			asList(Card.of(SPADE, SEVEN), Card.of(SPADE, TWO)),
			asList(Card.of(HEART, EIGHT), Card.of(HEART, ACE)),
			asList(Card.of(HEART, NINE), Card.of(DIAMOND, EIGHT))
		);
	}

	@DisplayName("딜러에게 5,10번 카드가 분배되는지 테스트")
	@Test
	void drawFirstTimeDealerTest() {
		gamers.drawFirstTime(user -> {
		});

		assertThat(gamers).extracting("dealer").extracting("cards").isEqualTo(
			asList(Card.of(CLOVER, NINE), Card.of(CLOVER, EIGHT))
		);
	}

	@DisplayName("4명의 플레이어에게 초기 드로우 후, 추가 드로는 no 이기 때문에 초기 드로했던 카드들만 갖는다.")
	@Test
	void drawPlayersAdditionalTest() {
		gamers.drawFirstTime(user -> {
		});
		gamers.drawPlayersAdditional(name -> NON_ADDITIONAL_DRAW_RESPONSE, player -> {
		});
		List<Player> players = gamers.getPlayers();

		assertThat(players).extracting("cards").contains(
			asList(Card.of(DIAMOND, ACE), Card.of(DIAMOND, KING)),
			asList(Card.of(SPADE, SEVEN), Card.of(SPADE, TWO)),
			asList(Card.of(HEART, EIGHT), Card.of(HEART, ACE)),
			asList(Card.of(HEART, NINE), Card.of(DIAMOND, EIGHT))
		);
	}

	@DisplayName("딜러는 카드가 없기 때문에 카드 한장을 추가로 받아야 한다. 그 카드는 카드 덱의 첫번째 카드가 될것이다.")
	@Test
	void drawDealerAdditional() {
		gamers.drawDealerAdditional(dealer -> {
		});
		assertThat(gamers).extracting("dealer").extracting("cards").isEqualTo(
			Collections.singletonList(Card.of(DIAMOND, ACE))
		);
	}

	@DisplayName("딜러는 2장의 카드를 드로우 받은 상태이다. 이때 보유한 카드 합이 17이기 때문에 추가 드로우가 발생하지 않는다.")
	@Test
	void drawDealerAdditional2() {
		gamers.drawFirstTime(user -> {
		});
		gamers.drawDealerAdditional(dealer -> {
		});
		assertThat(gamers).extracting("dealer").extracting("cards").isEqualTo(
			asList(Card.of(CLOVER, NINE), Card.of(CLOVER, EIGHT))
		);
	}

	@DisplayName("모든 참여자들은 순차적으로 2장의 카드를 받은뒤, 각 보유한 카드별 점수를 올바르게 계산해야한다. 결과는 플레이어, 딜러순으로 나타난다.")
	@Test
	void calculateScoreBoards() {
		gamers.drawFirstTime(user -> {
		});
		ScoreBoards scoreBoards = gamers.calculateScoreBoards();
		assertThat(scoreBoards.getScoreBoards()).extracting("score")
			.containsExactly(Score.ofValue(21), Score.ofValue(9), Score.ofValue(19), Score.ofValue(17), Score.ofValue(17));
	}

	@DisplayName("모든 참여자들은 카드 점수를 기준으로 승패 결정후, 결과별 최종 상금을 계산한다. 결과는 딜러, 플레이어순으로 나타난다.")
	@Test
	void calculatePrizeResults() {
		gamers.drawFirstTime(user -> {
		});
		ScoreBoards scoreBoards = gamers.calculateScoreBoards();
		UserResults userResults = gamers.calculatePrizeResults(scoreBoards);

		assertThat(userResults.getPlayerResults()).extracting("prize")
			.containsExactly(-97_500, 7_500, -10_000, 100_000, 0);
	}
}