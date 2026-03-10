package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Denomination;
import blackjack.domain.Hand;
import blackjack.domain.Participant;
import blackjack.domain.Participants;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.Status;
import blackjack.domain.Suit;
import blackjack.domain.Trump;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    @Test
    @DisplayName("플레이어에게 카드 전달 테스트")
    void 플레이어에게_카드_전달_테스트() {
        Trump trump = new Trump();
        Player player = new Player(new Hand(new ArrayList<>()), Status.HIT, "pobi");
        Dealer dealer = new Dealer(new Hand(new ArrayList<>()), Status.HIT, trump);
        int expected = 1;

        dealer.giveCardTo(player);

        assertThat(player).extracting("hand")
            .extracting("cards")
            .asInstanceOf(InstanceOfAssertFactories.LIST)
            .hasSize(expected);
    }

    @Test
    @DisplayName("딜러 자신에게 카드 전달 테스트")
    void 딜러_자신에게_카드_전달_테스트() {
        Trump trump = new Trump();
        Dealer dealer = new Dealer(new Hand(new ArrayList<>()), Status.HIT, trump);
        int expected = 1;

        dealer.giveCardMyself();

        assertThat(dealer).extracting("hand")
            .extracting("cards")
            .asInstanceOf(InstanceOfAssertFactories.LIST)
            .hasSize(expected);
    }

    @Test
    @DisplayName("딜러 피치 테스트")
    void 딜러_피치_테스트() {
        Trump trump = new Trump();
        Dealer dealer = new Dealer(new Hand(new ArrayList<>()), Status.HIT, trump);
        List<Player> players = List.of(
            new Player(new Hand(new ArrayList<>()), Status.HIT, "pobi"),
            new Player(new Hand(new ArrayList<>()), Status.HIT, "jason")
        );
        Participants participants = new Participants(new Players(players), dealer);
        int expectedTrumpSize = 13 * 4 - (players.size() + 1) * 2;
        int participantHandSize = 2;

        dealer.pitch(players);

        assertTrumpDeckSize(trump, expectedTrumpSize);
        participants.all().forEach(participant ->
            assertParticipantHandSize(participant, participantHandSize));
    }

    private void assertTrumpDeckSize(Trump trump, int expectedTrumpSize) {
        assertThat(trump).extracting("deck")
            .asInstanceOf(InstanceOfAssertFactories.LIST)
            .hasSize(expectedTrumpSize);
    }

    private void assertParticipantHandSize(Participant participant, int participantHandSize) {
        assertThat(participant).extracting("hand")
            .extracting("cards")
            .asInstanceOf(InstanceOfAssertFactories.LIST)
            .hasSize(participantHandSize);
    }

    @Test
    @DisplayName("딜러 히트 여부 결정 테스트: 카드 합계가 16 이하인 경우")
    void 딜러_히트_여부_결정_테스트_카드_합계가_16_이하인_경우() {
        Hand hand = new Hand(List.of(
            new Card(Suit.DIAMOND, Denomination.TEN),
            new Card(Suit.SPADE, Denomination.SIX)));
        Trump trump = new Trump();
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
        Trump trump = new Trump();
        Dealer dealer = new Dealer(hand, Status.HIT, trump);
        Status expected = Status.STAY;

        dealer.decideHit();

        assertThat(dealer).extracting("status")
            .isEqualTo(expected);

    }
}
