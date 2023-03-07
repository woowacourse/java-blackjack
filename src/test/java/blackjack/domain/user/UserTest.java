package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardGroup;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.TestDeckGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class UserTest {

    private final String name = "testUser";
    private final Card spadeAce = new Card(CardShape.SPADE, CardNumber.ACE);
    private final Card cloverEight = new Card(CardShape.CLOVER, CardNumber.EIGHT);
    private CardGroup initialGroup;

    @BeforeEach
    void setUp() {
        initialGroup = new CardGroup(spadeAce, cloverEight);
    }

    @Test
    @DisplayName("플레이어 초기화 테스트")
    void initTest() {
        final User user = new TestUser(initialGroup);

        assertSoftly(softly -> {
            softly.assertThat(user.getName()).isEqualTo(name);
            softly.assertThat(user.getStatus()).containsExactly(spadeAce, cloverEight);
        });
    }

    @Test
    @DisplayName("유저의 점수를 계산하는 기능 테스트")
    void getScoreTest() {
        final User user = new TestUser(initialGroup);

        assertThat(user.getScore())
                .isEqualTo(spadeAce.getNumber().getValue() + cloverEight.getNumber().getValue());
    }

    @Test
    @DisplayName("유저가 카드를 하나 뽑는 기능 테스트")
    void drawCardTest() {
        final Card heartJack = new Card(CardShape.HEART, CardNumber.JACK);
        final User user = new TestUser(initialGroup);
        final Deck deck = new Deck(new TestDeckGenerator(List.of(heartJack)));

        user.drawCard(deck);

        Assertions.assertThat(user.getStatus()).containsExactly(spadeAce, cloverEight, heartJack);
    }

    @Test
    @DisplayName("Ace의 개수를 반환하는 기능 테스트")
    void getAceCountTest() {
        final User user = new TestUser(initialGroup);

        assertThat(user.getAceCount()).isEqualTo(1);
    }

    private class TestUser extends User {

        protected TestUser(final CardGroup cardGroup) {
            super(name, cardGroup);
        }

        @Override
        protected List<Card> getInitialStatus() {
            return null;
        }
    }
}
