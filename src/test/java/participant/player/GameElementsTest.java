package participant.player;

import card.Card;
import card.CardNumber;
import card.CardPattern;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameElementsTest {

    @DisplayName("해당하는 유저가 Bust일 경우 true를 리턴한다.")
    @Test
    void isBustPlayer() {
        GameElements gameElements = new GameElements(List.of(new Card(CardNumber.JACK, CardPattern.DIA_PATTERN),
                new Card(CardNumber.JACK, CardPattern.SPADE_PATTERN),
                new Card(CardNumber.QUEEN, CardPattern.SPADE_PATTERN)), new BetMoney(3000));

        Assertions.assertThat(gameElements.isBustCard()).isTrue();
    }

    @DisplayName("해당하는 유저가 Bust가 아닐 경우 false를 리턴한다.")
    @Test
    void isNotBustPlayer() {
        GameElements gameElements = new GameElements(List.of(new Card(CardNumber.JACK, CardPattern.DIA_PATTERN)),
                new BetMoney(4000));

        Assertions.assertThat(gameElements.isBustCard()).isFalse();
    }

    @DisplayName("카드 한개를 받을 수 있다.")
    @Test
    void receiveOneCard() {
        List<Card> cards = new ArrayList<>();

        GameElements gameElements = new GameElements(cards, new BetMoney(3000));

        Card card = new Card(CardNumber.ACE, CardPattern.SPADE_PATTERN);

        gameElements.hit(card);

        Assertions.assertThat(gameElements.getCards().countCard()).isEqualTo(1);
    }

    @DisplayName("최대 카드 점수를 가져온다")
    @Test
    void getMaxCardScore() {
        GameElements gameElements = new GameElements(
                List.of(new Card(CardNumber.ACE, CardPattern.SPADE_PATTERN),
                        new Card(CardNumber.FOUR, CardPattern.SPADE_PATTERN)), new BetMoney(3000));

        Assertions.assertThat(gameElements.getCardsScore()).isEqualTo(15);
    }
}
