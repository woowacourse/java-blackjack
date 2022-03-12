package blackjack.domain.game;

import static blackjack.fixture.CardRepository.CLOVER10;
import static blackjack.fixture.CardRepository.CLOVER6;
import static blackjack.fixture.CardRepository.CLOVER7;
import static blackjack.fixture.CardRepository.CLOVER_KING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BlackjackGameTest {

    private static final List<Card> TWO_CARDS_OF_SIXTEEN = List.of(CLOVER6, CLOVER10);
    private static final List<Card> TWO_CARDS_OF_SEVENTEEN = List.of(CLOVER7, CLOVER10);
    private static final Card DRAWABLE_CARD = CLOVER_KING;

    @DisplayName("생성자 테스트")
    @Nested
    class ConstructorTest {

        @DisplayName("생성자는 1명 이상의 플레이어명을 리스트로 받아 게임을 생성한다.")
        @Test
        void constructor_initsGameWithPlayerNames() {
            BlackjackGame blackjackGame = new BlackjackGame(
                    new CardDeck(), List.of("hudi", "jeong"));

            List<Player> participants = blackjackGame.getPlayers();

            assertThat(participants.size()).isEqualTo(2);
            assertThat(participants.get(0).getName()).isEqualTo("hudi");
            assertThat(participants.get(1).getName()).isEqualTo("jeong");
        }

        @DisplayName("생성자 파라미터에 들어오는 플레이어명 리스트가 비어있으면 예외가 발생한다.")
        @Test
        void constructor_throwsExceptionOnNoPlayerNameInput() {
            assertThatThrownBy(() -> new BlackjackGame(new CardDeck(), List.of()))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("플레이어가 없는 게임은 존재할 수 없습니다.");
        }

        @DisplayName("생성자 파라미터에 들어오는 플레이어명들 중 중복이 존재하면 예외가 발생한다.")
        @Test
        void constructor_throwsExceptionOnDuplicatePlayerNameInput() {
            assertThatThrownBy(() -> new BlackjackGame(
                    new CardDeck(), List.of("중복", "중복")))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("플레이어명은 중복될 수 없습니다.");
        }
    }

    @DisplayName("dealerCanDraw 메서드 테스트")
    @Nested
    class DealerCanDrawTest {

        @DisplayName("dealerCanDraw 메서드는 딜러의 점수가 16이하인 경우 true를 반환한다.")
        @Test
        void dealerCanDraw_returnTrueIfDealerCanDraw() {
            BlackjackGame blackjackGame = BlackjackGameStub.ofDealerCards(TWO_CARDS_OF_SIXTEEN);

            boolean actual = blackjackGame.dealerCanDraw();

            assertThat(actual).isTrue();
        }

        @DisplayName("dealerCanDraw 메서드는 딜러의 점수가 17이상인 경우 false를 반환한다.")
        @Test
        void dealerCanDraw_returnFalseIfDealerCanNotDraw() {
            BlackjackGame blackjackGame = BlackjackGameStub.ofDealerCards(TWO_CARDS_OF_SEVENTEEN);

            boolean actual = blackjackGame.dealerCanDraw();

            assertThat(actual).isFalse();
        }
    }

    @DisplayName("drawDealerCard 메서드는 딜러에게 카드를 한 장 더 제공한다.")
    @Test
    void drawDealerCard_givesExtraCardToDealer() {
        BlackjackGame blackjackGame = BlackjackGameStub.ofDealerCards(TWO_CARDS_OF_SIXTEEN);

        blackjackGame.drawDealerCard();
        Score actual = blackjackGame.getDealer().getCurrentScore();
        Score expected = Score.valueOf(26);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("카드 덱에서 카드 한장을 뽑아서 반환한다.")
    @Test
    void popCard_returnPoppedCard() {
        BlackjackGame blackjackGame = BlackjackGameStub.ofDealerCards(TWO_CARDS_OF_SIXTEEN);

        Card actual = blackjackGame.popCard();

        assertThat(actual).isEqualTo(DRAWABLE_CARD);
    }

    private static class BlackjackGameStub extends BlackjackGame {

        private final Dealer fakeDealer;
        private final LinkedList<Card> drawableCards = new LinkedList<>(List.of(DRAWABLE_CARD));

        private BlackjackGameStub(Dealer dealer) {
            super(new CardDeck(), List.of("single_player"));
            this.fakeDealer = dealer;
        }

        public static BlackjackGameStub ofDealerCards(List<Card> dealerCards) {
            Dealer fakeDealer = Dealer.of(CardBundle.of(dealerCards.get(0), dealerCards.get(1)));
            return new BlackjackGameStub(fakeDealer);
        }

        @Override
        public boolean dealerCanDraw() {
            return fakeDealer.canDraw();
        }

        @Override
        public void drawDealerCard() {
            fakeDealer.receiveCard(popCard());
        }

        @Override
        public Card popCard() {
            return drawableCards.pop();
        }

        @Override
        public Dealer getDealer() {
            return fakeDealer;
        }
    }
}
