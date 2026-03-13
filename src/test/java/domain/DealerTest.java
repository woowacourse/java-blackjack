package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Hand;
import blackjack.domain.participant.Player;
import blackjack.domain.judgement.Status;
import blackjack.domain.card.Suit;
import blackjack.domain.card.Trump;
import blackjack.strategy.ShuffleStrategy;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    private Trump trump;

    @BeforeEach
    void setUp() {
        ShuffleStrategy strategy = new NoShuffleStrategy();
        trump = new Trump(strategy);
    }

    @Test
    @DisplayName("딜러 pitch 테스트")
    void 딜러_피치_테스트() {
        Dealer dealer = new Dealer(new Hand(new ArrayList<>()), Status.HIT, trump);
        List<Player> players = List.of(
            new Player(new Hand(new ArrayList<>()), Status.HIT, "pobi"),
            new Player(new Hand(new ArrayList<>()), Status.HIT, "jason")
        );
        int expected = 13 * 4 - (players.size() + 1) * 2;

        dealer.pitch(players);

        assertThat(trump).extracting("deck")
            .asInstanceOf(InstanceOfAssertFactories.LIST)
            .hasSize(expected);
    }

    @Test
    @DisplayName("딜러 히트 여부 결정 테스트: 카드 합계가 16 이하인 경우")
    void 딜러_히트_여부_결정_테스트_카드_합계가_16_이하인_경우() {
        Hand hand = new Hand(List.of(
            new Card(Suit.DIAMOND, Denomination.TEN),
            new Card(Suit.SPADE, Denomination.SIX)));
        Dealer dealer = new Dealer(hand, Status.HIT, trump);
        Status expected = Status.HIT;

        dealer.decideHit();

        assertThat(dealer).extracting("status")
            .isEqualTo(expected);

    }


    @Test
    @DisplayName("딜러 히트 여부 결정 테스트: 카드 합계가 16 초과인 경우")
    void 딜러_히트_여부_결정_테스트_카드_합계가_16_초과인_경우() {
        Hand hand = new Hand(List.of(
            new Card(Suit.DIAMOND, Denomination.TEN),
            new Card(Suit.SPADE, Denomination.SEVEN)));
        Dealer dealer = new Dealer(hand, Status.HIT, trump);

        dealer.decideHit();

        assertThat(dealer.isHit()).isFalse();
        assertThat(dealer.isBurst()).isFalse();
    }
}
