package controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import domain.Deck;
import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import service.BlackJackInitService;
import service.BlackJackTurnService;


public class BlackJackControllerTest {

    private ByteArrayOutputStream outputStreamCaptor;

    @BeforeEach
    void setUp() {
        systemIn("시오,봉구스\n10000\n20000\ny\nn\ny\nn\n"
                + "시오,봉구스\n10000\n20000\ny\nn\ny\nn");

        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    private void systemIn(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    private String getOutput() {
        return outputStreamCaptor.toString();
    }

    @Nested
    class 정상적으로_동작하는_경우 {

        BlackJackController blackJackController;

        @BeforeEach
        void setUp() {
            Deck deck = mock(Deck.class);
            when(deck.drawCard()).thenReturn(
                    // 시오
                    new Card(Suit.HEARTS, Rank.TWO),
                    new Card(Suit.HEARTS, Rank.THREE),
                    // 봉구스
                    new Card(Suit.HEARTS, Rank.FOUR),
                    new Card(Suit.HEARTS, Rank.FIVE),
                    // 딜러
                    new Card(Suit.HEARTS, Rank.SIX),
                    new Card(Suit.HEARTS, Rank.SEVEN),
                    // 시오
                    new Card(Suit.HEARTS, Rank.EIGHT),
                    // 봉구스
                    new Card(Suit.HEARTS, Rank.NINE),
                    // 딜러
                    new Card(Suit.SPADES, Rank.TWO),
                    new Card(Suit.SPADES, Rank.THREE),

                    new Card(Suit.SPADES, Rank.FOUR),
                    new Card(Suit.SPADES, Rank.FIVE),
                    new Card(Suit.SPADES, Rank.SIX),
                    new Card(Suit.SPADES, Rank.SEVEN),
                    new Card(Suit.SPADES, Rank.EIGHT)
            );

            BlackJackInitService blackJackInitService = spy(new BlackJackInitService());
            doReturn(deck).when(blackJackInitService).createDeck();

            BlackJackTurnService blackJackTurnService = new BlackJackTurnService();

            blackJackController = new BlackJackController(blackJackInitService, blackJackTurnService);
        }

        @Test
        void 출력_형식을_지켜야_한다() {
            // when
            blackJackController.run();

            // then
            String output = getOutput();
            assertThat(output)
                    .contains("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
                    .contains("딜러와 시오, 봉구스에게 2장을 나누었습니다.")
                    .contains("시오의 배팅 금액은?")
                    .contains("봉구스의 배팅 금액은?")
                    .contains("딜러카드:")
                    .contains("시오카드:")
                    .contains("봉구스카드:")
                    .contains("시오는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
                    .contains("봉구스는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
                    .contains("딜러는 16이하라 한장의 카드를 더 받았습니다.")
                    .contains("## 최종 수익")
                    .contains("딜러:")
                    .contains("시오:")
                    .contains("봉구스:");
        }

        @Test
        void 로직이_정상적으로_동작한다() {
            // when
            blackJackController.run();

            // then
            String output = getOutput();
            assertThat(output)
                    .contains("딜러카드: 6하트")
                    .contains("시오카드: 2하트, 3하트")
                    .contains("봉구스카드: 4하트, 5하트")
                    .contains("시오카드: 2하트, 3하트, 8하트")
                    .contains("봉구스카드: 4하트, 5하트, 9하트")
                    .contains("딜러카드: 6하트, 7하트, 2스페이드, 3스페이드 - 결과: 18")
                    .contains("시오카드: 2하트, 3하트, 8하트 - 결과: 13")
                    .contains("봉구스카드: 4하트, 5하트, 9하트 - 결과: 18")
                    .contains("딜러: 10000")
                    .contains("시오: -10000")
                    .contains("봉구스: 0");
        }
    }
}
