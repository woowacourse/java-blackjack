package blackjack.domain.participant;

import static blackjack.fixture.CardRepository.CLOVER10;
import static blackjack.fixture.CardRepository.CLOVER2;
import static blackjack.fixture.CardRepository.CLOVER3;
import static blackjack.fixture.CardRepository.CLOVER4;
import static blackjack.fixture.CardRepository.CLOVER_KING;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import blackjack.domain.hand.CardHand;
import blackjack.domain.hand.OneCard;
import blackjack.fixture.CardSupplierStub;
import blackjack.strategy.CardsViewStrategy;
import blackjack.strategy.HitOrStayStrategy;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class DealerTest {

    private static final HitOrStayStrategy HIT_CHOICE = () -> true;
    private static final CardsViewStrategy VIEW_STRATEGY = (p) -> {
    };

    private CardHand cardHand;

    @BeforeEach
    void setUp() {
        cardHand = new OneCard(CLOVER4).hit(CLOVER_KING);
    }

    @DisplayName("딜러 drawAll 메서드 테스트")
    @Nested
    class DrawAllCardsTest {

        @DisplayName("계속 드로우하도록 하면 16 이하일 때 계속 드로우를 하고 21을 넘어가면 버스트된다.")
        @Test
        void drawAll_bust() {
            Participant dealer = new Dealer(cardHand);

            dealer.drawAll(HIT_CHOICE, VIEW_STRATEGY, CardSupplierStub.of(CLOVER2, CLOVER10));

            assertThat(extractCards(dealer))
                    .containsExactly(CLOVER4, CLOVER_KING, CLOVER2, CLOVER10);
        }

        @DisplayName("계속 드로우하도록 해도 17~21이 되었을 때 카드 뽑기를 중단한다.")
        @Test
        void drawAll_stayOn17() {
            Participant dealer = new Dealer(cardHand);

            dealer.drawAll(HIT_CHOICE, VIEW_STRATEGY, () -> CLOVER3);

            assertThat(extractCards(dealer)).containsExactly(CLOVER4, CLOVER_KING, CLOVER3);
        }
    }

    @DisplayName("openInitialCards 메서드는 딜러 초기에 받은 두 장 카드만을 반환한다.")
    @Test
    void openInitialCards() {
        Participant dealer = new Dealer(cardHand);
        dealer.drawAll(HIT_CHOICE, VIEW_STRATEGY, () -> CLOVER10);

        List<Card> actual = dealer.openInitialCards();

        assertThat(actual).containsExactly(CLOVER4);
    }

    @DisplayName("카드 패의 구성이 동일한 딜러는 서로 동일하다고 간주된다.")
    @Test
    void equals_trueOnSameCardsComposition() {
        Dealer dealer1 = new Dealer(generateCardHand());
        Dealer dealer2 = new Dealer(generateCardHand());

        assertThat(dealer1).isEqualTo(dealer2);
    }

    @DisplayName("카드 패의 구성이 동일한 딜러의 해쉬 값은 서로 동일하다.")
    @Test
    void hashCode_trueOnCardHand() {
        Dealer dealer1 = new Dealer(generateCardHand());
        Dealer dealer2 = new Dealer(generateCardHand());

        assertThat(dealer1.hashCode()).isEqualTo(dealer2.hashCode());
    }

    private List<Card> extractCards(Participant participant) {
        CardBundle cardBundle = participant.getHand().getCardBundle();
        return cardBundle.getCards();
    }

    private CardHand generateCardHand() {
        return new OneCard(CLOVER4).hit(CLOVER_KING);
    }
}

