package com.blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.blackjack.domain.user.PlayerFactory;
import com.blackjack.domain.user.User;

class PlayerRecordsTest {
	@DisplayName("인스턴스 생성")
	@Test
	void constructor() {
		Map<User, ResultType> records = new HashMap<>();
		records.put(PlayerFactory.createPlayers("pobi").get(0), ResultType.WIN);
		assertThat(new PlayerRecords(records)).isInstanceOf(PlayerRecords.class);
	}

	@DisplayName("유저의 승패 리스트를 받아 ")
	@Test
	void calculateDealerResult_given() {
		Map<User, ResultType> records = new HashMap<>();
		records.put(PlayerFactory.createPlayers("pobi").get(0), ResultType.WIN);
		records.put(PlayerFactory.createPlayers("stitch").get(0), ResultType.LOSE);
		records.put(PlayerFactory.createPlayers("alt").get(0), ResultType.LOSE);
		records.put(PlayerFactory.createPlayers("turtle").get(0), ResultType.DRAW);
		PlayerRecords playerRecords = new PlayerRecords(records);

		Map<ResultType, Long> expect = new HashMap<>();
		expect.put(ResultType.WIN, 2L);
		expect.put(ResultType.LOSE, 1L);
		expect.put(ResultType.DRAW, 1L);

		assertThat(playerRecords.calculateDealerResult())
				.isEqualTo(expect);
	}
}
