package blackJack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackJack.domain.card.Card;
import blackJack.domain.card.Denomination;
import blackJack.domain.card.Symbol;
import blackJack.domain.result.MatchResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("Dealer 생성 테스트")
    void createValidDealer() {
        assertThat(new Dealer()).isNotNull();
    }

    @Test
    @DisplayName("딜러의 카드 합계 계산 테스트")
    void calculateScore() {
        Dealer dealer = new Dealer();
        dealer.hit(Card.from(Symbol.CLOVER, Denomination.EIGHT));

        assertThat(dealer.getScore()).isEqualTo(8);
    }

    @Test
    @DisplayName("딜러의 카드 추가 분배가 불가능한 경우 테스트")
    void hasFalseDealerNextTurn() {
        Dealer dealer = new Dealer();
        dealer.hit(Card.from(Symbol.SPADE, Denomination.SEVEN));
        dealer.hit(Card.from(Symbol.HEART, Denomination.JACK));

        assertThat(dealer.canHit()).isFalse();
    }

    @Test
    @DisplayName("딜러의 카드 추가 분배가 가능한 경우 테스트")
    void hasTrueDealerNextTurn() {
        Dealer dealer = new Dealer();
        dealer.hit(Card.from(Symbol.SPADE, Denomination.SIX));
        dealer.hit(Card.from(Symbol.HEART, Denomination.JACK));

        assertThat(dealer.canHit()).isTrue();
    }

    @Test
    @DisplayName("딜러가 승리하는 경우 승부 결과 반환 테스트")
    void getMatchResultDealerWin() {
        Dealer dealer = new Dealer();
        dealer.hit(Card.from(Symbol.CLOVER, Denomination.ACE));
        dealer.hit(Card.from(Symbol.SPADE, Denomination.EIGHT));

        Player player = new Player("rookie");
        player.hit(Card.from(Symbol.SPADE, Denomination.ACE));
        player.hit(Card.from(Symbol.CLOVER, Denomination.SEVEN));

        assertThat(dealer.getMatchResult(player)).isEqualTo(MatchResult.WIN);
    }

    @Test
    @DisplayName("딜러가 무승부인 경우 승부 결과 반환 테스트")
    void getMatchResultDealerDraw() {
        Dealer dealer = new Dealer();
        dealer.hit(Card.from(Symbol.SPADE, Denomination.ACE));
        dealer.hit(Card.from(Symbol.CLOVER, Denomination.EIGHT));

        Player player = new Player("rookie");
        player.hit(Card.from(Symbol.CLOVER, Denomination.ACE));
        player.hit(Card.from(Symbol.SPADE, Denomination.EIGHT));

        assertThat(dealer.getMatchResult(player)).isEqualTo(MatchResult.DRAW);
    }

    @Test
    @DisplayName("딜러가 패배하는 경우 승부 결과 반환 테스트")
    void getMatchResultDealerLose() {
        Dealer dealer = new Dealer();
        dealer.hit(Card.from(Symbol.SPADE, Denomination.ACE));
        dealer.hit(Card.from(Symbol.CLOVER, Denomination.SEVEN));

        Player player = new Player("rookie");
        player.hit(Card.from(Symbol.CLOVER, Denomination.ACE));
        player.hit(Card.from(Symbol.SPADE, Denomination.EIGHT));

        assertThat(dealer.getMatchResult(player)).isEqualTo(MatchResult.LOSE);
    }

    @Test
    @DisplayName("플레이어와 딜러 모두 버스트로 딜러가 승리하는 경우 승부 결과 반환 테스트")
    void getMatchResultAllBurst() {
        Dealer dealer = new Dealer();
        dealer.hit(Card.from(Symbol.HEART, Denomination.JACK));
        dealer.hit(Card.from(Symbol.DIAMOND, Denomination.JACK));
        dealer.hit(Card.from(Symbol.DIAMOND, Denomination.TWO));

        Player player = new Player("rookie");
        player.hit(Card.from(Symbol.CLOVER, Denomination.JACK));
        player.hit(Card.from(Symbol.SPADE, Denomination.JACK));
        player.hit(Card.from(Symbol.SPADE, Denomination.TWO));

        assertThat(dealer.getMatchResult(player)).isEqualTo(MatchResult.WIN);
    }

    @Test
    @DisplayName("점수는 같지만 플레이어가 블랙잭으로 딜러가 패배하는 경우 승부 결과 반환 테스트")
    void getMatchResultPlayerBlackJack() {
        Dealer dealer = new Dealer();
        dealer.hit(Card.from(Symbol.HEART, Denomination.JACK));
        dealer.hit(Card.from(Symbol.DIAMOND, Denomination.NINE));
        dealer.hit(Card.from(Symbol.DIAMOND, Denomination.TWO));

        Player player = new Player("rookie");
        player.hit(Card.from(Symbol.CLOVER, Denomination.JACK));
        player.hit(Card.from(Symbol.SPADE, Denomination.ACE));

        assertThat(dealer.getMatchResult(player)).isEqualTo(MatchResult.LOSE);
    }

    @Test
    @DisplayName("점수는 같지만 딜러가 블랙잭으로 딜러가 승리하는 경우 승부 결과 반환 테스트")
    void getMatchResultSameScoreDealerBlackJack() {
        Player player = new Player("rookie");
        player.hit(Card.from(Symbol.HEART, Denomination.JACK));
        player.hit(Card.from(Symbol.DIAMOND, Denomination.NINE));
        player.hit(Card.from(Symbol.DIAMOND, Denomination.TWO));

        Dealer dealer = new Dealer();
        dealer.hit(Card.from(Symbol.CLOVER, Denomination.JACK));
        dealer.hit(Card.from(Symbol.SPADE, Denomination.ACE));

        assertThat(dealer.getMatchResult(player)).isEqualTo(MatchResult.WIN);
    }
}
