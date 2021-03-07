package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.carddeck.Card;
import blackjack.domain.carddeck.Pattern;
import blackjack.domain.carddeck.Number;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultTest {

    @Test
    @DisplayName("플레이어의 승패에 따라 딜러 승패가 잘 결정되는지 테스트")
    void testDealerWinOrLoseByPlayer() {
        Dealer dealer = new Dealer();
        Player player = new Player(new Name("미립"));

        dealer.addCard(Card.valueOf(Pattern.HEART, Number.THREE));   // 3
        player.addCard(Card.valueOf(Pattern.DIAMOND, Number.TEN));   // 10
        Result playerWin = dealer.judge(player);

        dealer.addCard(Card.valueOf(Pattern.HEART, Number.TEN));     // 13
        player.addCard(Card.valueOf(Pattern.DIAMOND, Number.TWO));   // 12
        Result playerLose = dealer.judge(player);

        player.addCard(Card.valueOf(Pattern.DIAMOND, Number.ACE));   // 23->13
        Result playerDraw = dealer.judge(player);

        assertThat(playerWin.reverse()).isEqualTo(Result.LOSE);
        assertThat(playerLose.reverse()).isEqualTo(Result.WIN);
        assertThat(playerDraw.reverse()).isEqualTo(Result.DRAW);
    }
}
