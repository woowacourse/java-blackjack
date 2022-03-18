package blackjack.domain.participant;

import static blackjack.domain.fixture.CardRepository.CLOVER10;
import static blackjack.domain.fixture.CardRepository.CLOVER2;
import static blackjack.domain.fixture.CardRepository.CLOVER3;
import static blackjack.domain.fixture.CardRepository.CLOVER4;
import static blackjack.domain.fixture.CardRepository.CLOVER5;
import static blackjack.domain.fixture.CardRepository.CLOVER6;
import static blackjack.domain.fixture.CardRepository.CLOVER7;
import static blackjack.domain.fixture.CardRepository.CLOVER_ACE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Hand;
import blackjack.domain.money.Money;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    private Players players;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        players = new Players();
        players.add(Player.of("winPlayer", Hand.of(CLOVER6, CLOVER7), Money.from(10000)));
        players.add(Player.of("blackjackWinPlayer", Hand.of(CLOVER_ACE, CLOVER10), Money.from(10000)));
        players.add(Player.of("losePlayer", Hand.of(CLOVER2, CLOVER3), Money.from(10000)));

        dealer = Dealer.of(Hand.of(CLOVER4, CLOVER5));
    }

    @DisplayName("add 에 Player 인스턴스를 전달하면 리스트에 Player 가 생성되어 추가된다.")
    @Test
    void add() {
        // given
        String playerName = "hudi";
        Hand hand = Hand.of(CLOVER2, CLOVER3);
        Money betMoney = Money.from(10000);
        Player player = Player.of(playerName, hand, betMoney);

        // when
        Players players = new Players();
        players.add(player);

        List<Player> actual = players.getValue();
        List<Player> expected = List.of(player);

        // then
        assertThat(actual).containsAll(expected);
    }

    @DisplayName("add 에 중복된 이름이 전달되면 예외가 발생한다.")
    @Test
    void add_throwsExceptionIfDuplicateNameInput() {
        // given
        String playerName = "winPlayer";
        Hand hand = Hand.of(CLOVER2, CLOVER3);
        Money betMoney = Money.from(10000);
        Player player = Player.of(playerName, hand, betMoney);

        // when & then
        Assertions.assertThatThrownBy(() -> players.add(player))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 이름은 중복될 수 없습니다.");
    }

    @DisplayName("calculateProfit 은 플레이어와 딜러를 전달받아 플레이어의 수익을 계산하여 Money 인스턴스로 반환한다.")
    @Test
    void calculateProfit_returnsProfitOfPlayer() {
        // given & when
        Money actual = players.calculateProfit(
                Player.of("winPlayer", Hand.of(CLOVER6, CLOVER7), Money.from(10000)),
                dealer
        );
        Money expected = Money.from(10000);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("calculateProfit 에 존재하지 않는 플레이어를 전달하면 예외가 발생한다.")
    @Test
    void calculateProfit_throwsOnNotFoundPlayer() {
        // given
        Player notExistingPlayer = Player.of("unknown", Hand.of(CLOVER4, CLOVER5), Money.from(10000));

        // when & then
        assertThatThrownBy(() -> players.calculateProfit(notExistingPlayer, dealer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어를 찾을 수 없습니다.");
    }

    @DisplayName("calculateTotalProfit 은 딜러를 전달받아 모든 플레이어의 수익을 계산하여 Money 인스턴스로 반환한다.")
    @Test
    void calculateTotalProfit_returnsSumOfProfit() {
        // given & when
        Money actual = players.calculateTotalProfit(dealer);
        Money expected = Money.from(15000);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}