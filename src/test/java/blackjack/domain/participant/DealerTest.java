package blackjack.domain.participant;

import static blackjack.fixture.CardBundleGenerator.generateCardBundleOf;
import static blackjack.fixture.CardRepository.CLOVER10;
import static blackjack.fixture.CardRepository.CLOVER4;
import static blackjack.fixture.CardRepository.CLOVER5;
import static blackjack.fixture.CardRepository.CLOVER6;
import static blackjack.fixture.CardRepository.CLOVER7;
import static blackjack.fixture.CardRepository.CLOVER8;
import static blackjack.fixture.CardRepository.CLOVER_ACE;
import static blackjack.fixture.CardRepository.CLOVER_KING;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import blackjack.strategy.CardsViewStrategy;
import blackjack.strategy.HitOrStayChoiceStrategy;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DealerTest {

    private static final HitOrStayChoiceStrategy HIT_CHOICE = () -> true;
    private static final CardsViewStrategy VIEW_STRATEGY = (p) -> {
    };

    @DisplayName("딜러가 drawAllCards 메서드 실행시, 17 이상의 카드 패를 지닐 때까지 카드 한 장을 추가한다.")
    @Test
    void drawAllCards() {
        CardBundle cardBundle = generateCardBundleOf(CLOVER4, CLOVER5);
        Dealer dealer = Dealer.of(cardBundle);

        dealer.drawAllCards(HIT_CHOICE, VIEW_STRATEGY, () -> CLOVER8);

        assertThat(dealer.getCards()).containsExactly(CLOVER4, CLOVER5, CLOVER8);
        assertThat(dealer.canDraw()).isFalse();
    }

    @DisplayName("Dealer 인스턴스에는 CardBundle의 isBust 메서드가 구현되어있다.")
    @Test
    void isBust_implementationTest() {
        CardBundle cardBundle = generateCardBundleOf(CLOVER6, CLOVER10);
        Dealer dealer = Dealer.of(cardBundle);
        dealer.drawAllCards(HIT_CHOICE, VIEW_STRATEGY, () -> CLOVER_KING);

        boolean actual = dealer.isBust();

        assertThat(actual).isTrue();
    }

    @DisplayName("Dealer 인스턴스에는 Participant의 isBlackjack 메서드가 구현되어있다.")
    @Test
    void isBlackjack_implementationTest() {
        CardBundle cardBundle = generateCardBundleOf(CLOVER10, CLOVER_ACE);
        Dealer dealer = Dealer.of(cardBundle);

        boolean actual = dealer.isBlackjack();

        assertThat(actual).isTrue();
    }

    @DisplayName("딜러의 패가 17이상 21이하인 경우 버스트도, 블랙잭도 아니지만, 더 이상 드로우를 하지 않는다.")
    @Test
    void dealerStayTest() {
        CardBundle cardBundle = generateCardBundleOf(CLOVER7, CLOVER10);
        Dealer dealer = Dealer.of(cardBundle);

        assertThat(dealer.canDraw()).isFalse();
        assertThat(dealer.isBlackjack()).isFalse();
        assertThat(dealer.isBust()).isFalse();

        dealer.drawAllCards(HIT_CHOICE, VIEW_STRATEGY, () -> CLOVER_KING);
        assertThat(dealer.getCards()).containsExactly(CLOVER7, CLOVER10);
    }

    @DisplayName("딜러의 getInitialOpenCards 메서드는 초기에 받은 카드 중 한 장이 담긴 컬렉션을 반환한다.")
    @Test
    void getInitialOpenCards() {
        CardBundle cardBundle = generateCardBundleOf(CLOVER4, CLOVER5);
        Dealer dealer = Dealer.of(cardBundle);

        List<Card> actual = dealer.getInitialOpenCards();

        assertThat(actual).containsExactly(CLOVER4);
        assertThat(actual.size()).isEqualTo(1);
    }
}