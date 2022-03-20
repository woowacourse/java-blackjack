package blackjack.domain.role;

import static blackjack.util.Fixtures.CLOVER_ACE;
import static blackjack.util.Fixtures.CLOVER_KING;
import static blackjack.util.Fixtures.CLOVER_TWO;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.game.Deck;
import blackjack.domain.game.Money;
import blackjack.domain.state.Ready;
import blackjack.domain.state.State;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Roles 테스트")
class RolesTest {

	@Test
	@DisplayName("블랙잭 게임을 위해서 카드 2장을 준비하는 메서드 검증")
	void ready() {
		Role dealer = new Dealer(new Ready(), DealerDrawChoice::chooseDraw);
		Role player = new Player("player", new Ready(), new Money(1000));
		Roles roles = new Roles(dealer, List.of(player));
		Deck deck = new Deck();
		roles.ready(deck);
		assertAll(
				() -> assertThat(dealer.getCards().getSize()).isEqualTo(2),
				() -> assertThat(player.getCards().getSize()).isEqualTo(2)
		);
	}

	@Test
	@DisplayName("블랙잭 게임에서 딜러가 블랙잭인 경우 확인")
	void is_Dealer_Blackjack() {
		State blackjack = new Ready().draw(CLOVER_ACE).draw(CLOVER_KING);
		Role dealer = new Dealer(blackjack, DealerDrawChoice::chooseDraw);
		Role player = new Player("player", new Ready(), new Money(1000));
		Roles roles = new Roles(dealer, List.of(player));
		assertThat(roles.isDealerBlackjack()).isTrue();
	}

	@Test
	@DisplayName("플레이어의 드로우를 요청할 때 해당 이름을 가진 플레이어가 없으면 예외 발생")
	void draw_Player_With_Wrong_Name() {
		Role dealer = new Dealer(new Ready(), DealerDrawChoice::chooseDraw);
		Role player = new Player("player", new Ready(), new Money(1000));
		Roles roles = new Roles(dealer, List.of(player));
		assertThatThrownBy(() -> roles.drawPlayer(PlayerDrawChoice.YES, "wrongName", new Deck()))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("해당 플레이어를 찾을 수 없습니다.");
	}

	@Test
	@DisplayName("딜러가 한장 드로우 후에는 상태가 finished임을 검증")
	void draw_Dealer() {
		Role dealer = new Dealer(new Ready(), DealerDrawChoice::chooseDraw);
		Role player = new Player("player", new Ready(), new Money(1000));
		Roles roles = new Roles(dealer, List.of(player));
		Deck deck = new Deck();
		roles.ready(deck);
		Role afterDrawDealer = roles.drawDealer(deck);
		assertThat(afterDrawDealer.isFinished()).isTrue();
	}

	@Test
	@DisplayName("플레이어와 딜러의 카드 승부 후 베팅 수익 검증")
	void get_Players_Profit() {
		State blackjack = new Ready().draw(CLOVER_ACE).draw(CLOVER_KING);
		State noneBlackjack = new Ready().draw(CLOVER_KING).draw(CLOVER_TWO).stay();
		Role dealer = new Dealer(blackjack, DealerDrawChoice::chooseDraw);
		Role player = new Player("player", noneBlackjack, new Money(1000));
		Roles roles = new Roles(dealer, List.of(player));
		Map<String, Money> playerProfit = roles.getPlayersProfit();
		assertThat(playerProfit.get("player")).isEqualTo(new Money(-1000));
	}
}

