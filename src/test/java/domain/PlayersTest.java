package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.constant.Result;
import domain.participant.Player;
import domain.participant.Players;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayersTest {

    @Test
    void 플레이어를_등록한다() {
        Players players = new Players();
        players.add(new Player("pobi", 1000));
        players.add(new Player("abc", 1000));

        List<Player> records = players.getPlayers();

        assertThat(records).anyMatch(player -> player.getName().equals("abc"));
    }

    @Test
    void 블랙잭인_플레이어는_Result를_플레이어로_변경() {
        Players players = new Players();
        Player blackJackPlayer = new Player("pobi", 1000);
        Player normalPlayer = new Player("jason", 1000);

        blackJackPlayer.receiveCard(new Card(Rank.ACE, Suit.SPADE));
        blackJackPlayer.receiveCard(new Card(Rank.KING, Suit.HEART));

        normalPlayer.receiveCard(new Card(Rank.TWO, Suit.CLUB));
        normalPlayer.receiveCard(new Card(Rank.THREE, Suit.DIAMOND));

        players.add(blackJackPlayer);
        players.add(normalPlayer);

        players.renewedWithBlackJack();

        assertThat(blackJackPlayer.getResult()).isEqualTo(Result.BLACKJACK);
        assertThat(normalPlayer.getResult()).isNotEqualTo(Result.BLACKJACK);
    }
}
