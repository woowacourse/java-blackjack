package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class PlayerMoneyTest {

    @Test
    @DisplayName("")
    void betMoney() {
        Player blackJack = new Player(new Name("bj"));
        blackJack.addCard(Card.of(Suit.CLOVER, Denomination.ACE));
        blackJack.addCard(Card.of(Suit.CLOVER, Denomination.TEN));

        Player win = new Player(new Name("win"));
        win.addCard(Card.of(Suit.CLOVER, Denomination.ACE));
        win.addCard(Card.of(Suit.CLOVER, Denomination.TEN));
        win.addCard(Card.of(Suit.CLOVER, Denomination.TEN));

        Player draw = new Player(new Name("draw"));
        draw.addCard(Card.of(Suit.CLOVER, Denomination.JACK));
        draw.addCard(Card.of(Suit.CLOVER, Denomination.TEN));

        Player lose = new Player(new Name("lose"));
        lose.addCard(Card.of(Suit.CLOVER, Denomination.EIGHT));
        lose.addCard(Card.of(Suit.CLOVER, Denomination.NINE));

        Dealer dealer = new Dealer();
        dealer.addCard(Card.of(Suit.CLOVER, Denomination.JACK));
        dealer.addCard(Card.of(Suit.CLOVER, Denomination.TEN));

        PlayerMoney playerMoney = new PlayerMoney();
        playerMoney.addPlayerMoney(blackJack, new Money(10000));
        playerMoney.addPlayerMoney(win, new Money(10000));
        playerMoney.addPlayerMoney(draw, new Money(10000));
        playerMoney.addPlayerMoney(lose, new Money(10000));

        Map<Player, Money> result = playerMoney.calculateYieldAllPlayer(dealer.getHand());

        Assertions.assertThat(result.get(blackJack)).isEqualTo(new Money(15000));
        Assertions.assertThat(result.get(win)).isEqualTo(new Money(10000));
        Assertions.assertThat(result.get(draw)).isEqualTo(new Money(0));
        Assertions.assertThat(result.get(lose)).isEqualTo(new Money(-10000));
    }
}
