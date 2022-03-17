package blackjack.domain;

import static blackjack.fixture.CardBundleGenerator.getCardBundleOfBlackjack;
import static blackjack.fixture.CardBundleGenerator.getCardBundleOfSixteen;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.CardBundle;
import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.strategy.CardBundleStrategy;
import blackjack.strategy.CardsViewStrategy;
import blackjack.strategy.HitOrStayChoiceStrategy;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BlackjackGameTest {

    private static final List<String> PLAYER_NAMES_LIST = List.of("player1", "player2");

    private static final CardBundleStrategy prodStrategy =
            (cardStack) -> CardBundle.of(cardStack.pop(), cardStack.pop());
    private static final CardBundleStrategy cardBundleOfSixteenStrategy = (cardStack) -> getCardBundleOfSixteen();
    private static final CardBundleStrategy cardBundleOfBlackjackStrategy = (cardStack) -> getCardBundleOfBlackjack();

    private static final HitOrStayChoiceStrategy DRAW_CHOICE = () -> true;
    private static final CardsViewStrategy CARDS_VIEW_STRATEGY = (dealer) -> {
    };

    @DisplayName("생성자 테스트")
    @Nested
    class ConstructorTest {

        @DisplayName("생성자는 1명 이상의 플레이어명을 리스트로 받아 게임을 생성한다.")
        @Test
        void constructor_initsGameWithPlayerNames() {
            BlackjackGame blackjackGame = new BlackjackGame(
                    new CardDeck(), List.of("hudi", "jeong"), prodStrategy);

            List<Player> participants = blackjackGame.getParticipants().getPlayers();

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

        List<Participant> actual = blackjackGame.getParticipants().getValue();

        assertThat(actual.get(0)).isInstanceOf(Dealer.class);
        for (int i = 1; i < actual.size(); i++) {
            assertThat(actual.get(i)).isInstanceOf(Player.class);
        }
    }

    @DisplayName("drawAllPlayerCards 메서드 호출 후 모든 플레이어는 더 이상 드로우를 할 수 없게 된다.")
    @Test
    void drawAllPlayerCards() {
        for (int i = 0; i < 1000; i++) {
            BlackjackGame blackjackGame = new BlackjackGame(
                    new CardDeck(), List.of("p1", "p2", "p3"), prodStrategy);

            blackjackGame.drawAllPlayerCards(DRAW_CHOICE, CARDS_VIEW_STRATEGY);
            blackjackGame.getParticipants()
                    .getPlayers()
                    .stream()
                    .map(Participant::canDraw)
                    .forEach(playerCanDraw -> assertThat(playerCanDraw).isFalse());
        }
    }

    @DisplayName("drawDealerCards 메서드 호출 후 딜러는 더 이상 드로우를 할 수 없게 된다.")
    @Test
    void drawDealerCards() {
        for (int i = 0; i < 1000; i++) {
            BlackjackGame blackjackGame = new BlackjackGame(
                    new CardDeck(), List.of("p1", "p2", "p3"), prodStrategy);

            blackjackGame.drawDealerCards(CARDS_VIEW_STRATEGY);
            Dealer dealer = blackjackGame.getParticipants().getDealer();

            boolean actual = dealer.canDraw();
            assertThat(actual).isFalse();
        }
    }
}
