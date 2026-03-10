package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class BlackJackGameTest {

    @Test
    void 게임이_시작되면_참여자_모두_카드를_2장씩_받는다() {
        // arrange
        Players players = new Players(List.of("이산", "모카"));
        Dealer dealer = new Dealer();
        Deck deck = new Deck();
        BlackJackGame blackJackGame = new BlackJackGame(players, dealer, deck);

        // act
        blackJackGame.initDraw();

        // assert
        assertThat(deck.getCount()).isEqualTo(46);
    }

}
