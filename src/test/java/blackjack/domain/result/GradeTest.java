package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.CardShape;
import blackjack.domain.game.Dealer;
import blackjack.domain.game.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GradeTest {

    private Dealer dealer;
    private Player player;
    private Card aceSpade;
    private Card tenSpade;

    @BeforeEach
    void init() {
        dealer = new Dealer();
        player = new Player("pobi");
        aceSpade = Card.of(Denomination.ACE, CardShape.SPADE);
        tenSpade = Card.of(Denomination.KING, CardShape.SPADE);
    }

    @DisplayName("딜러와 플레이어 모두 블랙잭일 경우 무승부임을 확인한다.")
    @Test
    void grade_to_init_cards_tie() {
        dealer.dealCards(List.of(aceSpade, tenSpade));
        player.dealCards(List.of(aceSpade, tenSpade));

        assertThat(Grade.gradeToInitCards(dealer, player)).isEqualTo(Grade.TIE);
    }

    @DisplayName("딜러는 블랙잭이나 플레이어는 블랙잭이 아닐 경우 패배임을 확인한다.")
    @Test
    void grade_to_init_cards_lose() {
        dealer.dealCards(List.of(aceSpade, tenSpade));
        player.dealCards(List.of(tenSpade, tenSpade));

        assertThat(Grade.gradeToInitCards(dealer, player)).isEqualTo(Grade.LOSE);
    }

    @DisplayName("딜러는 블랙잭이 아니나 플레이어가 블랙잭일 경우 승리임을 확인한다.")
    @Test
    void grade_to_init_cards_win() {
        dealer.dealCards(List.of(tenSpade, tenSpade));
        player.dealCards(List.of(aceSpade, tenSpade));

        assertThat(Grade.gradeToInitCards(dealer, player)).isEqualTo(Grade.BLACKJACK_WIN);
    }

    @DisplayName("딜러와 플레이어 모두 블랙잭이 없을 경우 게임을 진행하는 것을 확인한다.")
    @Test
    void grade_to_init_cards_proceed() {
        dealer.dealCards(List.of(tenSpade, tenSpade));
        player.dealCards(List.of(tenSpade, tenSpade));

        assertThat(Grade.gradeToInitCards(dealer, player)).isEqualTo(Grade.PROCEED);
    }

    @DisplayName("플레이어가 버스트일 경우 패배임을 확인한다.")
    @Test
    void grade_player_bust() {
        dealer.dealCards(List.of(tenSpade, tenSpade));
        player.dealCards(List.of(tenSpade, tenSpade, tenSpade));

        assertThat(Grade.grade(dealer, player)).isEqualTo(Grade.LOSE);
    }

    @DisplayName("딜러가 버스트일 경우 승리임을 확인한다.")
    @Test
    void grade_dealer_bust() {
        dealer.dealCards(List.of(tenSpade, tenSpade, tenSpade));
        player.dealCards(List.of(tenSpade, tenSpade));

        assertThat(Grade.grade(dealer, player)).isEqualTo(Grade.WIN);
    }

    @DisplayName("모두 버스트가 아니며 동점일 경우 무승부임을 확인한다.")
    @Test
    void grade_tie() {
        dealer.dealCards(List.of(tenSpade, tenSpade));
        player.dealCards(List.of(tenSpade, tenSpade));

        assertThat(Grade.grade(dealer, player)).isEqualTo(Grade.TIE);
    }

    @DisplayName("모두 버스트가 아니며 플레이어의 점수가 더 높을 경우 승리임을 확인한다.")
    @Test
    void grade_win() {
        dealer.dealCards(List.of(tenSpade));
        player.dealCards(List.of(tenSpade, tenSpade));

        assertThat(Grade.grade(dealer, player)).isEqualTo(Grade.WIN);
    }

    @DisplayName("모두 버스트가 아니며 플레이어의 점수가 더 낮을 경우 패배임을 확인한다.")
    @Test
    void grade_lose() {
        dealer.dealCards(List.of(tenSpade, tenSpade));
        player.dealCards(List.of(tenSpade));

        assertThat(Grade.grade(dealer, player)).isEqualTo(Grade.LOSE);
    }
}