package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
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
        Player newPlayer = player.receiveCard(spaceAce);
        assertThat(newPlayer.countCards()).isEqualTo(1);
    }

    @Test
    void 플레이어가_여러_카드를_받는다() {
        TrumpCard spaceAce = TrumpCard.of(Suit.SPADE, Rank.ACE);
        TrumpCard heartKing = TrumpCard.of(Suit.HEART, Rank.KING);

        Player newPlayer = player.receiveCard(spaceAce);
        newPlayer = newPlayer.receiveCard(heartKing);
        assertThat(newPlayer.countCards()).isEqualTo(2);
    }

    @Test
    void 플레이어의_현재_점수를_계산한다() {
        TrumpCard spaceAce = TrumpCard.of(Suit.SPADE, Rank.ACE);
        TrumpCard heartKing = TrumpCard.of(Suit.HEART, Rank.KING);

        Player newPlayer = player.receiveCard(spaceAce);
        newPlayer = newPlayer.receiveCard(heartKing);

        assertThat(newPlayer.score()).isEqualTo(21);
    }

    @Test
    void 플레이어가_여러_카드를_한번에_받는다() {
        List<TrumpCard> cards = new ArrayList<>();
        cards.add(TrumpCard.of(Suit.SPADE, Rank.ACE));
        cards.add(TrumpCard.of(Suit.HEART, Rank.KING));

        Player newPlayer = player.receiveCards(cards);
        assertThat(newPlayer.countCards()).isEqualTo(2);
    }

    @Test
    void 플레이어가_21이하면_카드를_더_받을_수_있다() {
        TrumpCard spadeKing = TrumpCard.of(Suit.SPADE, Rank.KING);
        Player newPlayer = player.receiveCard(spadeKing);

        assertThat(newPlayer.canHit()).isTrue();
    }

    @Test
    void 플레이어가_21을_초과하면_카드를_받을_수_없다() {
        TrumpCard spadeKing = TrumpCard.of(Suit.SPADE, Rank.KING);
        TrumpCard heartQueen = TrumpCard.of(Suit.HEART, Rank.QUEEN);
        TrumpCard diamondFive = TrumpCard.of(Suit.DIAMOND, Rank.FIVE);

        Player newPlayer = player.receiveCard(spadeKing);
        newPlayer = newPlayer.receiveCard(heartQueen);
        newPlayer = newPlayer.receiveCard(diamondFive);

        assertThat(newPlayer.canHit()).isFalse();
    }

    @Test
    void 플레이어의_보유_카드_목록을_반환한다() {
        TrumpCard spadeAce = TrumpCard.of(Suit.SPADE, Rank.ACE);
        TrumpCard heartKing = TrumpCard.of(Suit.HEART, Rank.KING);

        Player newPlayer = player.receiveCard(spadeAce);
        newPlayer = newPlayer.receiveCard(heartKing);

        assertThat(newPlayer.getCards()).hasSize(2);
        assertThat(newPlayer.getCards()).containsExactly(spadeAce, heartKing);
    }
}
