package blackjack.domain.card;

import blackjack.domain.Score;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CardGroupTest {

    private final Card spadeAceCard = new Card(CardShape.SPADE, CardNumber.ACE);
    private final Card spadeEightCard = new Card(CardShape.SPADE, CardNumber.EIGHT);

    private CardGroup cards;

    @BeforeEach
    void setup() {
        cards = new CardGroup(spadeAceCard, spadeEightCard);
    }

    // TODO: nested test 또는 parameterized 로 여러 경우 테스트 되도록 (ace의 갯수같이)
    @Test
    @DisplayName("카드패의 총 점수를 구하는 테스트")
    void getScoreTest() {
        final Score expectedScore = new Score(19, 2);

        Score score = cards.getScore();

        assertThat(score).isEqualTo(expectedScore);
    }

    @Test
    @DisplayName("카드패의 총 점수를 구하는 테스트 - Ace하나는 1점 처리")
    void getScoreWithAceTest() {
        final Score expectedScore = new Score(20, 3);

        cards.add(new Card(CardShape.HEART, CardNumber.ACE));
        Score score = cards.getScore();

        assertThat(score).isEqualTo(expectedScore);
    }

    @Test
    @DisplayName("카드 그룹에 카드 하나를 추가하는 기능 테스트")
    void addCardTest() {
        final Card heartTwoCard = new Card(CardShape.HEART, CardNumber.TWO);

        cards.add(heartTwoCard);

        assertThat(cards).extracting("cards", InstanceOfAssertFactories.list(Card.class))
                .contains(spadeAceCard, spadeEightCard, heartTwoCard);
    }
}
