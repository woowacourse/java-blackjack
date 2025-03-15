package blackjack.model;

import static blackjack.model.card.CardFixtures.NO_SHUFFLER;
import static blackjack.model.card.CardFixtures.createCard;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.model.card.CardValue;
import blackjack.model.card.Deck;
import blackjack.model.card.Suit;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Name;
import blackjack.model.participant.Player;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("배팅 테이블 테스트")
class BettingTableTest {

    @DisplayName("플레이어 이름으로 배팅할 수 있다.")
    @Test
    void betTest() {
        // given
        BettingTable bettingTable = new BettingTable();
        Player player = new Player(new Name("포비"));

        // when, then
        assertThatCode(() -> bettingTable.bet(player, 1_000))
                .doesNotThrowAnyException();
    }

    @DisplayName("플레이어 이름으로 배팅 금액을 확인한다.")
    @Test
    void getBetAmountTest() {
        // given
        Player player = new Player(new Name("포비"));
        BettingTable bettingTable = new BettingTable();
        bettingTable.bet(player, 1_000);

        // when
        int bettingAmount = bettingTable.getBetAmount(player);

        // then
        assertThat(bettingAmount)
                .isEqualTo(1_000);
    }

    @DisplayName("배팅 금액을 확인할 때 배팅한 플레이어를 찾을 수 없는 경우 예외가 발생한다.")
    @Test
    void shouldThrowException_WhenNotFoundBettingPlayer() {
        // given
        BettingTable bettingTable = new BettingTable();
        Player player = new Player(new Name("포비"));

        // when, then
        assertThatCode(() -> bettingTable.getBetAmount(player))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 내역을 찾을 수 없습니다.");
    }

    @DisplayName("플레이어들의 배당금을 계산할 수 있다.")
    @ParameterizedTest
    @CsvSource({
            "TEN, ACE, 2.5",
            "TEN, TEN, 2",
            "NINE, NINE, 1",
            "EIGHT, EIGHT, 0",
    })
    void calculatePayoutsTest(CardValue playerCardValue1, CardValue playerCardValue2, double multiplier) {
        // given
        Player player = new Player(new Name("포비"));
        player.receiveCard(createCard(Suit.SPADES, playerCardValue1));
        player.receiveCard(createCard(Suit.SPADES, playerCardValue2));
        if (!player.getState().isFinished()) {
            player.stand();
        }
        Dealer dealer = new Dealer(Deck.createStandardDeck(NO_SHUFFLER));
        dealer.receiveCard(createCard(Suit.SPADES, CardValue.NINE));
        dealer.receiveCard(createCard(Suit.SPADES, CardValue.NINE));
        BettingTable bettingTable = new BettingTable();
        int betAmount = 1_000;
        bettingTable.bet(player, betAmount);

        // when
        Map<Player, Integer> payouts = bettingTable.calculatePayouts(dealer);

        // then
        assertThat(payouts)
                .containsEntry(player, (int) (betAmount * multiplier));
    }

    @DisplayName("플레이어들의 배팅 금액을 조회할 수 있다.")
    @Test
    void getBettingTest() {
        // given
        Player player = new Player(new Name("포비"));
        BettingTable bettingTable = new BettingTable();
        int betAmount = 1_000;
        bettingTable.bet(player, betAmount);

        // when
        Map<Player, Integer> betting = bettingTable.getBetting();

        // then
        assertThat(betting)
                .containsEntry(player, betAmount);
    }
}
