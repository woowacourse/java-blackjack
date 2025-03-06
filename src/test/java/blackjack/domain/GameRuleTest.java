package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameRuleTest {
    @DisplayName("딜러가 가진 카드의 총합의 경우의 수 중에서 16보다 큰 숫자가 있다면 false 를 반환한다")
    @Test
    void test1() {
        // given
        GameRule gameRule = new GameRule();
        CardHolder cardHolder = new CardHolder() {
            @Override
            public List<Card> getAllCards() {
                return List.of();
            }

            @Override
            public void takeCard(Card newCard) {

            }

            @Override
            public List<Integer> getPossibleSums() {
                return List.of(1, 16, 19);
            }
        };

        // when
        boolean actual = gameRule.canTakeCardFor(new Dealer(cardHolder));

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("딜러가 가진 카드의 총합의 경우의 수가 모두 16 이하라면 true 를 반환한다")
    @Test
    void test2() {
        // given
        GameRule gameRule = new GameRule();
        CardHolder cardHolder = new CardHolder() {
            @Override
            public List<Card> getAllCards() {
                return List.of();
            }

            @Override
            public void takeCard(Card newCard) {

            }

            @Override
            public List<Integer> getPossibleSums() {
                return List.of(1, 3, 13);
            }
        };

        // when
        boolean actual = gameRule.canTakeCardFor(new Dealer(cardHolder));

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("플레이어의 카드 총합이 21을 넘지 않은 경우가 있다면 true를 반환한다.")
    @Test
    void test3() {
        // given
        GameRule gameRule = new GameRule();
        CardHolder cardHolder = new CardHolder() {
            @Override
            public List<Card> getAllCards() {
                return List.of();
            }

            @Override
            public void takeCard(Card newCard) {

            }

            @Override
            public List<Integer> getPossibleSums() {
                return List.of(1, 21, 100);
            }
        };

        // when
        boolean actual = gameRule.canTakeCardFor(new Player("꾹이", cardHolder));

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("플레이어의 카드 총합이 모두 21을 넘는다면 false를 반환한다")
    @Test
    void test4() {
        // given
        GameRule gameRule = new GameRule();
        CardHolder cardHolder = new CardHolder() {
            @Override
            public List<Card> getAllCards() {
                return List.of();
            }

            @Override
            public void takeCard(Card newCard) {

            }

            @Override
            public List<Integer> getPossibleSums() {
                return List.of(22, 23, 100);
            }
        };

        // when
        boolean actual = gameRule.canTakeCardFor(new Player("꾹이", cardHolder));

        // then
        assertThat(actual).isFalse();
    }
}
