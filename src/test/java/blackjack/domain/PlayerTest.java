package blackjack.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Nested
public class PlayerTest {

    @Nested
    @DisplayName("플레이어 생성 테스트")
    class CreatePlayerTest {

        @Test
        @DisplayName("한 명의 플레이어를 이름으로 생성할 수 있다.")
        void createPlayerByName() {
            String name = "jason";
            Player player = new Player(name);

            assertThat(player).isInstanceOf(Player.class);
        }

        @ParameterizedTest
        @ValueSource(strings = {"포비_", "sa나!", "훌라627", "HULA,"})
        @DisplayName("영어 이름이 아니면 예외를 발생시킨다.")
        void createPlayerByEmptyName(String name) {
            assertThatThrownBy(() -> new Player(name))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이름은 알파벳 소문자만 입력 가능합니다.");
        }
    }

    @Nested
    @DisplayName("플레이어의 추가 배부 테스트")
    class DistributionTest {

        @Test
        @DisplayName("보유 카드가 21 미만이면 추가 배부가 가능하다")
        void canDistributeCard_Under21() {
            Player player = new Player("sana");

            Card card1 = new Card(Suit.HEART, Denomination.TWO);
            Card card2 = new Card(Suit.SPADE, Denomination.TEN);
            Card card3 = new Card(Suit.CLUB, Denomination.ACE);

            player.addCards(card1, card2, card3);

            assertThat(player.isPossibleToAdd()).isTrue();
        }

        @Test
        @DisplayName("보유 카드가 21 이상이면 추가 배부가 불가능하다")
        void canDistributeCard_Over21() {
            Player player = new Player("sana");

            Card card1 = new Card(Suit.HEART, Denomination.TWO);
            Card card2 = new Card(Suit.SPADE, Denomination.TEN);
            Card card3 = new Card(Suit.CLUB, Denomination.JACK);

            player.addCards(card1, card2, card3);

            assertThat(player.isPossibleToAdd()).isFalse();
        }
    }
}
