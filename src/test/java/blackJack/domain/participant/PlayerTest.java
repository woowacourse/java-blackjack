package blackJack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackJack.domain.card.Card;
import blackJack.domain.card.Denomination;
import blackJack.domain.card.Symbol;
import blackJack.domain.result.BlackJackMatch;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerTest {

    @ParameterizedTest()
    @ValueSource(strings = {" ", ""})
    void 플레이어의_이름이_공백인_경우_예외가_발생한다(String value) {
        assertThatThrownBy(() -> new Player(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름이 존재하지 않습니다.");
    }

    @Test
    void 플레이어의_이름이_딜러인_경우_예외가_발생한다() {
        assertThatThrownBy(() -> new Player("딜러"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름은 '딜러'일 수 없습니다.");
    }

    @Test
    void 플레이어가_승리하는_경우의_승부_결과를_계산한다() {
        Player player = new Player("rookie");
        player.hit(Card.of(Symbol.CLOVER, Denomination.ACE));
        player.hit(Card.of(Symbol.SPADE, Denomination.EIGHT));

        Dealer dealer = new Dealer();
        dealer.hit(Card.of(Symbol.SPADE, Denomination.ACE));
        dealer.hit(Card.of(Symbol.CLOVER, Denomination.SEVEN));

        assertThat(player.calculateMatchResult(dealer)).isEqualTo(BlackJackMatch.WIN);
    }

    @Test
    void 무승부인_경우의_승부_결과를_계산한다() {
        Player player = new Player("rookie");
        player.hit(Card.of(Symbol.CLOVER, Denomination.ACE));
        player.hit(Card.of(Symbol.SPADE, Denomination.EIGHT));

        Dealer dealer = new Dealer();
        dealer.hit(Card.of(Symbol.SPADE, Denomination.ACE));
        dealer.hit(Card.of(Symbol.CLOVER, Denomination.EIGHT));

        assertThat(player.calculateMatchResult(dealer)).isEqualTo(BlackJackMatch.DRAW);
    }

    @Test
    void 플레이어가_패배하는_경우의_승부_결과를_계산한다() {
        Player player = new Player("rookie");
        player.hit(Card.of(Symbol.CLOVER, Denomination.ACE));
        player.hit(Card.of(Symbol.SPADE, Denomination.SEVEN));

        Dealer dealer = new Dealer();
        dealer.hit(Card.of(Symbol.SPADE, Denomination.ACE));
        dealer.hit(Card.of(Symbol.CLOVER, Denomination.EIGHT));

        assertThat(player.calculateMatchResult(dealer)).isEqualTo(BlackJackMatch.LOSE);
    }

    @Test
    void 플레이어와_딜러가_모두_버스트로_플레이어가_패배하는_경우의_승부_결과를_계산한다() {
        Player player = new Player("rookie");
        player.hit(Card.of(Symbol.CLOVER, Denomination.JACK));
        player.hit(Card.of(Symbol.SPADE, Denomination.JACK));
        player.hit(Card.of(Symbol.SPADE, Denomination.TWO));

        Dealer dealer = new Dealer();
        dealer.hit(Card.of(Symbol.HEART, Denomination.JACK));
        dealer.hit(Card.of(Symbol.DIAMOND, Denomination.JACK));
        dealer.hit(Card.of(Symbol.DIAMOND, Denomination.TWO));

        assertThat(player.calculateMatchResult(dealer)).isEqualTo(BlackJackMatch.LOSE);
    }

    @Test
    void 딜러가_버스트로_플레이어가_승리하는_경우의_승부_결과를_계산한다() {
        Player player = new Player("rookie");
        player.hit(Card.of(Symbol.CLOVER, Denomination.JACK));
        player.hit(Card.of(Symbol.SPADE, Denomination.JACK));

        Dealer dealer = new Dealer();
        dealer.hit(Card.of(Symbol.HEART, Denomination.JACK));
        dealer.hit(Card.of(Symbol.DIAMOND, Denomination.JACK));
        dealer.hit(Card.of(Symbol.DIAMOND, Denomination.TWO));

        assertThat(player.calculateMatchResult(dealer)).isEqualTo(BlackJackMatch.WIN);
    }

    @Test
    void 점수는_같지만_플레이어가_블랙잭으로_승리하는_경우의_승부_결과를_계산한다() {
        Player player = new Player("rookie");
        player.hit(Card.of(Symbol.CLOVER, Denomination.JACK));
        player.hit(Card.of(Symbol.SPADE, Denomination.ACE));

        Dealer dealer = new Dealer();
        dealer.hit(Card.of(Symbol.HEART, Denomination.JACK));
        dealer.hit(Card.of(Symbol.DIAMOND, Denomination.NINE));
        dealer.hit(Card.of(Symbol.DIAMOND, Denomination.TWO));

        assertThat(player.calculateMatchResult(dealer)).isEqualTo(BlackJackMatch.BLACK_JACK_WIN);
    }

    @Test
    void 점수는_같지만_딜러가_블랙잭으로_플레이어가_패배하는_경우의_승부_결과를_계산한다() {
        Player player = new Player("rookie");
        player.hit(Card.of(Symbol.HEART, Denomination.JACK));
        player.hit(Card.of(Symbol.DIAMOND, Denomination.NINE));
        player.hit(Card.of(Symbol.DIAMOND, Denomination.TWO));

        Dealer dealer = new Dealer();
        dealer.hit(Card.of(Symbol.CLOVER, Denomination.JACK));
        dealer.hit(Card.of(Symbol.SPADE, Denomination.ACE));

        assertThat(player.calculateMatchResult(dealer)).isEqualTo(BlackJackMatch.LOSE);
    }
}
