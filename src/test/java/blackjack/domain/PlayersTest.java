package blackjack.domain;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import blackjack.MockDeck;

public class PlayersTest {

    @Nested
    @DisplayName("생성자는")
    class Constructor {

        @Test
        @DisplayName("8명을 초과할 때 예외를 발생시킨다.")
        void throwExceptionOverEight() {
            Assertions.assertThatThrownBy(() -> new Players(List.of(
                new Player("a"), new Player("b"), new Player("c"), new Player("d"),
                new Player("e"), new Player("f"), new Player("g"), new Player("h"),
                new Player("q")
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
            Player roma = new Player("roma");
            Player tonic = new Player("tonic");
            Player pobi = new Player("pobi");

            Players players = new Players(List.of(roma, tonic, pobi));
            MockDeck mockDeck = new MockDeck(List.of(Card.of(CardPattern.DIAMOND, CardNumber.NINE),
                Card.of(CardPattern.DIAMOND, CardNumber.TEN),
                Card.of(CardPattern.DIAMOND, CardNumber.ACE),
                Card.of(CardPattern.SPADE, CardNumber.TEN)));
            players.drawAll(mockDeck);
            Dealer dealer = new Dealer();
            dealer.drawCard(mockDeck);
            ScoreResult result = players.compete(dealer);

            Assertions.assertThat(result.getPlayerScore(roma)).isEqualTo(Score.LOSE);
            Assertions.assertThat(result.getPlayerScore(tonic)).isEqualTo(Score.DRAW);
            Assertions.assertThat(result.getPlayerScore(pobi)).isEqualTo(Score.WIN);
            Assertions.assertThat(result.getDealerScoreCount(Score.LOSE)).isEqualTo(1);
            Assertions.assertThat(result.getDealerScoreCount(Score.DRAW)).isEqualTo(1);
            Assertions.assertThat(result.getDealerScoreCount(Score.WIN)).isEqualTo(1);
        }
    }

}
