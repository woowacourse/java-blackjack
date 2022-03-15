package blackjack.model.player;

import static blackjack.model.card.Suit.CLOVER;
import static blackjack.model.card.Suit.HEART;
import static blackjack.model.card.Suit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.blackjack.Record;
import blackjack.model.blackjack.Result;
import blackjack.model.card.Card;
import blackjack.model.card.Rank;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    private static final Card ACE = new Card(Rank.ACE, SPADE);
    private static final Card TWO = new Card(Rank.TWO, SPADE);
    private static final Card THREE = new Card(Rank.THREE, CLOVER);
    private static final Card JACK = new Card(Rank.JACK, HEART);
    private static final Card QUEEN = new Card(Rank.QUEEN, SPADE);

    @Test
    @DisplayName("여러 플레이어 결과 수집")
    void blackjackWithTwoPlayer() {
        Dealer dealer = new Dealer(JACK, QUEEN);

        String pobi = "pobi";
        String crong = "crong";

        Player player1 = new Player(pobi, TWO, THREE);
        Player player2 = new Player(crong, ACE, JACK);
        Players players = new Players(List.of(player1, player2));

        List<Record> records = players.match(dealer);

        assertThat(records).hasSize(2);
        assertThat(records).contains(new Record(pobi, Result.LOSS), new Record(crong, Result.WIN));
    }

    @Test
    @DisplayName("동일한 이름의 여러 플레이어 결과 수집")
    void blackjackWithSameNamePlayers() {
        Dealer dealer = new Dealer(JACK, QUEEN);

        String pobi1 = "pobi";
        String pobi2 = "pobi";

        Player player1 = new Player(pobi1, TWO, THREE);
        Player player2 = new Player(pobi2, ACE, JACK);
        Players players = new Players(List.of(player1, player2));

        List<Record> records = players.match(dealer);

        assertThat(records).hasSize(2);
        assertThat(records).contains(new Record(pobi1, Result.LOSS), new Record(pobi2, Result.WIN));
    }
}
