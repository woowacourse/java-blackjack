package blackjack.domain.participant;

import static blackjack.fixture.CardRepository.CLOVER4;
import static blackjack.fixture.CardRepository.CLOVER5;
import static blackjack.fixture.CardRepository.CLOVER6;
import static blackjack.fixture.CardRepository.CLOVER7;
import static blackjack.fixture.CardRepository.CLOVER8;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void setUp() {
        CardBundle cardBundle = CardBundle.of(CLOVER4, CLOVER5);
        dealer = Dealer.of(cardBundle);
    }

    @DisplayName("카드를 전달받아 cardBundle에 추가할 수 있다.")
    @Test
    void receiveCard() {
        dealer.receiveCard(CLOVER6);

        Set<Card> actual = dealer.getCards();

        assertThat(actual).containsExactlyInAnyOrder(CLOVER4, CLOVER5, CLOVER6);
    }

    @DisplayName("점수가 16을 넘지 않으면 true를 반환한다.")
    @Test
    void canReceive_returnTrueOnLessThan16() {
        dealer.receiveCard(CLOVER6);
        boolean actual = dealer.canReceive();

        assertThat(actual).isTrue();
    }

    @DisplayName("점수가 16이면 true를 반환한다.")
    @Test
    void canReceive_returnTrueOn16() {
        dealer.receiveCard(CLOVER7);

        boolean actual = dealer.canReceive();

        assertThat(actual).isTrue();
    }

    @DisplayName("점수가 17 이상이면 false를 반환한다.")
    @Test
    void canReceive_returnFalseOnGreaterThan16() {
        dealer.receiveCard(CLOVER8);

        boolean actual = dealer.canReceive();

        assertThat(actual).isFalse();
    }
}
