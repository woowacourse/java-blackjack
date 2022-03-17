package blackjack.domain.player;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import blackjack.MockDeck;
import blackjack.domain.Score;
import blackjack.domain.ScoreResult;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.Deck;
import blackjack.domain.card.HoldCards;

public class PlayersTest {

    @Nested
    @DisplayName("생성자는")
    class Constructor {

        @Test
        @DisplayName("8명을 초과할 때 예외를 발생시킨다.")
        void throwExceptionOverEight() {
            Deck deck = Deck.create();
            Assertions.assertThatThrownBy(() -> new Players(List.of(
                new Player(new Name("a"), HoldCards.drawTwoCards(deck)),
                new Player(new Name("b"), HoldCards.drawTwoCards(deck)),
                new Player(new Name("c"), HoldCards.drawTwoCards(deck)),
                new Player(new Name("d"), HoldCards.drawTwoCards(deck)),
                new Player(new Name("e"), HoldCards.drawTwoCards(deck)),
                new Player(new Name("f"), HoldCards.drawTwoCards(deck)),
                new Player(new Name("g"), HoldCards.drawTwoCards(deck)),
                new Player(new Name("h"), HoldCards.drawTwoCards(deck)),
                new Player(new Name("q"), HoldCards.drawTwoCards(deck))
            ))).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("인원수는 8명을 넘을 수 없습니다.");
        }
    }

    @Nested
    @DisplayName("compete는")
    class Compete {

        @Test
        @DisplayName("딜러와 승부를 겨루고 결과를 맵으로 반환한다.")
        void returnScoreMap() {
            MockDeck mockDeck = new MockDeck(List.of(Card.of(CardPattern.DIAMOND, CardNumber.NINE),
                Card.of(CardPattern.DIAMOND, CardNumber.TEN),
                Card.of(CardPattern.DIAMOND, CardNumber.ACE),
                Card.of(CardPattern.SPADE, CardNumber.TEN)));

            Player roma = new Player(new Name("roma"),
                HoldCards.drawTwoCards(() -> Card.of(CardPattern.DIAMOND, CardNumber.NINE)));
            Player tonic =new Player(new Name("tonic"),
                HoldCards.drawTwoCards(() -> Card.of(CardPattern.DIAMOND, CardNumber.EIGHT)));
            Player pobi = new Player(new Name("pobi"),
                HoldCards.drawTwoCards(() -> Card.of(CardPattern.DIAMOND, CardNumber.SEVEN)));

            Players players = new Players(List.of(roma, tonic, pobi));

            Dealer dealer = new Dealer(() -> Card.of(CardPattern.DIAMOND, CardNumber.EIGHT));
            ScoreResult result = players.compete(dealer);

            Assertions.assertThat(result.getPlayerScore(roma)).isEqualTo(Score.WIN);
            Assertions.assertThat(result.getPlayerScore(tonic)).isEqualTo(Score.DRAW);
            Assertions.assertThat(result.getPlayerScore(pobi)).isEqualTo(Score.LOSE);
            Assertions.assertThat(result.getDealerScoreCount(Score.LOSE)).isEqualTo(1);
            Assertions.assertThat(result.getDealerScoreCount(Score.DRAW)).isEqualTo(1);
            Assertions.assertThat(result.getDealerScoreCount(Score.WIN)).isEqualTo(1);
        }
    }

}
