package com.blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.blackjack.domain.user.PlayerFactory;
import com.blackjack.domain.user.User;

public class PlayerRecordsTest {
	@DisplayName("인스턴스 생성")
	@Test
	void constructor() {
		Map<User, ResultType> records = new HashMap<>();
		records.put(PlayerFactory.createPlayers("pobi").get(0), ResultType.WIN);
		assertThat(new PlayerRecords(records)).isInstanceOf(PlayerRecords.class);
	}
}
