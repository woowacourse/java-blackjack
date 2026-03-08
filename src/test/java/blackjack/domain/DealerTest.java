package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    void 딜러_생성_테스트() {
        // when & then
        assertThatCode(Dealer::new)
                .doesNotThrowAnyException();
    }

    @Test
    void 승패_판단_테스트() {
        // given
        Player player = new Player("흑곰");
        Dealer dealer = new Dealer();
        player.get(new Card(CardValue.SEVEN, CardShape.DIAMOND));
        dealer.get(new Card(CardValue.EIGHT, CardShape.DIAMOND));

        // when
        boolean isDealerWinning = dealer.winsAgainst(player);

        // then
        assertThat(isDealerWinning).isTrue();
    }

}
