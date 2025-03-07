package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardManagerTest {

    CardManager cardManager;

    @BeforeEach
    void setUp() {
        cardManager = new CardManager();
    }

    @Test
    @DisplayName("플레이어의 카드 포인트의 합을 구할 수 있다.")
    void canCalculateSumByNickname() {
        //given
        Player player = new Player(new Nickname("쿠키"));

        cardManager.addCardByNickname(player, 2);
        cardManager.addCardByNickname(player, 1);
        List<Card> cards = cardManager.findCardsByNickname(player);
        int expectedPoint = cards.stream().mapToInt(Card::getPoint).sum();

        // when
        int actualPoint = cardManager.calculateSumByNickname(player);

        // then
        assertThat(actualPoint).isEqualTo(expectedPoint);
    }
}
