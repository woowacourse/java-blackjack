package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.result.Result;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Players;
import blackjack.domain.user.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {

    @Test
    void of() {
        User dealer = Dealer.create();
        Players players = Players.of("그니,무늬");

        assertThat(Result.of(dealer, players)).isNotNull();
    }

    @Test
    void getDealerResult() {
        User dealer = Dealer.create();
        dealer.giveCards(new Card(Symbol.ACE, Type.SPADE));

        Players players = Players.of("그니, 무늬, 포비");
        players.giveCards(0, new Card(Symbol.TEN, Type.DIAMOND));
        players.giveCards(1, new Card(Symbol.FIVE, Type.CLUB),
                new Card(Symbol.SEVEN, Type.HEART));
        players.giveCards(2, new Card(Symbol.TWO, Type.DIAMOND));

        Result result = Result.of(dealer, players);

        assertThat(result.getDealerResult()).isEqualTo("2승 1패");
    }
}