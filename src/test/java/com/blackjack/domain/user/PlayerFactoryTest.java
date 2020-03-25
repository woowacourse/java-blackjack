package com.blackjack.domain.user;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.blackjack.util.StringUtil;

class PlayerFactoryTest {
	@DisplayName("플레이어와 베팅액을 입력받아 플레이어 리스트 생성")
	@Test
	void createPlayers() {
		List<String> names = StringUtil.splitByDelimiter("pobi, turtle, stitch, ttoring");
		List<Integer> bettingMonies = Arrays.asList(1_000, 1_000, 1_000, 1_000);

		List<Player> players = PlayerFactory.createPlayers(names, bettingMonies);

		assertThat(players.size()).isEqualTo(4);
	}

	@DisplayName("인자로 중복되는 이름을 넣었을 때 예외 발생")
	@Test
	void createPlayers_DuplicatedName_ExceptionThrown() {
		List<String> names = StringUtil.splitByDelimiter("pobi, pobi, stitch, ttoring");
		List<Integer> bettingMonies = Arrays.asList(1_000, 1_000, 1_000, 1_000);
		assertThatThrownBy(() -> PlayerFactory.createPlayers(names, bettingMonies)).isInstanceOf(
				IllegalArgumentException.class);
	}

	@DisplayName("참여자의 수가 범위를 벗어나는 경우 예외 발생")
	@Test
	void createPlayers_OutOfBounds_ExceptionThrown() {
		List<String> names = StringUtil.splitByDelimiter("pobi1, pobi2, pobi3, pobi4, pobi5, pobi6, pobi7, pobi8");
		List<Integer> bettingMonies = Arrays.asList(1_000, 1_000, 1_000, 1_000, 1_000, 1_000, 1_000, 1_000);

		assertThatThrownBy(() -> PlayerFactory.createPlayers(names, bettingMonies)).isInstanceOf(
				IllegalArgumentException.class);
	}

	@DisplayName("참여자의 수와 베팅액의 크기가 일치하지 않는 경우 예외 발생")
	@Test
	void createPlayers_NameMoneySizeMismatch_ExceptionThrown() {
		List<String> names = StringUtil.splitByDelimiter("pobi1, pobi2, pobi3, pobi4, pobi5, pobi6, pobi7");
		List<Integer> bettingMonies = Arrays.asList(1_000, 2_000, 3_000, 4_000, 5_000, 6_000);

		assertThatThrownBy(() -> PlayerFactory.createPlayers(names, bettingMonies)).isInstanceOf(
				IllegalArgumentException.class);
	}
}
