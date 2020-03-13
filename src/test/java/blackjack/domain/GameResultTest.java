package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.result.GameResult;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Players;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {

    @Test
    void of() {
        Dealer dealer = Dealer.create();
        Players players = Players.of("그니,무늬");

        assertThat(GameResult.of(dealer, players)).isNotNull();
    }

    @Test
    void getDealerResult() {
        Dealer dealer = Dealer.create();
        dealer.giveCards(new Card(Symbol.ACE, Type.SPADE));

        Players players = Players.of("그니, 무늬, 포비");
        players.giveCards(0, new Card(Symbol.TEN, Type.DIAMOND));
        players.giveCards(1, new Card(Symbol.FIVE, Type.CLUB),
                new Card(Symbol.SEVEN, Type.HEART));
        players.giveCards(2, new Card(Symbol.TWO, Type.DIAMOND));

        GameResult gameResult = GameResult.of(dealer, players);

        assertThat(gameResult.getDealerWinCount()).isEqualTo(2);
        assertThat(gameResult.getDealerLoseCount()).isEqualTo(1);
    }
}