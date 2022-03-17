package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Symbol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ResultTest {
    private Dealer dealer;
    private Player player;

    @BeforeEach
    void initDealer(){
        dealer = new Dealer();
        player = new Player("name", 1000);
    }

    @Test
    @DisplayName("플레이어가 블랙잭이고, 딜러가 블랙잭 아닌경우는 BlackJack을 return한다.")
    void judgeBlackJackResult() {
        player.hit(Card.of(Symbol.SPADE, Denomination.TEN));
        player.hit(Card.of(Symbol.CLOVER, Denomination.TEN));
        player.hit(Card.of(Symbol.SPADE, Denomination.ACE));

        dealer.hit(Card.of(Symbol.SPADE, Denomination.EIGHT));
        dealer.hit(Card.of(Symbol.HEART, Denomination.EIGHT));

        assertThat(Result.judgeResult(player,dealer)).isEqualTo(Result.BLACKJACK);
    }


    @Test
    @DisplayName("둘다 blakJack인 경우 draw를 return한다.")
    void judgeBothBlackJacksResult() {
        player.hit(Card.of(Symbol.SPADE, Denomination.TEN));
        player.hit(Card.of(Symbol.CLOVER, Denomination.TEN));
        player.hit(Card.of(Symbol.SPADE, Denomination.ACE));

        dealer.hit(Card.of(Symbol.SPADE, Denomination.QUEEN));
        dealer.hit(Card.of(Symbol.CLOVER, Denomination.FIVE));
        dealer.hit(Card.of(Symbol.HEART, Denomination.SIX));

        assertThat(Result.judgeResult(player,dealer)).isEqualTo(Result.DRAW);
    }

    @Test
    @DisplayName("유저가 burst인 경우 lose를 return한다.")
    void judgePlayerBurstResult() {
        player.hit(Card.of(Symbol.SPADE, Denomination.TEN));
        player.hit(Card.of(Symbol.SPADE, Denomination.JACK));
        player.hit(Card.of(Symbol.CLOVER, Denomination.QUEEN));

        dealer.hit(Card.of(Symbol.SPADE, Denomination.QUEEN));
        dealer.hit(Card.of(Symbol.CLOVER, Denomination.JACK));

        assertThat(Result.judgeResult(player,dealer)).isEqualTo(Result.LOSE);
    }

    @Test
    @DisplayName("딜러가 burst인 경우 win을 return한다.")
    void judgeDealerBurstResult() {
        dealer.hit(Card.of(Symbol.SPADE, Denomination.TEN));
        dealer.hit(Card.of(Symbol.SPADE, Denomination.SIX));
        dealer.hit(Card.of(Symbol.CLOVER, Denomination.QUEEN));

        player.hit(Card.of(Symbol.SPADE, Denomination.QUEEN));
        player.hit(Card.of(Symbol.CLOVER, Denomination.JACK));

        assertThat(Result.judgeResult(player,dealer)).isEqualTo(Result.WIN);
    }

}