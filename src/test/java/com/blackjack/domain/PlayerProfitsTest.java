package com.blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.blackjack.domain.user.Player;
import com.blackjack.domain.user.User;

class PlayerProfitsTest {
	@DisplayName("인스턴스 생성")
	@Test
	void calculateDealerResult() {
		Map<User, Integer> records = new HashMap<>();
		records.put(new Player("pobi", 10_000), 10_000);
		PlayerProfits playerProfits = new PlayerProfits(records);
		assertThat(playerProfits.calculateDealerProfit()).isEqualTo(-10_000);
	}
}
