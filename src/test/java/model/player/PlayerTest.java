package model.player;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import model.card.Card;
import model.card.CardNumber;
import model.card.CardShape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    private Player player;

    @BeforeEach
    void makePlayer() {
        this.player = new Player("켬미") {
            @Override
            public boolean canReceiveCard() {
                return false;
            }
        };
    }

    @DisplayName("딜러의 이름이 공백이면 예외가 발생한다.")
    @Test
    void validateBlankName() {
        Assertions.assertThatThrownBy(() -> new Player("") {
            @Override
            public boolean canReceiveCard() {
                return false;
            }
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("딜러의 이름이 null이면 예외가 발생한다.")
    @Test
    void validateNullName() {
        Assertions.assertThatThrownBy(() -> new Player(null) {
            @Override
            public boolean canReceiveCard() {
                return false;
            }
        }).isInstanceOf(IllegalArgumentException.class);
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
        List<Card> cards = List.of(new Card(CardShape.CLOVER, CardNumber.EIGHT),
                new Card(CardShape.CLOVER, CardNumber.FIVE));
        player.addCards(cards);
        assertThat(player.getCards()).hasSize(2);
    }

    @DisplayName("카드의 합을 계산한다.")
    @Test
    void calculateScore() {
        player.addCard(new Card(CardShape.CLOVER, CardNumber.EIGHT));
        player.addCard(new Card(CardShape.CLOVER, CardNumber.FIVE));
        assertThat(player.calculateScore()).isEqualTo(13);
    }

    @DisplayName("Ace가 나왔을 때는 21을 초과하지 않으며 21과 가까운 수가 되도록 11이나 1을 고른다.")
    @Test
    void calculateScoreWithAce() {
        player.addCard(new Card(CardShape.CLOVER, CardNumber.EIGHT));
        player.addCard(new Card(CardShape.CLOVER, CardNumber.FIVE));
        player.addCard(new Card(CardShape.SPACE, CardNumber.ACE));
        assertThat(player.calculateScore()).isEqualTo(14);
    }
}
