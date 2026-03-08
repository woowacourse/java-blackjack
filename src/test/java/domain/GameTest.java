package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import controller.GameDelegate;
import dto.GameResultDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GameTest {
    private static final List<String> TEST_PLAYER_NAMES = List.of("pobi", "terry", "rati", "gump");
    private static final CardCreationStrategy STRATEGY = GameTest::createSampleCards;
    @Mock
    GameDelegate gameDelegate;
    @Captor
    ArgumentCaptor<GameResultDto> gameResultDtoArgumentCaptor;

    private static List<Card> createSampleCards() {
        CardShape[] shapes = CardShape.values();
        CardContents[] contents = CardContents.values();

        List<Card> sampleCards = new ArrayList<>();
        for (CardShape cardShape : shapes) {
            for (CardContents content : contents) {
                sampleCards.add(new Card(cardShape, content));
            }
        }

        return sampleCards;
    }

    @Test
    @DisplayName("생성 잘 한다")
    void ready_good() {
        //given

        //when, then
        assertDoesNotThrow(
                () -> Game.ready(gameDelegate, STRATEGY)
        );
    }

    @Test
    @DisplayName("게임 플레이 잘함")
    void play_success() {
        //given
        when(gameDelegate.askPlayerNames()).thenReturn(TEST_PLAYER_NAMES);
        Game game = Game.ready(gameDelegate, STRATEGY);

        //when
        game.play(gameDelegate);

        //then
        for (String name : TEST_PLAYER_NAMES) {
            verify(gameDelegate, atLeast(1)).askDrawCard(name);
        }
        verify(gameDelegate, atLeast(1)).showDealerOneMoreCardMessage();

    }

    @Test
    @DisplayName("결과 계산 잘함")
    void calculate_success() {
        //given
        CardCreationStrategy strategy = new CardCreationStrategy() {
            @Override
            public List<Card> create() {
                return new ArrayList<>(
                        List.of(
                                new Card(CardShape.하트, CardContents.J),
                                new Card(CardShape.하트, CardContents.EIGHT),
                                new Card(CardShape.다이아몬드, CardContents.J),
                                new Card(CardShape.다이아몬드, CardContents.THREE),
                                new Card(CardShape.스페이드, CardContents.J),
                                new Card(CardShape.스페이드, CardContents.THREE),
                                new Card(CardShape.클로버, CardContents.J),
                                new Card(CardShape.클로버, CardContents.NINE),
                                new Card(CardShape.하트, CardContents.K),
                                new Card(CardShape.하트, CardContents.NINE)
                        )
                );
            }
        };

        when(gameDelegate.askPlayerNames()).thenReturn(TEST_PLAYER_NAMES);
        Game game = Game.ready(gameDelegate, strategy);
        Map<String, Integer> expectDealerWinLossResults = consistExpectDealerWinLossResults();
        Map<String, String> expectPlayerWinLossResults = consistExpectPlayerWinLossResults(TEST_PLAYER_NAMES);

        //when
        game.play(gameDelegate);
        game.end(gameDelegate);

        //then
        verify(gameDelegate, times(1))
                .showGameResult(gameResultDtoArgumentCaptor.capture());
        GameResultDto result = gameResultDtoArgumentCaptor.getValue();
        assertThat(result.dealerWinLossResults()).isEqualTo(expectDealerWinLossResults);
        assertThat(result.playerWinLossResults()).isEqualTo(expectPlayerWinLossResults);
    }

    private Map<String, String> consistExpectPlayerWinLossResults(List<String> testPlayerNames) {
        Map<String, String> expectPlayerWinLossResults = new LinkedHashMap<>();
        expectPlayerWinLossResults.put("pobi", "패");
        expectPlayerWinLossResults.put("terry", "패");
        expectPlayerWinLossResults.put("rati", "승");
        expectPlayerWinLossResults.put("gump", "승");
        return expectPlayerWinLossResults;
    }

    private Map<String, Integer> consistExpectDealerWinLossResults() {
        Map<String, Integer> expectDealerWinLossResults = new HashMap<>();
        expectDealerWinLossResults.put("승", 2);
        expectDealerWinLossResults.put("패", 2);
        return expectDealerWinLossResults;
    }
}