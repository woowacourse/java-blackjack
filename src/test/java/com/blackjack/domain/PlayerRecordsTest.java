package com.blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import com.blackjack.domain.user.Dealer;
import com.blackjack.domain.user.Player;
import com.blackjack.domain.user.PlayerFactory;

class PlayerRecordsTest {
	private Dealer dealer;
	private List<Player> players;
	private PlayerRecords playerRecords;

	@BeforeEach
	void setUp() {
		dealer = new Dealer();
		List<String> names = Arrays.asList("a", "b", "c");
		List<Integer> bettingMoneies = Arrays.asList(1_000, 1_000, 1_000);
		players = PlayerFactory.createPlayers(names, bettingMoneies);
		playerRecords = PlayerRecords.createPlayerRecords(dealer, players);
	}

	@DisplayName("dealer와 player리스트를 인자로 넣은 경우 인스턴스 생성")
	@Test
	void createPlayerRecords() {
		assertThat(PlayerRecords.createPlayerRecords(dealer, players)).isInstanceOf(PlayerRecords.class);
	}

	@DisplayName("dealer 또는 player리스트가 null일 경우 예외 발생")
	@Test
	void createPlayerRecords_DealerOrPlayerListIsNull_ExceptionThrown() {
		assertThatThrownBy(() -> PlayerRecords.createPlayerRecords(dealer, null))
				.isInstanceOf(NullPointerException.class);
		assertThatThrownBy(() -> PlayerRecords.createPlayerRecords(null, players))
				.isInstanceOf(NullPointerException.class);
	}

	@DisplayName("dealer와 player리스트를 인자로 넣은 경우 인스턴스 생성")
	@Test
	void calculateDealerResult() {
		assertThat(playerRecords.calculateDealerResult()).isEqualTo(0);
	}
}
