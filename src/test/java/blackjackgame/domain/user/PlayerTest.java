package blackjackgame.domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import blackjackgame.domain.card.Card;
import blackjackgame.domain.card.CloverCard;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("플레이어는 ")
class PlayerTest {
    @DisplayName("생성 직후엔 빈 카드패를 가지고 있다.")
    @Test
    void generatePlayerTest_noCard() {
        Player player = new Player(new Name("플레이어"));

        assertThat(player.cards()).isEmpty();
    }

    @DisplayName("카드 한 장을 받아 패에 넣는다.")
    @Test
    void receiveCardTest() {
        Player player = new Player(new Name("플레이어"));
        List<Card> cards = player.cards();
        int initialPlayerCardsSize = cards.size();

        player.receiveCard(CloverCard.CLOVER_FOUR);

        assertThat(cards.size()).isEqualTo(initialPlayerCardsSize + 1);
    }

    @DisplayName("합산 점수가 21을 초과하면 Bust 상태가 된다.")
    @Test
    void calculateStatusTest() {
        Player player = new Player(new Name("플레이어"));

        player.receiveCard(CloverCard.CLOVER_TEN);
        player.receiveCard(CloverCard.CLOVER_KING);
        player.receiveCard(CloverCard.CLOVER_QUEEN);
        UserStatus result = player.getStatus();

        assertThat(player.getScore()).isGreaterThan(21);
        assertThat(result).isEqualTo(PlayerStatus.BUST);
    }
}
