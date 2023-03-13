package blackjack.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.user.Dealer;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BlackjackGameTest {

    @Test
    void 게임은_시작_처음_2회_카드를_뽑는다() {
        // given
        BlackjackGame blackjackGame = new BlackjackGame();
        Dealer dealer = new Dealer();

        // when
        blackjackGame.initDraw(dealer);

        // then
        assertThat(dealer.getCards().size()).isEqualTo(2);
    }
}
