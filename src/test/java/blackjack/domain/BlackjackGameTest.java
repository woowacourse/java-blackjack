package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.CardDeck;
import blackjack.domain.hand.CardHand;
import blackjack.domain.participant.Participant;
import blackjack.strategy.CardsViewStrategy;
import blackjack.strategy.HitOrStayStrategy;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BlackjackGameTest {

    private static final HitOrStayStrategy HIT_CHOICE = () -> true;
    private static final CardsViewStrategy VIEW_STRATEGY = (dealer) -> {
    };

    @DisplayName("생성자 테스트")
    @Nested
    class ConstructorTest {

        @DisplayName("생성자는 1명 이상의 플레이어명을 순서대로 받아 게임을 생성한다.")
        @Test
        void constructor() {
            BlackjackGame blackjackGame = new BlackjackGame(new CardDeck(), List.of("hudi", "jeong"));

            List<Participant> participants = extractPlayers(blackjackGame);

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
            assertThatThrownBy(() -> new BlackjackGame(new CardDeck(), List.of("중복", "중복")))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("플레이어명은 중복될 수 없습니다.");
        }
    }

    @DisplayName("drawAllPlayerCards 메서드를 호출하면 모든 플레이어는 더 이상 hit이 불가능한 상태가 된다.")
    @Test
    void drawAllPlayerCards_isFinishedAfterKeepHitting() {
        for (int i = 0; i < 1000; i++) {
            BlackjackGame blackjackGame = new BlackjackGame(
                    new CardDeck(), List.of("p1", "p2", "p3"));

            blackjackGame.drawAllPlayerCards(HIT_CHOICE, VIEW_STRATEGY);
            List<CardHand> playerHands = extractPlayers(blackjackGame).stream()
                    .map(Participant::getHand)
                    .collect(Collectors.toUnmodifiableList());

            for (CardHand playerHand : playerHands) {
                assertThat(playerHand.isFinished()).isTrue();
            }
        }
    }

    @DisplayName("drawDealerCards 메서드 호출 후 딜러는 더 이상 hit이 불가능한 상태가 된다.")
    @Test
    void drawDealerCards_isFinishedAfterKeepHitting() {
        for (int i = 0; i < 1000; i++) {
            BlackjackGame blackjackGame = new BlackjackGame(new CardDeck(), List.of("p1"));
            blackjackGame.drawDealerCards(VIEW_STRATEGY);

            CardHand dealerHand = extractDealer(blackjackGame).getHand();

            assertThat(dealerHand.isFinished()).isTrue();
        }
    }

    private Participant extractDealer(BlackjackGame blackjackGame) {
        return blackjackGame.getParticipants().getDealer();
    }

    private List<Participant> extractPlayers(BlackjackGame blackjackGame) {
        return blackjackGame.getParticipants().getPlayers();
    }
}
