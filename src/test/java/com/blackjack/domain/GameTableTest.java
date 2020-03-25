package com.blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.blackjack.domain.user.Dealer;
import com.blackjack.domain.user.Player;
import com.blackjack.domain.user.PlayerFactory;

public class GameTableTest {
	@DisplayName("딜러와 플레이어를 받아 인스턴스 생성")
	@Test
	void constructor() {
		Dealer dealer = new Dealer();

		List<Player> players = PlayerFactory.createPlayers(
				Arrays.asList("1", "2", "3", "4"),
				Arrays.asList(1_000, 1_000, 1_000, 1_000)
		);

		assertThat(new GameTable(dealer, players)).isInstanceOf(GameTable.class);
	}

	@DisplayName("초기 카드를 드로우하면 각 플레이어가 2장의 카드를 가짐")
	@Test
	void drawAtFirst_AllPlayersHasTwoCards() {
		Dealer dealer = new Dealer();
		List<Player> players = PlayerFactory.createPlayers(
				Collections.singletonList("1"),
				Collections.singletonList(1_000)
		);
		GameTable gameTable = new GameTable(dealer, players);

		gameTable.drawAtFirst();

		int dealerCardSize = dealer.getHand()
				.getCards()
				.size();
		int playerCardSize = players.get(0)
				.getHand()
				.getCards()
				.size();
		assertThat(dealerCardSize).isEqualTo(2);
		assertThat(playerCardSize).isEqualTo(2);
	}

	@DisplayName("초기 카드를 드로우하면 각 플레이어가 2장의 카드를 가짐")
	@Test
	void draw() {
		Dealer dealer = new Dealer();
		List<Player> players = PlayerFactory.createPlayers(
				Collections.singletonList("1"),
				Collections.singletonList(1_000)
		);
		GameTable gameTable = new GameTable(dealer, players);

		gameTable.draw(players.get(0));

		int playerCardSize = players.get(0)
				.getHand()
				.getCards()
				.size();
		assertThat(playerCardSize).isEqualTo(1);
	}
}
