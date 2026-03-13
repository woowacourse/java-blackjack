package controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import domain.Card;
import domain.CardContents;
import domain.CardCreationStrategy;
import domain.CardShape;
import dto.GameResultDto;
import dto.GameStateDto;
import dto.ParticipantDto;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import view.InputView;
import view.OutputView;

class BlackJackControllerTest {
    private BlackJackController controller;

    @BeforeEach
    void setUpGame() {
        controller = new BlackJackController(
                new TestInputViewImpl(),
                new TestOutputViewImpl(),
                new TestCardCreationStrategy()
        );
    }

    @Test
    @DisplayName("게임이 정상적으로 수행되면 오류가 발생하지 않는다")
    void askPlayerNames_success() {
        //when, then
        assertDoesNotThrow(
                () -> controller.doGameProcess()
        );
    }

    static class TestInputViewImpl implements InputView {

        private static final Deque<Boolean> hitOrStandOrder = new ArrayDeque<>(
                List.of(Boolean.TRUE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE));

        @Override
        public List<String> readNames() {
            return List.of("pobi", "jason", "gump");
        }

        @Override
        public Boolean wantToHit() {
            return hitOrStandOrder.poll();
        }
    }

    static class TestOutputViewImpl implements OutputView {

        @Override
        public void printErrorMessage(Exception e) {
        }

        @Override
        public void printNamePrompt() {
        }

        @Override
        public void printInitialStates(GameStateDto gameStateDto) {
        }

        @Override
        public void printHitOrStandPrompt(String name) {
        }

        @Override
        public void printUserState(ParticipantDto participantDto) {
        }

        @Override
        public void printDealerAddCardNotice() {
        }

        @Override
        public void printGameResult(GameResultDto gameResultDto) {
        }
    }

    static class TestCardCreationStrategy implements CardCreationStrategy {

        @Override
        public Deque<Card> create() {
            List<Card> candidateCard = List.of(
                    new Card(CardShape.하트, CardContents.A),
                    new Card(CardShape.하트, CardContents.TWO),
                    new Card(CardShape.하트, CardContents.THREE),
                    new Card(CardShape.하트, CardContents.FOUR),
                    new Card(CardShape.하트, CardContents.FIVE),
                    new Card(CardShape.하트, CardContents.SIX),
                    new Card(CardShape.하트, CardContents.SEVEN),
                    new Card(CardShape.하트, CardContents.EIGHT),
                    new Card(CardShape.하트, CardContents.NINE),
                    new Card(CardShape.하트, CardContents.TEN),
                    new Card(CardShape.하트, CardContents.J),
                    new Card(CardShape.하트, CardContents.Q),
                    new Card(CardShape.하트, CardContents.K)
            );

            return new ArrayDeque<>(candidateCard);
        }
    }

}
