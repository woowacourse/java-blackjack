package blackjack.domain.participant2;

import static blackjack.fixture.CardRepository.CLOVER10;
import static blackjack.fixture.CardRepository.CLOVER2;
import static blackjack.fixture.CardRepository.CLOVER3;
import static blackjack.fixture.CardRepository.CLOVER4;
import static blackjack.fixture.CardRepository.CLOVER8;
import static blackjack.fixture.CardRepository.CLOVER_KING;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import blackjack.domain.hand.CardHand;
import blackjack.domain.hand.OneCard;
import blackjack.fixture.CardDeckStub;
import blackjack.strategy.CardsViewStrategy2;
import blackjack.strategy.HitOrStayChoiceStrategy;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    private static final HitOrStayChoiceStrategy HIT_CHOICE = () -> true;
    private static final HitOrStayChoiceStrategy STAY_CHOICE = () -> false;
    private static final CardsViewStrategy2 VIEW_STRATEGY = (p) -> {
    };

    private Participant player;

    @BeforeEach
    void setUp() {
        CardHand cardHand = new OneCard(CLOVER8).hit(CLOVER_KING);
        player = new Player("jeong", cardHand);

    }

    @DisplayName("플레이어 drawAll 메서드 테스트")
    @Nested
    class DrawAllCardsTest {

        @DisplayName("계속 드로우하도록 하면 21이상이 될 때까지 카드를 추가한다.")
        @Test
        void drawAll_keepDrawingUntilBust() {
            List<Card> cardsToBeAdded = List.of(CLOVER2, CLOVER3, CLOVER4);

            player.drawAll(HIT_CHOICE, VIEW_STRATEGY, new CardDeckStub(cardsToBeAdded));

            assertThat(extractCards(player)).containsExactly(CLOVER8, CLOVER_KING, CLOVER2, CLOVER3);
        }

        @DisplayName("계속 드로우하도록 해도 21이 되었을 때 카드 뽑기를 중단한다.")
        @Test
        void drawAll_stayOn21() {
            List<Card> cardsToBeAdded = List.of(CLOVER3, CLOVER4);

            player.drawAll(HIT_CHOICE, VIEW_STRATEGY, new CardDeckStub(cardsToBeAdded));

            assertThat(extractCards(player)).containsExactly(CLOVER8, CLOVER_KING, CLOVER3);
        }

        @DisplayName("stay를 선택하는 경우 21이 아니어도 카드를 뽑지 않는다.")
        @Test
        void drawAll_stopsOnStay() {
            player.drawAll(STAY_CHOICE, VIEW_STRATEGY, () -> CLOVER10);

            assertThat(extractCards(player)).containsExactly(CLOVER8, CLOVER_KING);
        }
    }

    @DisplayName("openInitialCards 메서드는 플레이어가 초기에 받은 두 장 카드만을 반환한다.")
    @Test
    void openInitialCards() {
        player.drawAll(HIT_CHOICE, VIEW_STRATEGY, CardDeckStub.of(CLOVER2, CLOVER10));

        List<Card> actual = player.openInitialCards();

        assertThat(actual).containsExactly(CLOVER8, CLOVER_KING);
    }

    private List<Card> extractCards(Participant participant) {
        CardBundle cardBundle = participant.getCardBundle();
        return cardBundle.getCards();
    }
}
