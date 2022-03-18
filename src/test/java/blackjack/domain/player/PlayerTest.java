package blackjack.domain.player;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.MockDeck;
import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.HoldCards;

public class PlayerTest {

    @Nested
    @DisplayName("drawCard는")
    class DrawCard {

        @Test
        @DisplayName("Card를 자신의 패에 추가한다.")
        void addCard() {
            MockDeck mockDeck = new MockDeck(List.of(Card.of(CardPattern.CLOVER, CardNumber.JACK),
                Card.of(CardPattern.CLOVER, CardNumber.JACK), Card.of(CardPattern.CLOVER, CardNumber.JACK)));
            Player player = new Player(new Name("roma"), HoldCards.drawTwoCards(mockDeck));
            player.drawCard(mockDeck);
            Assertions.assertThat(player.getTotalNumber()).isEqualTo(30);
        }
    }

    @Nested
    @DisplayName("isBust는")
    class IsBust {

        @ParameterizedTest
        @CsvSource(value = {"ACE|false", "TWO|true"}, delimiter = '|')
        @DisplayName("패의 합이 21이 넘는지 유무를 알려준다.")
        void returnFalse(CardNumber cardNumber, boolean expected) {
            MockDeck mockDeck = new MockDeck(List.of(Card.of(CardPattern.CLOVER, CardNumber.JACK)
                , Card.of(CardPattern.CLOVER, CardNumber.KING),
                Card.of(CardPattern.CLOVER, cardNumber)));
            Player player = new Player(new Name("roma"), HoldCards.drawTwoCards(mockDeck));
            player.drawCard(mockDeck);

            Assertions.assertThat(player.isBust()).isEqualTo(expected);
        }
    }

    @Nested
    @DisplayName("compete는")
    class Compete {

        @Test
        @DisplayName("플레이어가 블랙잭이고 딜러가 블랙잭이 아니면 승리")
        void win() {
            MockDeck blackjackDeck = new MockDeck(List.of(
                Card.of(CardPattern.DIAMOND, CardNumber.ACE), Card.of(CardPattern.DIAMOND, CardNumber.TEN)
            ));
            Player player = new Player(new Name("tonic"),
                HoldCards.drawTwoCards(blackjackDeck));
            Dealer dealer = new Dealer(() -> Card.of(CardPattern.DIAMOND, CardNumber.NINE));

            Assertions.assertThat(player.compete(dealer)).isEqualTo(Score.WIN);
        }

        @Test
        @DisplayName("딜러도 블랙잭이면 무승부")
        void draw1() {
            MockDeck blackjackDeck = new MockDeck(List.of(
                Card.of(CardPattern.DIAMOND, CardNumber.ACE), Card.of(CardPattern.DIAMOND, CardNumber.TEN)
            ));
            Player player = new Player(new Name("tonic"),
                HoldCards.drawTwoCards(blackjackDeck));
            MockDeck blackjackDeck2 = new MockDeck(List.of(
                Card.of(CardPattern.DIAMOND, CardNumber.ACE), Card.of(CardPattern.DIAMOND, CardNumber.TEN)
            ));
            Dealer dealer = new Dealer(blackjackDeck2);

            Assertions.assertThat(player.compete(dealer)).isEqualTo(Score.DRAW);
        }

        @Test
        @DisplayName("딜러가 버스트이면 무승부")
        void draw2() {
            MockDeck blackjackDeck = new MockDeck(List.of(
                Card.of(CardPattern.DIAMOND, CardNumber.TEN), Card.of(CardPattern.DIAMOND, CardNumber.TEN)
            ));
            Player player = new Player(new Name("tonic"),
                HoldCards.drawTwoCards(blackjackDeck));
            MockDeck blackjackDeck2 = new MockDeck(List.of(
                Card.of(CardPattern.DIAMOND, CardNumber.TEN),
                Card.of(CardPattern.DIAMOND, CardNumber.TEN),
                Card.of(CardPattern.DIAMOND, CardNumber.TEN)
            ));
            Dealer dealer = new Dealer(blackjackDeck2);
            dealer.drawCard(blackjackDeck2);

            Assertions.assertThat(player.compete(dealer)).isEqualTo(Score.DRAW);
        }

        @Test
        @DisplayName("양쪽 다 버스트가 아니고 숫자의 합이 높으면 무승부")
        void draw3() {
            MockDeck blackjackDeck = new MockDeck(List.of(
                Card.of(CardPattern.DIAMOND, CardNumber.TEN), Card.of(CardPattern.DIAMOND, CardNumber.TEN)
            ));
            Player player = new Player(new Name("tonic"),
                HoldCards.drawTwoCards(blackjackDeck));
            MockDeck blackjackDeck2 = new MockDeck(List.of(
                Card.of(CardPattern.DIAMOND, CardNumber.TEN),
                Card.of(CardPattern.DIAMOND, CardNumber.NINE)
            ));
            Dealer dealer = new Dealer(blackjackDeck2);

            Assertions.assertThat(Score.compete(player, dealer)).isEqualTo(Score.DRAW);
        }

        @Test
        @DisplayName("양쪽 다 버스트가 아니고 숫자의 합이 작으면 패배")
        void lose1() {
            MockDeck blackjackDeck = new MockDeck(List.of(
                Card.of(CardPattern.DIAMOND, CardNumber.NINE), Card.of(CardPattern.DIAMOND, CardNumber.TEN)
            ));
            Player player = new Player(new Name("tonic"),
                HoldCards.drawTwoCards(blackjackDeck));
            MockDeck blackjackDeck2 = new MockDeck(List.of(
                Card.of(CardPattern.DIAMOND, CardNumber.TEN),
                Card.of(CardPattern.DIAMOND, CardNumber.TEN)
            ));
            Dealer dealer = new Dealer(blackjackDeck2);

            Assertions.assertThat(player.compete(dealer)).isEqualTo(Score.LOSE);
        }

        @Test
        @DisplayName("플레이어가 버스트이면 패배")
        void lose2() {
            MockDeck blackjackDeck = new MockDeck(List.of(
                Card.of(CardPattern.DIAMOND, CardNumber.NINE),
                Card.of(CardPattern.DIAMOND, CardNumber.TEN),
                Card.of(CardPattern.DIAMOND, CardNumber.TEN)
            ));
            Player player = new Player(new Name("tonic"),
                HoldCards.drawTwoCards(blackjackDeck));
            player.drawCard(blackjackDeck);

            MockDeck blackjackDeck2 = new MockDeck(List.of(
                Card.of(CardPattern.DIAMOND, CardNumber.TEN),
                Card.of(CardPattern.DIAMOND, CardNumber.TEN),
                Card.of(CardPattern.DIAMOND, CardNumber.TEN)
            ));
            Dealer dealer = new Dealer(blackjackDeck2);
            dealer.drawCard(blackjackDeck2);
            Assertions.assertThat(player.compete(dealer)).isEqualTo(Score.LOSE);
        }
    }
}
