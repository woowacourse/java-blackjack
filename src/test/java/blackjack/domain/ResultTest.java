package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultTest {

    @Test
    @DisplayName("승리한 경우를 계산한다.")
    void findWinningResult(){
        Player dealer = new Dealer();
        dealer.receiveCard(new Card(Suit.DIAMOND,Denomination.FIVE));

        Gamer judy = new Gamer("judy");
        judy.receiveCard(new Card(Suit.CLOVER,Denomination.SIX));

        assertThat(Result.findResult(dealer,judy)).isEqualTo(Result.WIN);
    }

}