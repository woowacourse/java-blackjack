package model.player;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import model.card.Card;
import model.card.CardNumber;
import model.card.CardShape;
import model.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    private Player player;

    @BeforeEach
    void makePlayer() {
        this.player = new Player("켬미") {
            @Override
            public boolean isDealer() {
                return false;
            }

            @Override
            public boolean receiveCard() {
                return false;
            }
        };
    }

    @DisplayName("초기 사람이 가지고 있는 카드 수는 0이다.")
    @Test
    void initCardSizeZero() {
        assertThat(player.getCards()).hasSize(0);
    }

    @DisplayName("카드를 추가할 수 있다.")
    @Test
    void addCard() {
        Card card = new Card(CardShape.CLOVER, CardNumber.EIGHT);
        player.addCard(card);
        assertThat(player.getCards()).hasSize(1);
    }

    @DisplayName("카드 여러 개를 추가할 수 있다.")
    @Test
    void addCards() {
        List<Card> cards = List.of(new Card(CardShape.CLOVER, CardNumber.EIGHT), new Card(CardShape.CLOVER, CardNumber.FIVE));
        player.addCards(cards);
        assertThat(player.getCards()).hasSize(2);
    }

    @DisplayName("카드의 합을 계산한다.")
    @Test
    void sumCardNumbers() {
        player.addCard(new Card(CardShape.CLOVER, CardNumber.EIGHT));
        player.addCard(new Card(CardShape.CLOVER, CardNumber.FIVE));
        assertThat(player.sumCardNumbers()).isEqualTo(13);
    }

    @DisplayName("Ace가 나왔을 때는 21을 초과하지 않으며 21과 가까운 수가 되도록 11이나 1을 고른다.")
    @Test
    void sumAce() {
        player.addCard(new Card(CardShape.CLOVER, CardNumber.EIGHT));
        player.addCard(new Card(CardShape.CLOVER, CardNumber.FIVE));
        player.addCard(new Card(CardShape.SPACE, CardNumber.ACE));
        assertThat(player.sumCardNumbers()).isEqualTo(14);
    }
}
