package domain.user;

import domain.CardNumber;
import domain.CardSetting;
import domain.CardShape;
import domain.TrumpCard;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class UserTest {
    @BeforeEach
    public void setUp() {
        CardSetting.bin();
        CardSetting.initCache();
    }

    @Nested
    @DisplayName("카드 공개")
    public class OpenCard {
        @DisplayName("일반 유저는 자신이 가진 모든 카드를 공개해야 한다")
        @Test
        void test() {
            // given
            User user = new Player("수양");
            user.drawCard();
            user.drawCard();

            // when
            List<TrumpCard> cards = user.openCard();

            // then
            Assertions.assertThat(cards).hasSize(2);
        }

        @DisplayName("딜러는 자신이 가진 하나의 카드만 공개해야 한다")
        @Test
        void test2() {
            // given
            User user = new Dealer("dealer");
            user.drawCard();
            user.drawCard();

            // when
            List<TrumpCard> cards = user.openCard();

            // then
            Assertions.assertThat(cards).hasSize(1);
        }
    }

    @DisplayName("카드를 뽑았을 때 21을 넘기면 죽는다")
    @Test
    void test3() {
        // given
        User user = new User("test");
        user.getCardDeck().addTrumpCard(new TrumpCard(CardShape.DIA, CardNumber.K));
        user.getCardDeck().addTrumpCard(new TrumpCard(CardShape.DIA, CardNumber.J));
        user.getCardDeck().addTrumpCard(new TrumpCard(CardShape.DIA, CardNumber.Q));

        // when
        user.

        // then

    }
}