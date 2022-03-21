package blackjack.domain.participant;

import static blackjack.domain.fixture.CardRepository.CLOVER10;
import static blackjack.domain.fixture.CardRepository.CLOVER4;
import static blackjack.domain.fixture.CardRepository.CLOVER5;
import static blackjack.domain.fixture.CardRepository.CLOVER6;
import static blackjack.domain.fixture.CardRepository.CLOVER7;
import static blackjack.domain.fixture.CardRepository.CLOVER8;
import static blackjack.domain.fixture.CardRepository.CLOVER_ACE;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void setUp() {
        Hand hand = Hand.of(CLOVER4, CLOVER5);
        dealer = Dealer.of(hand);
    }

    @DisplayName("Dealer 인스턴스가 생성된다.")
    @Test
    void of() {
        assertThat(dealer).isNotNull();
    }

    @DisplayName("Card 를 전달받아 Hand 에 추가할 수 있다.")
    @Test
    void receiveCard() {
        dealer.receiveCard(CLOVER6);

        List<Card> actual = dealer.getHand().getCards();

        assertThat(actual).containsExactlyInAnyOrder(CLOVER4, CLOVER5, CLOVER6);
    }

    @DisplayName("Score 가 16을 넘지 않으면 true 를 반환한다.")
    @Test
    void canReceive_returnTrueOnLessThan16() {
        dealer.receiveCard(CLOVER6);
        boolean actual = dealer.canReceive();

        assertThat(actual).isTrue();
    }

    @DisplayName("Score 가 16이면 true 를 반환한다.")
    @Test
    void canReceive_returnTrueOn16() {
        dealer.receiveCard(CLOVER7);

        boolean actual = dealer.canReceive();

        assertThat(actual).isTrue();
    }

    @DisplayName("Score 가 17 이상이면 false 를 반환한다.")
    @Test
    void canReceive_returnFalseOnGreaterThan16() {
        dealer.receiveCard(CLOVER8);

        boolean actual = dealer.canReceive();

        assertThat(actual).isFalse();
    }

    @DisplayName("isBlackjack 은 딜러가 받은 첫 두장의 카드의 합이 21일 경우 true 를 반환한다.")
    @Test
    void isBlackjack_returnsTrueOnBlackjack() {
        // given
        Dealer dealer = Dealer.of(Hand.of(CLOVER_ACE, CLOVER10));

        // when
        boolean actual = dealer.isBlackjack();

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("isBlackjack 은 딜러의 점수가 21이더라도 받은 첫 두장의 카드의 합이 21이 아닐 경우 false 를 반환한다.")
    @Test
    void isBlackjack_returnsFalseIfTotalScoreIs21ButNotBlackjack() {
        // given
        Dealer dealer = Dealer.of(Hand.of(CLOVER_ACE, CLOVER4));
        dealer.receiveCard(CLOVER6);

        // when
        boolean actual = dealer.isBlackjack();

        // then
        assertThat(actual).isFalse();
    }
}
