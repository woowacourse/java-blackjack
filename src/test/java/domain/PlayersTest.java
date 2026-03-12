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
    void 초기_블랙잭인_플레이어는_naturalBlackJack_상태가_true가_된다() {
        Players players = new Players();
        Player blackJackPlayer = new Player("pobi", 1000);
        Player normalPlayer = new Player("jason", 1000);

        blackJackPlayer.receiveCard(new Card(Rank.ACE, Suit.SPADE));
        blackJackPlayer.receiveCard(new Card(Rank.KING, Suit.HEART));

        normalPlayer.receiveCard(new Card(Rank.TWO, Suit.CLUB));
        normalPlayer.receiveCard(new Card(Rank.THREE, Suit.DIAMOND));

        players.add(blackJackPlayer);
        players.add(normalPlayer);

        players.updateNaturalBlackJackStatus();

        assertThat(blackJackPlayer.isNaturalBlackJack()).isTrue();
        assertThat(normalPlayer.isNaturalBlackJack()).isFalse();
    }
}