package com.blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.blackjack.domain.user.Name;
import com.blackjack.domain.user.Player;
import com.blackjack.domain.user.User;

class PlayerRecordsTest {
	@DisplayName("인스턴스 생성")
	@Test
	void calculateDealerResult() {
		Map<User, Integer> records = new HashMap<>();
		records.put(new Player(new Name("pobi")), 10_000);
		PlayerRecords playerRecords = new PlayerRecords(records);
		assertThat(playerRecords.calculateDealerResult()).isEqualTo(-10_000);
	}
}
