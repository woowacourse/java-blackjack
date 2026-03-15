package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.Player;
import domain.participant.Players;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayersTest {

    @Test
    void 플레이어를_등록한다() {
        Players players = Players.of(List.of(
                new Player("pobi", 1000),
                new Player("abc", 1000)
        ));

        List<Player> records = players.getPlayers();

        assertThat(records).hasSize(2);
        assertThat(records).extracting(Player::getName)
                .containsExactly("pobi", "abc");
    }

    @Test
    void 초기_블랙잭인_플레이어는_naturalBlackJack_상태가_true가_된다() {
        Player blackJackPlayer = new Player("pobi", 1000);
        Player normalPlayer = new Player("jason", 1000);

        blackJackPlayer.receiveCard(new Card(Rank.ACE, Suit.SPADE));
        blackJackPlayer.receiveCard(new Card(Rank.KING, Suit.HEART));

        normalPlayer.receiveCard(new Card(Rank.TWO, Suit.CLUB));
        normalPlayer.receiveCard(new Card(Rank.THREE, Suit.DIAMOND));

        Players players = Players.of(List.of(blackJackPlayer, normalPlayer));

        players.updateNaturalBlackJackStatus();

        assertThat(blackJackPlayer.isNaturalBlackJack()).isTrue();
        assertThat(normalPlayer.isNaturalBlackJack()).isFalse();
    }

    @Test
    void 초기_블랙잭이_아닌_플레이어들만_반환한다() {
        Player blackJackPlayer = new Player("pobi", 1000);
        Player normalPlayer = new Player("jason", 1000);

        blackJackPlayer.receiveCard(new Card(Rank.ACE, Suit.SPADE));
        blackJackPlayer.receiveCard(new Card(Rank.KING, Suit.HEART));

        normalPlayer.receiveCard(new Card(Rank.TWO, Suit.CLUB));
        normalPlayer.receiveCard(new Card(Rank.THREE, Suit.DIAMOND));

        Players players = Players.of(List.of(blackJackPlayer, normalPlayer));

        players.updateNaturalBlackJackStatus();

        List<Player> result = players.getNonNaturalBlackJackPlayers();

        assertThat(result).hasSize(1);
        assertThat(result.getFirst().getName()).isEqualTo("jason");
    }
}