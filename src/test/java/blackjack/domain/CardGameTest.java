package blackjack.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.domain.CardNumber.ACE;
import static blackjack.domain.CardShape.SPADE;
import static blackjack.fixture.PlayerFixture.dealer;
import static blackjack.fixture.PlayerFixture.playerA;
import static blackjack.fixture.PlayerFixture.playerAWithEmptyHand;
import static blackjack.fixture.PlayerFixture.playerB;
import static blackjack.fixture.PlayerFixture.playerBWithEmptyHand;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class CardGameTest {
    @Test
    void 카드_한_장을_플레이어에게_지급한다() {
        Player mangcho = playerAWithEmptyHand();
        Card card = new Card(ACE, SPADE);

        CardGame cardGame = new CardGame();
        cardGame.giveCard(mangcho, card);

        assertThat(mangcho.getCards().size()).isEqualTo(1);
    }

    @Test
    void 모든_플레이어에게_카드_2장을_지급한다() {
        Player mangcho = playerAWithEmptyHand();
        Player ddang = playerBWithEmptyHand();

        CardGame cardGame = new CardGame();
        cardGame.giveTwoCardsEachPlayer(List.of(mangcho, ddang));

        assertSoftly(softly -> {
            softly.assertThat(mangcho.getCards().size()).isEqualTo(2);
            softly.assertThat(ddang.getCards().size()).isEqualTo(2);
        });
    }

    @Test
    void 딜러와_여러_플레이어의_숫자가_21_이하인_경우_숫자가_큰_사람이_이긴다() {
        CardGameJudge cardGameJudge = new CardGameJudge();
        Player mangcho = playerA();
        Player ddang = playerB();
        Dealer dealer = dealer();

        var result = cardGameJudge.judge(dealer, List.of(mangcho, ddang))
                .getTotalResult();

        assertThat(result.get(mangcho)).isEqualTo(WinningStatus.WIN);
        assertThat(result.get(ddang)).isEqualTo(WinningStatus.WIN);
    }
}
