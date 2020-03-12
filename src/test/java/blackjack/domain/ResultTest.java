package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.user.DefaultDealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {

    @Test
    void of() {
        Player dealer = DefaultDealer.create();
        Players players = Players.of("그니,무늬");

        assertThat(Result.of(dealer, players)).isNotNull();
    }

    @Test
    void getDealerResult() {
        Player dealer = DefaultDealer.create();
        dealer.giveCards(Card.of(Symbol.ACE, Type.SPADE));

        Players players = Players.of("그니, 무늬, 포비");
        players.giveCards(0, Card.of(Symbol.TEN, Type.DIAMOND));
        players.giveCards(1, Card.of(Symbol.FIVE, Type.CLUB),
                Card.of(Symbol.SEVEN, Type.HEART));
        players.giveCards(2, Card.of(Symbol.TWO, Type.DIAMOND));

        Result result = Result.of(dealer, players);

        assertThat(result.getDealerResult()).isEqualTo("2승 1패");
    }
}