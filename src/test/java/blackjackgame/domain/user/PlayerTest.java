package blackjackgame.domain.user;

import static blackjackgame.domain.Fixtures.JACK_KING_NINE_CARDS;
import static org.assertj.core.api.Assertions.assertThat;

import blackjackgame.domain.card.Card;
import blackjackgame.domain.card.CloverCard;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("플레이어는 ")
class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player(new Name("플레이어"), new Bet(5000));
    }

    @DisplayName("생성 직후엔 빈 카드패를 가지고 있다.")
    @Test
    void generatePlayerTest_noCard() {
        assertThat(player.getCards()).isEmpty();
    }

    @DisplayName("카드 한 장을 받아 패에 넣는다.")
    @Test
    void receiveCardTest() {
        List<Card> cards = player.getCards();
        int initialPlayerCardsSize = cards.size();

        player.receiveCard(CloverCard.CLOVER_FOUR);

        assertThat(cards.size()).isEqualTo(initialPlayerCardsSize + 1);
    }

    @DisplayName("합산 점수가 21을 초과하면 Bust 상태가 된다.")
    @Test
    void calculateStatusTest() {
        player.receiveCards(JACK_KING_NINE_CARDS);
        UserStatus result = player.getStatus();

        assertThat(player.getScore()).isGreaterThan(21);
        assertThat(result).isEqualTo(PlayerStatus.BUST);
    }
}
