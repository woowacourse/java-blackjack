package domain;

import domain.card.Card;
import domain.user.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import static domain.card.Suit.SPADE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static utils.Assistant.addCards;

class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("조이", new Money(1_000));
    }

    @ParameterizedTest(name = "점수를 계산한다")
    @CsvSource({"8,K,18", "10,5,15"})
    void test_calculateScore(String denomination1, String denomination2, int score) {
        addCards(player, denomination1, denomination2);

        assertThat(player.score()).isEqualTo(new Score(score));
    }

    @ParameterizedTest(name = "A는 무조건 플레이어에게 유리하게 계산한다")
    @CsvSource({"J,10,21", "7,3,21", "A,A,13"})
    void test_calculateScoreByAce(String denomination1, String denomination2, int score) {
        addCards(player, denomination1, denomination2, "A");

        assertThat(player.score()).isEqualTo(new Score(score));
    }

    @ParameterizedTest(name = "점수가 21을 초과하는지 확인한다")
    @CsvSource({"8,K,BUST", "10,5,PLAYING"})
    void test_bust(String denomination1, String denomination2, Status status) {
        addCards(player, denomination1, denomination2, "5");

        assertThat(player.status()).isEqualTo(status);
    }

    @DisplayName("카드를 받는다.")
    @Test
    void test_addCard() {
        addCards(player, "2");

        player.addCard(new Card(SPADE, "K"));

        assertThat(player.score()).isEqualTo(new Score(12));
    }

    @ParameterizedTest(name = "점수가 21 초과인지 확인하여 hit이 가능한지 판단한다")
    @CsvSource({"10,A,false", "10,6,true"})
    void test_canHit(String denomination1, String denomination2, boolean canHit) {
        addCards(player, denomination1, denomination2);

        assertThat(player.canHit()).isEqualTo(canHit);
    }

    @ParameterizedTest(name = "사용자의 응답에 따라 사용자의 상태는 STAY가 된다.")
    @CsvSource({"false, STAY", "true,PLAYING"})
    void test_updateStatusToStay(boolean isYes, Status status) {
        player.updateStatusToStay(isYes);

        assertThat(player.status()).isEqualTo(status);
    }
}