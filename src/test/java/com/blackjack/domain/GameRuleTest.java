package com.blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import com.blackjack.domain.card.Card;
import com.blackjack.domain.card.Denomination;
import com.blackjack.domain.card.Suit;
import com.blackjack.domain.user.Dealer;
import com.blackjack.domain.user.Player;
import com.blackjack.domain.user.PlayerFactory;

public class GameRuleTest {
	@DisplayName("딜러와 플레이어 리스트를 입력받아 인스턴스 생성")
	@Test
	void constructor() {
		Dealer dealer = new Dealer();
		List<Player> players = PlayerFactory.createPlayers(
				Arrays.asList("1", "2", "3", "4"),
				Arrays.asList(1_000, 1_000, 1_000, 1_000)
		);

		assertThat(new GameRule(dealer, players)).isInstanceOf(GameRule.class);
	}

	@DisplayName("딜러가 null인 경우 예외 발생")
	@Test
	void constructor_DealerIsNull_ExceptionThrown() {
		List<Player> players = PlayerFactory.createPlayers(
				Arrays.asList("1", "2", "3", "4"),
				Arrays.asList(1_000, 1_000, 1_000, 1_000)
		);

		assertThatThrownBy(() -> new GameRule(null, players)).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("플레이어가 null 또는 empty인 경우 예외 발생")
	@ParameterizedTest
	@NullAndEmptySource
	void constructor_PlayersIsNullOrEmpty_ExceptionThrown(List<Player> players) {
		assertThatThrownBy(() -> new GameRule(new Dealer(), players)).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("유저와 플레이어의 패를 비교하여 결과 생성")
	@Test
	void calculateResult() {
		Dealer dealer = new Dealer();
		dealer.draw(Card.valueOf(Denomination.TEN, Suit.CLUB));
		dealer.draw(Card.valueOf(Denomination.TEN, Suit.HEART));
		dealer.draw(Card.valueOf(Denomination.TEN, Suit.SPADE));

		List<Player> players = PlayerFactory.createPlayers(
				Arrays.asList("1", "2", "3", "4"),
				Arrays.asList(1_000, 1_000, 1_000, 1_000)
		);

		players.get(0).draw(Card.valueOf(Denomination.JACK, Suit.CLUB));
		players.get(0).draw(Card.valueOf(Denomination.QUEEN, Suit.CLUB));
		players.get(0).draw(Card.valueOf(Denomination.KING, Suit.CLUB));

		GameRule gameRule = new GameRule(dealer, players);
		assertThat(gameRule.calculatePlayerProfits()).isInstanceOf(PlayerProfits.class);
	}
}
