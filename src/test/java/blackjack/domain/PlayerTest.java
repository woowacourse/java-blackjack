package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        Name newPlayerName = Name.of("한다");
        player = Player.of(newPlayerName);
    }

    @Test
    void 플레이어를_생성한다() {
        assertThat(player).isNotNull();
    }

    @Test
    void 플레이어의_이름을_반환한다() {
        assertThat(player.name()).isEqualTo("한다");
    }


    @Test
    void 플레이어는_초기에_비어있는_핸드를_가진다() {
        assertThat(player.countCards()).isEqualTo(0);
    }

    @Test
    void 플레이어가_카드1장_받는다() {
        TrumpCard spaceAce = TrumpCard.of(Suit.SPADE, Rank.ACE);
        Player newPlayer = player.receive(spaceAce);
        assertThat(newPlayer.countCards()).isEqualTo(1);
    }

    @Test
    void 플레이어가_여러_카드를_받는다() {
        TrumpCard spaceAce = TrumpCard.of(Suit.SPADE, Rank.ACE);
        TrumpCard heartKing = TrumpCard.of(Suit.HEART, Rank.KING);

        Player newPlayer = player.receive(spaceAce);
        newPlayer = newPlayer.receive(heartKing);
        assertThat(newPlayer.countCards()).isEqualTo(2);
    }

    @Test
    void 플레이어의_현재_점수를_계산한다() {
        TrumpCard spaceAce = TrumpCard.of(Suit.SPADE, Rank.ACE);
        TrumpCard heartKing = TrumpCard.of(Suit.HEART, Rank.KING);

        Player newPlayer = player.receive(spaceAce);
        newPlayer = newPlayer.receive(heartKing);

        assertThat(newPlayer.score()).isEqualTo(21);
    }
}
