package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class PlayerTest {
    private Player playerWith(TrumpCard... cards) {
        Player player = Player.of(Name.of("한다"));
        for (TrumpCard card : cards) {
            player.hit(card);
        }
        return player;
    }

    private TrumpCard card(Suit suit, Rank rank) {
        return TrumpCard.of(suit, rank);
    }

    @Test
    void 플레이어는_이름을_가진다(){
        Player player = Player.of(Name.of("한다"));
        assertThat(player.name()).isEqualTo("한다");
    }

    @Test
    void 버스트가_아니면_카드를_더_받을_수_있다() {
        Player player = playerWith(
                card(Suit.SPADE, Rank.KING)
        );
        assertThat(player.canHit()).isTrue();
    }

    @Test
    void 버스트이면_카드를_받을_수_없다() {
        Player player = playerWith(
                card(Suit.SPADE, Rank.KING),
                card(Suit.HEART, Rank.QUEEN),
                card(Suit.DIAMOND, Rank.FIVE)
        );
        assertThat(player.canHit()).isFalse();
    }
}
