package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import blackjack.domain.player.BettingMoney;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.Name;
import blackjack.domain.result.GameResult;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BlackjackTest {

    @Test
    void 블랙잭_상태_테스트() {
        State blackjack = StateFactory.drawFirstCards(Cards.of(
                Card.of(Denomination.KING, Shape.CLUBS),
                Card.of(Denomination.ACE, Shape.CLUBS)
        ));

        assertThat(blackjack).isInstanceOf(Blackjack.class);
    }

    @Test
    void 게이머_블랙잭이면서_딜러_블랙잭_아닐때() {
        Cards cards1 = Cards.of(
                Card.of(Denomination.KING, Shape.CLUBS),
                Card.of(Denomination.ACE, Shape.CLUBS)
        );
        Gamer gamer = new Gamer(new Name("게이머"), cards1, new BettingMoney(10000));

        Cards cards2 = Cards.of(
                Card.of(Denomination.KING, Shape.CLUBS),
                Card.of(Denomination.TWO, Shape.CLUBS)
        );
        Dealer dealer = new Dealer(cards2);

        GameResult gameResult = GameResult.of(dealer, Arrays.asList(gamer));

        assertThat(gameResult.findProfitByPlayer(gamer)).isEqualTo(15000);
        assertThat(gameResult.findProfitByPlayer(dealer)).isEqualTo(-15000);
    }

}