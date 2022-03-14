package blackjack.domain;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.machine.Blackjack;
import blackjack.domain.machine.Card;
import blackjack.domain.machine.Record;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class RecordTest {
	private Blackjack blackjack;
	private Player player;
	private Dealer dealer;

	@BeforeEach
	void setUp() {
		List<String> playerNames = List.of("범블비");
		blackjack = new Blackjack(playerNames);
		IntendedNumberGenerator intendedNumberGenerator = new IntendedNumberGenerator(List.of(1, 2, 11, 8));
		blackjack.dealInitialCards(intendedNumberGenerator);

		//dealer: 12점, player: 12점
		player = blackjack.getPlayers().get(0);
		dealer = blackjack.getDealer();
	}

	@DisplayName("딜러만 버스트일 경우 전적 테스트")
	@Test
	void dealerBurst() {
		IntendedNumberGenerator intendedNumberGenerator = new IntendedNumberGenerator(List.of(10));
		Card card = dealer.handOutCard(intendedNumberGenerator);
		dealer.addCard(card);

		assertThat(Record.getRecord(player, dealer)).isEqualTo(Record.VICTORY);
	}

	@DisplayName("플레이어만 버스트일 경우 전적 테스트")
	@Test
	void playerBurst() {
		IntendedNumberGenerator intendedNumberGenerator = new IntendedNumberGenerator(List.of(10));
		Card card = dealer.handOutCard(intendedNumberGenerator);
		player.addCard(card);

		assertThat(Record.getRecord(player, dealer)).isEqualTo(Record.DEFEAT);
	}

	@DisplayName("딜러, 플레이어 모두 버스트일 경우 전적 테스트")
	@Test
	void dealerPlayerBurst() {
		IntendedNumberGenerator intendedNumberGenerator1 = new IntendedNumberGenerator(List.of(10));
		Card card1 = dealer.handOutCard(intendedNumberGenerator1);
		player.addCard(card1);

		IntendedNumberGenerator intendedNumberGenerator2 = new IntendedNumberGenerator(List.of(9));
		Card card2 = dealer.handOutCard(intendedNumberGenerator2);
		dealer.addCard(card2);

		assertThat(Record.getRecord(player, dealer)).isEqualTo(Record.DEFEAT);
	}

	@DisplayName("버스트가 아닌 경우 무승부 전적 테스트")
	@Test
	void ordinaryDrawRecord() {
		assertThat(Record.getRecord(player, dealer)).isEqualTo(Record.DRAW);
	}

	@DisplayName("버스드가 아닌 경우 딜러 승리 전적 테스트")
	@Test
	void ordinaryDefeatTest() {
		// 7하트
		IntendedNumberGenerator intendedNumberGenerator1 = new IntendedNumberGenerator(List.of(18));
		Card card1 = dealer.handOutCard(intendedNumberGenerator1);
		player.addCard(card1);

		// 9하트
		IntendedNumberGenerator intendedNumberGenerator2 = new IntendedNumberGenerator(List.of(20));
		Card card2 = dealer.handOutCard(intendedNumberGenerator2);
		dealer.addCard(card2);

		assertThat(Record.getRecord(player, dealer)).isEqualTo(Record.DEFEAT);
	}

	@DisplayName("버스드가 아닌 경우 플레이어 승리 전적 테스트")
	@Test
	void ordinaryVictoryTest() {
		IntendedNumberGenerator intendedNumberGenerator1 = new IntendedNumberGenerator(List.of(20));
		Card card1 = dealer.handOutCard(intendedNumberGenerator1);
		System.out.println(card1.getName());
		player.addCard(card1);

		IntendedNumberGenerator intendedNumberGenerator2 = new IntendedNumberGenerator(List.of(18));
		Card card2 = dealer.handOutCard(intendedNumberGenerator2);
		System.out.println(card2.getName());
		dealer.addCard(card2);

		assertThat(Record.getRecord(player, dealer)).isEqualTo(Record.VICTORY);
	}
}
