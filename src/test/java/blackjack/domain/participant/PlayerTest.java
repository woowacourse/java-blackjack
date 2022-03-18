package blackjack.domain.participant;

import static blackjack.fixture.CardBundleGenerator.generateCardBundleOf;
import static blackjack.fixture.CardRepository.CLOVER10;
import static blackjack.fixture.CardRepository.CLOVER2;
import static blackjack.fixture.CardRepository.CLOVER3;
import static blackjack.fixture.CardRepository.CLOVER4;
import static blackjack.fixture.CardRepository.CLOVER5;
import static blackjack.fixture.CardRepository.CLOVER6;
import static blackjack.fixture.CardRepository.CLOVER_ACE;
import static blackjack.fixture.CardRepository.CLOVER_KING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import blackjack.strategy.CardSupplier;
import blackjack.strategy.CardsViewStrategy;
import blackjack.strategy.HitOrStayChoiceStrategy;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    private static final HitOrStayChoiceStrategy HIT_CHOICE = () -> true;
    private static final HitOrStayChoiceStrategy STAY_CHOICE = () -> false;
    private static final CardsViewStrategy VIEW_STRATEGY = (p) -> {
    };

    private Player player;
    private CardBundle cardBundle;

    @BeforeEach
    void setUp() {
        cardBundle = generateCardBundleOf(CLOVER4, CLOVER5);
        player = Player.of("hudi", cardBundle);
    }

    @DisplayName("플레이어의 이름을 빈 문자열로 생성하려는 경우 예외가 발생한다.")
    @Test
    void constructor_throwExceptionOnBlankName() {
        assertThatThrownBy(() -> Player.of("", cardBundle))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어는 이름을 지녀야 합니다.");
    }

    @DisplayName("플레이어의 이름을 '딜러'로 생성하려는 경우 예외가 발생한다.")
    @Test
    void constructor_throwExceptionOnDealerName() {
        assertThatThrownBy(() -> Player.of("딜러", cardBundle))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름은 딜러가 될 수 없습니다.");
    }

    @DisplayName("플레이어 drawAllCards 메서드 테스트")
    @Nested
    class DrawAllCardsTest {

        @DisplayName("계속 드로우하도록 하면 21이상이 될 때까지 카드를 추가한다.")
        @Test
        void drawAllCards_keepDrawingUntilBust() {
            CardBundle cardBundle = generateCardBundleOf(CLOVER5, CLOVER6);
            CardSupplier cardSupplier = new CardDeckStub(List.of(CLOVER2, CLOVER10, CLOVER3));

            Dealer dealer = Dealer.of(cardBundle);
            dealer.drawAllCards(HIT_CHOICE, VIEW_STRATEGY, cardSupplier);

            assertThat(extractCards(dealer)).containsExactly(CLOVER5, CLOVER6, CLOVER2, CLOVER10);
            assertThat(dealer.canDraw()).isFalse();
            assertThat(dealer.isBust()).isTrue();
        }

        @DisplayName("계속 드로우하도록 해도 21이 되었을 때 카드 뽑기를 중단한다.")
        @Test
        void drawAllCards_keepDrawingUntil21() {
            CardBundle cardBundle = generateCardBundleOf(CLOVER5, CLOVER6);
            CardSupplier cardSupplier = new CardDeckStub(List.of(CLOVER10, CLOVER3));

            Dealer dealer = Dealer.of(cardBundle);
            dealer.drawAllCards(HIT_CHOICE, VIEW_STRATEGY, cardSupplier);

            assertThat(extractCards(dealer)).containsExactly(CLOVER5, CLOVER6, CLOVER10);
            assertThat(dealer.canDraw()).isFalse();
            assertThat(dealer.isBust()).isFalse();
        }

        @DisplayName("stay를 선택하는 경우 21이 아니어도 카드를 뽑지 않는다.")
        @Test
        void drawAllCards_immediatelyStops() {
            CardBundle cardBundle = generateCardBundleOf(CLOVER5, CLOVER6);

            Dealer dealer = Dealer.of(cardBundle);
            dealer.drawAllCards(STAY_CHOICE, VIEW_STRATEGY, () -> CLOVER10);

            assertThat(extractCards(dealer)).containsExactly(CLOVER5, CLOVER6);
            assertThat(dealer.canDraw()).isFalse();
        }
    }

    @DisplayName("Player 인스턴스에는 CardBundle의 isBust 메서드가 구현되어있다.")
    @Test
    void isBust_implementationTest() {
        CardBundle cardBundle = generateCardBundleOf(CLOVER6, CLOVER10);
        Player player = Player.of("jeong", cardBundle);
        player.drawAllCards(HIT_CHOICE, VIEW_STRATEGY, () -> CLOVER_KING);

        boolean actual = player.isBust();

        assertThat(actual).isTrue();
    }

    @DisplayName("Player 인스턴스에는 Participant의 isBlackjack 메서드가 구현되어있다.")
    @Test
    void isBlackjack_implementationTest() {
        CardBundle cardBundle = generateCardBundleOf(CLOVER10, CLOVER_ACE);
        Player player = Player.of("hudi", cardBundle);

        boolean actual = player.isBlackjack();

        assertThat(actual).isTrue();
    }

    @DisplayName("플레이어의 getInitialOpenCards 메서드는 초기에 받은 두 장의 카드가 담긴 컬렉션을 반환한다.")
    @Test
    void getInitialOpenCards() {
        List<Card> actual = player.getInitialOpenCards();

        assertThat(actual).containsExactly(CLOVER4, CLOVER5);
        assertThat(actual.size()).isEqualTo(2);
    }

    private static class CardDeckStub implements CardSupplier {

        final LinkedList<Card> cards = new LinkedList<>();

        CardDeckStub(List<Card> cards) {
            this.cards.addAll(cards);
        }

        @Override
        public Card getCard() {
            return cards.poll();
        }
    }

    private List<Card> extractCards(Participant participant) {
        CardBundle cardBundle = participant.getCardBundle();
        return cardBundle.getCards();
    }
}
