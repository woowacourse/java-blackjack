package blackjack.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.strategy.NumberGenerator;

public class BlackjackTest {
	private Blackjack blackjack;
	private IntendedNumberGenerator intendedNumberGenerator;

	@BeforeEach
	void setUp() {
		List<String> playerNames = List.of("pobi", "jason");
		blackjack = new Blackjack(playerNames);
		intendedNumberGenerator = new IntendedNumberGenerator(List.of(1, 2, 3, 4, 5, 6));

		blackjack.dealInitialCards(intendedNumberGenerator);
	}

	@DisplayName("아무 이름도 들어오지 않는 경우 에러 테스트")
	@Test
	void nothing() {
		List<String> playerNames = new ArrayList<>();

		assertThatThrownBy(() -> new Blackjack(playerNames)).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("이름 중복 에러 테스트")
	@Test
	void duplicate() {
		List<String> playerNames = List.of("pobi", "pobi");

		assertThatThrownBy(() -> new Blackjack(playerNames)).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("플레이어, 딜러 카드 두장 분배 테스트")
	@Test
	void distributeInit() {
		Participant dealer = blackjack.getDealer();
		List<Player> players = blackjack.getPlayers();

		Set<Integer> checker = new HashSet<>();
		int dealerCardSize = dealer.getMyCards().size();
		checker.add(dealerCardSize);

		players.forEach(player -> checker.add(player.getMyCards().size()));

		assertThat(checker.size() == 1 && checker.contains(2)).isTrue();
	}

	@DisplayName("카드 한장 더 분배 테스트_딜러 hit")
	@Test
	void distributeOneMoreCardDealer() {
		NumberGenerator numberGenerator = new IntendedNumberGenerator(List.of(10));
		blackjack.dealAdditionalCardToDealer(numberGenerator);
		assertThat(blackjack.getDealer().getMyCards().size()).isEqualTo(3);
	}

	@DisplayName("카드 한장 더 분배 테스트_딜러 stay")
	@Test
	void distributeOneMoreCardDealer2() {
		NumberGenerator numberGenerator = new IntendedNumberGenerator(List.of(10, 4));
		blackjack.dealAdditionalCardToDealer(numberGenerator);
		assertThat(blackjack.getDealer().getMyCards().size()).isEqualTo(3);
	}

	@DisplayName("카드 한장 더 분배 테스트_플레이어")
	@Test
	void distributeOneMoreCardPlayer() {
		NumberGenerator numberGenerator = new IntendedNumberGenerator(List.of(10));
		Player player = blackjack.getPlayers().get(0);
		blackjack.dealAdditionalCardToPlayer(numberGenerator, player);

		assertThat(player.getMyCards().size()).isEqualTo(3);
	}

	@DisplayName("최종 승패 기능 테스트")
	@Test
	void result() {
		assertDoesNotThrow(() -> blackjack.result());
	}
}
