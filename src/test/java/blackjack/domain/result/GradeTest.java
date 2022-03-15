package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.game.Dealer;
import blackjack.domain.game.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GradeTest {

    private Dealer dealer;
    private Player player;
    private Card aceSpade;
    private Card tenSpade;

    @BeforeEach
    void init() {
        dealer = new Dealer();
        player = new Player("pobi");
        aceSpade = Card.of(CardNumber.ACE, CardShape.SPADE);
        tenSpade = Card.of(CardNumber.KING, CardShape.SPADE);
    }

    @DisplayName("딜러와 플레이어 모두 블랙잭일 경우 무승부임을 확인한다.")
    @Test
    void grade_tie() {
        dealer.dealCards(List.of(aceSpade, tenSpade));
        player.dealCards(List.of(aceSpade, tenSpade));

        assertThat(Grade.grade(dealer, player)).isEqualTo(Grade.TIE);
    }

    @DisplayName("딜러는 블랙잭이나 플레이어는 블랙잭이 아닐 경우 패배임을 확인한다.")
    @Test
    void grade_lose() {
        dealer.dealCards(List.of(aceSpade, tenSpade));
        player.dealCards(List.of(tenSpade, tenSpade));

        assertThat(Grade.grade(dealer, player)).isEqualTo(Grade.LOSE);
    }

    @DisplayName("딜러는 블랙잭이 아니나 플레이어가 블랙잭일 경우 승리임을 확인한다.")
    @Test
    void grade_win() {
        dealer.dealCards(List.of(tenSpade, tenSpade));
        player.dealCards(List.of(aceSpade, tenSpade));

        assertThat(Grade.grade(dealer, player)).isEqualTo(Grade.WIN);
    }

    @DisplayName("딜러와 플레이어 모두 블랙잭이 없을 경우 게임을 진행하는 것을 확인한다.")
    @Test
    void grade_proceed() {
        dealer.dealCards(List.of(tenSpade, tenSpade));
        player.dealCards(List.of(tenSpade, tenSpade));

        assertThat(Grade.grade(dealer, player)).isEqualTo(Grade.PROCEED);
    }

    // TODO: 플레이어가 버스트 -> 패

    // TODO: 딜러가 버스트 -> 승

    // TODO: 동점 -> 무승부

    // TODO: 점수가 더 높음 -> 승

    // TODO: 점수가 낮음 -> 패
}