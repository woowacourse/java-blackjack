package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Symbol;
import blackjack.domain.card.Type;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.domain.user.User;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

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
        dealer.append(new Card(Symbol.ACE, Type.SPADE));

        Player player1 = Player.of("그니");
        player1.append(new Card(Symbol.TEN, Type.DIAMOND));

        Player player2 = Player.of("무늬");
        player2.append(new Card(Symbol.FIVE, Type.CLUB));
        player2.append(new Card(Symbol.SEVEN, Type.HEART));

        Player player3 = Player.of("포비");
        player3.append(new Card(Symbol.TWO, Type.CLUB));

        List<User> players = Arrays.asList(player1, player2, player3);
        Result result = Result.of(dealer, players);

        assertThat(result.getDealerResult()).isEqualTo("2승 1패");
    }
}