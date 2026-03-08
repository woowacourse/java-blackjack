package domain.constant;

import domain.Card;
import domain.Dealer;
import domain.Hand;
import domain.Player;
import org.junit.jupiter.api.Test;

import static domain.constant.Rank.*;
import static domain.constant.Suit.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ResultTest {

    @Test
    void 플레이어가_버스트면_결과는_패다() {
        Dealer dealer = new Dealer();
        Player player = new Player("pobi", new Hand());
        player.addCard(new Card(TEN, SPADE));
        player.addCard(new Card(TEN, HEART));
        player.addCard(new Card(FIVE, HEART));

        Result result = Result.of(dealer, player);

        assertThat(result).isEqualTo(Result.LOSE);
    }

    @Test
    void 딜러만_버스트면_결과는_승이다() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(TEN, SPADE));
        dealer.addCard(new Card(TEN, HEART));
        dealer.addCard(new Card(FIVE, HEART));
        Player player = new Player("pobi", new Hand());

        Result result = Result.of(dealer, player);

        assertThat(result).isEqualTo(Result.WIN);
    }

    @Test
    void 플레이어의_점수가_높으면_결과는_승이다() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(TEN, HEART));
        dealer.addCard(new Card(FIVE, HEART));
        Player player = new Player("pobi", new Hand());
        player.addCard(new Card(ACE, SPADE));
        player.addCard(new Card(KING, HEART));

        Result result = Result.of(dealer, player);

        assertThat(result).isEqualTo(Result.WIN);
    }
}