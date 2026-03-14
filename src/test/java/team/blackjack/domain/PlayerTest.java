package team.blackjack.domain;

import java.util.List;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {
    @Test
    void hit하면_플레이어_핸드에_카드가_추가된다() {
        Player player = new Player("pobi", 10000);
        Card card = Card.of(Suit.HEARTS, Rank.ACE);

        player.hit(card);

        assertThat(player.getHands().getFirst().getCards())
                .containsExactly(card);
    }

    @Test
    void 킹과_에이스를_각각_1장씩_받은_플레이어의_점수는_21로_정상_계산된다() {
        Player player = new Player("pobi", 10000);
        List<Card> cards = List.of(
                Card.of(Suit.HEARTS, Rank.ACE),
                Card.of(Suit.CLUBS, Rank.KING)
        );

        for (Card card : cards) {
            player.hit(card);
        }

        assertThat(player.getScore()).isEqualTo(21);
    }

    @Test
    void 사용자가_카드를_받지않은_경우_점수는_0으로_정상_계산된다() {
        Player player = new Player("pobi", 10000);
        assertThat(player.getScore()).isEqualTo(0);
    }

    /**
     * 배팅 수익 계산 테스트
     */
    @Test
    void 플레이어가_패배하는_경우_수익_금액은_마이너스_원금이_된다() {
        Player player = new Player("pobi", 10000);

        int payout = player.getPayout(Result.LOSE);

        assertThat(payout).isEqualTo(-10000);
    }

    @Test
    void 플레이어가_승리하는_경우_수익_금액은_배팅금액이_된다() {
        Player player = new Player("pobi", 10000);

        int payout = player.getPayout(Result.WIN);

        assertThat(payout).isEqualTo(10000);
    }

    @Test
    void 플레이어가_블랙잭으로_승리한_경우_수익_금액은_배팅금액의_1_5배가_된다() {
        Player player = new Player("pobi", 10000);

        int payout = player.getPayout(Result.BLACKJACK);

        assertThat(payout).isEqualTo(15000);
    }

    /**
     * 첨언. 둘다 블랙잭으로 무승부인 경우도 해당 케이스에 포함된다. 둘 다 블랙잭으로 Result.DRAW 판별을 하는 책임은 BlackjackRule이 갖는다. 이에 따라 해당 테스트도
     * BlackjackRule 테스트에 작성한다.
     */
    @Test
    void 플레이어가_무승부인_경우_수익_금액은_없다() {
        Player player = new Player("pobi", 10000);

        int payout = player.getPayout(Result.DRAW);

        assertThat(payout).isEqualTo(0);
    }
}
