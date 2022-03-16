package blackjack.model.player;

import static blackjack.model.card.Suit.CLOVER;
import static blackjack.model.card.Suit.HEART;
import static blackjack.model.card.Suit.SPADE;
import static blackjack.model.player.matcher.ResultStatus.BLACKJACK;
import static blackjack.model.player.matcher.ResultStatus.LOSS;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import blackjack.model.player.matcher.Record;
import blackjack.model.player.matcher.Result;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GamersTest {

    private static final Card ACE = new Card(Rank.ACE, SPADE);
    private static final Card TWO = new Card(Rank.TWO, SPADE);
    private static final Card THREE = new Card(Rank.THREE, CLOVER);
    private static final Card JACK = new Card(Rank.JACK, HEART);
    private static final Card QUEEN = new Card(Rank.QUEEN, SPADE);
    private static final Money MONEY = new Money(new BigDecimal("1000"));


    @Test
    @DisplayName("여러 플레이어 결과 수집")
    void blackjackWithTwoPlayer() {
        Dealer dealer = new Dealer(THREE, QUEEN);

        String pobi = "pobi";
        String crong = "crong";

        Gamer player1 = new Gamer(pobi, MONEY, TWO, THREE);
        Gamer player2 = new Gamer(crong, MONEY, ACE, JACK);
        Gamers gamers = new Gamers(List.of(player1, player2));

        List<Record> records = gamers.match(dealer);

        assertThat(records).hasSize(2);
        assertThat(records).contains(new Record(pobi, new Result(LOSS, MONEY)), new Record(crong, new Result(BLACKJACK, MONEY)));
    }

    @Test
    @DisplayName("동일한 이름의 여러 플레이어 결과 수집")
    void blackjackWithSameNamePlayers() {
        Dealer dealer = new Dealer(JACK, QUEEN);

        String pobi1 = "pobi";
        String pobi2 = "pobi";

        Gamer player1 = new Gamer(pobi1, MONEY, TWO, THREE);
        Gamer player2 = new Gamer(pobi2, MONEY, ACE, JACK);
        Gamers gamers = new Gamers(List.of(player1, player2));

        List<Record> records = gamers.match(dealer);

        assertThat(records).hasSize(2);
        assertThat(records).contains(new Record(pobi1, new Result(LOSS, MONEY)), new Record(pobi2, new Result(BLACKJACK, MONEY)));
    }
}
