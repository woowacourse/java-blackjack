package blackjack.domain.game;

import static blackjack.fixture.CardBundleGenerator.getCardBundleOfBlackjack;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfSeventeen;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfSixteen;
import static blackjack.fixture.CardRepository.CLOVER_KING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardBundle;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardStack;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.strategy.CardBundleStrategy;
import java.util.List;
import java.util.function.Function;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BlackjackGameTest {

    private static final Card DRAWABLE_CARD = CLOVER_KING;
    private static final List<String> PLAYER_NAMES_LIST = List.of("player1", "player2");
    private static final CardBundleStrategy prodStrategy = (cardStack) -> CardBundle.of(cardStack.pop(),
            cardStack.pop());
    private static final CardBundleStrategy cardBundleOfSixteenStrategy = (cardStack) -> getCardBundleOfSixteen();
    private static final CardBundleStrategy cardBundleOfSeventeenStrategy = (cardStack) -> getCardBundleOfSeventeen();
    private static final CardBundleStrategy cardBundleOfBlackjackStrategy = (cardStack) -> getCardBundleOfBlackjack();
    private static final Function<String, Boolean> DRAW_CHOICE = (s) -> true;

    @DisplayName("생성자 테스트")
    @Nested
    class ConstructorTest {

        @DisplayName("생성자는 1명 이상의 플레이어명을 리스트로 받아 게임을 생성한다.")
        @Test
        void constructor_initsGameWithPlayerNames() {
            BlackjackGame blackjackGame = new BlackjackGame(
                    new CardDeck(), List.of("hudi", "jeong"), prodStrategy);

            List<Player> participants = blackjackGame.getPlayers();

            assertThat(participants.size()).isEqualTo(2);
            assertThat(participants.get(0).getName()).isEqualTo("hudi");
            assertThat(participants.get(1).getName()).isEqualTo("jeong");
        }

        @DisplayName("생성자 파라미터에 들어오는 플레이어명 리스트가 비어있으면 예외가 발생한다.")
        @Test
        void constructor_throwsExceptionOnNoPlayerNameInput() {
            assertThatThrownBy(() -> new BlackjackGame(new CardDeck(), List.of(), prodStrategy))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("플레이어가 없는 게임은 존재할 수 없습니다.");
        }

        @DisplayName("생성자 파라미터에 들어오는 플레이어명들 중 중복이 존재하면 예외가 발생한다.")
        @Test
        void constructor_throwsExceptionOnDuplicatePlayerNameInput() {
            assertThatThrownBy(() -> new BlackjackGame(
                    new CardDeck(), List.of("중복", "중복"), prodStrategy))
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
            BlackjackGame blackjackGame = new BlackjackGame(
                    new CardsStub(), PLAYER_NAMES_LIST, cardBundleOfSixteenStrategy);

            boolean actual = blackjackGame.dealerCanDraw();

            assertThat(actual).isTrue();
        }

        @DisplayName("dealerCanDraw 메서드는 딜러의 점수가 17이상인 경우 false를 반환한다.")
        @Test
        void dealerCanDraw_returnFalseIfDealerCanNotDraw() {
            BlackjackGame blackjackGame = new BlackjackGame(
                    new CardsStub(), PLAYER_NAMES_LIST, cardBundleOfSeventeenStrategy);

            boolean actual = blackjackGame.dealerCanDraw();

            assertThat(actual).isFalse();
        }
    }

    @DisplayName("drawDealerCard 메서드는 딜러에게 카드를 한 장 더 제공한다.")
    @Test
    void drawDealerCard_givesExtraCardToDealer() {
        BlackjackGame blackjackGame = new BlackjackGame(
                new CardsStub(), PLAYER_NAMES_LIST, cardBundleOfSixteenStrategy);

        blackjackGame.drawDealerCard();
        Score actual = blackjackGame.getDealer().getCurrentScore();
        Score expected = Score.valueOf(26);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("카드 덱에서 카드 한장을 뽑아서 반환한다.")
    @Test
    void popCard_returnPoppedCard() {
        BlackjackGame blackjackGame = new BlackjackGame(
                new CardsStub(), PLAYER_NAMES_LIST, cardBundleOfSixteenStrategy);

        Card actual = blackjackGame.popCard();

        assertThat(actual).isEqualTo(DRAWABLE_CARD);
    }

    @DisplayName("isBlackjackDealer 메서드는 Dealer 인스턴스의 isBlackjack 메서드를 호출한다.")
    @Test
    void isBlackjackDealer_trueOnDealerBlackjack() {
        BlackjackGame blackjackGame = new BlackjackGame(
                new CardDeck(), PLAYER_NAMES_LIST, cardBundleOfBlackjackStrategy);

        boolean actual = blackjackGame.isBlackjackDealer();

        assertThat(actual).isTrue();
    }

    @DisplayName("getParticipants 메서드는 Dealer 인스턴스와 1명 이상의 Player 인스턴스로 구성된 컬렉션을 반환한다.")
    @Test
    void getParticipants_returnsListOfParticipantsWithDealerAtIndexZero() {
        BlackjackGame blackjackGame = new BlackjackGame(
                new CardDeck(), List.of("p1", "p2", "p3"), cardBundleOfSixteenStrategy);

        List<Participant> actual = blackjackGame.getParticipants();

        assertThat(actual.get(0)).isInstanceOf(Dealer.class);
        for (int i = 1; i < actual.size(); i++) {
            assertThat(actual.get(i)).isInstanceOf(Player.class);
        }
    }

    @DisplayName("distributeAllPlayerCards 메서드 호출 후 모든 플레이어는 더 이상 드로우를 할 수 없게 된다.")
    @Test
    void distributeAllPlayerCards() {
        BlackjackGame blackjackGame = new BlackjackGame(
                new CardDeck(), List.of("p1", "p2", "p3"), prodStrategy);

        blackjackGame.distributeAllPlayerCards(DRAW_CHOICE);

        blackjackGame.getPlayers()
                .forEach(player -> assertThat(player.isBust() || player.isBlackjack()).isTrue());
    }

    private static class CardsStub implements CardStack {

        @Override
        public Card pop() {
            return DRAWABLE_CARD;
        }
    }
}
