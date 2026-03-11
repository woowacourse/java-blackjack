package controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import domain.Card;
import domain.CardContents;
import domain.CardCreationStrategy;
import domain.CardShape;
import domain.Deck;
import domain.Player;
import dto.ParticipantDto;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import view.InputView;
import view.OutputView;

@ExtendWith(MockitoExtension.class)
class BlackJackControllerTest {
    private static final String TEST_NAME = "tester";

    @Mock
    private InputView inputView;

    @Mock
    private OutputView outputView;

    @Mock
    private CardCreationStrategy strategy;

    @InjectMocks
    private BlackJackController controller;

    @Test
    @DisplayName("이름 잘 물어봄")
    void askPlayerNames_success() {
        //given
        when(inputView.readNames()).thenReturn(List.of("pobi", "gump"));

        //when
        List<String> result = controller.askPlayerNames();

        //then
        verify(outputView, times(1)).printNamePrompt();
        verify(inputView, times(1)).readNames();
    }

    @Test
    @DisplayName("카드 내용 출력 전달 잘함")
    void showPlayerCards_success() {
        //given
        List<Card> testCards = List.of(
                new Card(CardShape.하트, CardContents.TWO),
                new Card(CardShape.하트, CardContents.THREE),
                new Card(CardShape.하트, CardContents.FOUR)
        );
        CardCreationStrategy strategy = new CardCreationStrategy() {
            @Override
            public List<Card> create() {
                return new ArrayList<>(testCards);
            }
        };

        Deck totalDeck = Deck.createDeck(strategy);
        Deck playerDeck = Deck.createParticipantDeck(totalDeck);
        Player PLAYER = new Player(playerDeck, TEST_NAME);
        ParticipantDto PARTICIPANT_DTO = ParticipantDto.from(PLAYER);

        //when && then
        assertDoesNotThrow(
                () -> controller.showPlayerCards(PARTICIPANT_DTO)
        );
    }

    @Nested
    class addDrawCardTest {
        @Test
        @DisplayName("질문해서 y 면 true 반환")
        void askDrawCard_true() {
            //given
            when(inputView.readHitOrStand()).thenReturn("y");

            //when
            boolean result = controller.askDrawCard(TEST_NAME);

            //then
            Assertions.assertThat(result).isTrue();
        }

        @Test
        @DisplayName("질문해서 n이면 false 반환")
        void askDrawCard_false() {
            //given
            when(inputView.readHitOrStand()).thenReturn("n");

            //when
            boolean result = controller.askDrawCard(TEST_NAME);

            //then
            Assertions.assertThat(result).isFalse();
        }
    }
}
