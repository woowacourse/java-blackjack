package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class PlayerTest {

    private final String name = "test";
    private final Card firstCard = new Card(CardShape.SPADE, CardNumber.ACE);
    private final Card secondCard = new Card(CardShape.CLOVER, CardNumber.EIGHT);
    private CardGroup initialGroup;

    @BeforeEach
    void setUp() {
        initialGroup = new CardGroup(firstCard, secondCard);
    }

    @Test
    @DisplayName("플레이어 초기화 테스트")
    void initTest() {
        final User player = new Player(name, initialGroup);

        assertSoftly(softly -> {
            softly.assertThat(player.getName()).isEqualTo(name);
            softly.assertThat(player.getCards()).containsExactly(firstCard, secondCard);
        });
    }

    @Test
    @DisplayName("유저의 점수를 계산하는 기능 테스트")
    void getScoreTest() {
        final User player = new Player(name, initialGroup);

        assertThat(player.getScore())
                .isEqualTo(firstCard.getNumber().getValue() + secondCard.getNumber().getValue());
    }

    @Test
    @DisplayName("유저가 카드를 하나 뽑는 기능 테스트")
    void drawCardTest(){
        final Card card = new Card(CardShape.HEART, CardNumber.JACK);
        final User player = new Player(name, initialGroup);
        final Deck deck = new Deck(new TestDeckGenerator(List.of(card)));

        player.drawCard(deck);

        Assertions.assertThat(player.getCards()).containsExactly(firstCard, secondCard, card);
    }
}
