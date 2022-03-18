package blackjack.domain;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import blackjack.MockDeck;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.HoldCards;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Name;
import blackjack.domain.player.Player;

public class ScoreTest {

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

            Assertions.assertThat(Score.compete(player, dealer)).isEqualTo(Score.WIN);
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

            Assertions.assertThat(Score.compete(player, dealer)).isEqualTo(Score.DRAW);
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

            Assertions.assertThat(Score.compete(player, dealer)).isEqualTo(Score.DRAW);
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

            Assertions.assertThat(Score.compete(player, dealer)).isEqualTo(Score.LOSE);
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
            Assertions.assertThat(Score.compete(player, dealer)).isEqualTo(Score.LOSE);
        }
    }

    @Nested
    @DisplayName("inverse는")
    class Inverse {

        @ParameterizedTest
        @CsvSource(value = {"WIN|LOSE", "DRAW|DRAW", "LOSE|WIN"}, delimiter = '|')
        @DisplayName("입력한 스코어의 반대 스코어를 반환한다.")
        void returnInverseScore(Score score, Score expected) {
            Assertions.assertThat(score.inverse()).isEqualTo(expected);
        }
    }
}
