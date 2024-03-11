package model.player;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import model.card.Card;
import model.card.CardNumber;
import model.card.CardShape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerTest {

//    @BeforeEach
//    void makePlayer() {
//        this.player = new Player("켬미",
//                List.of(new Card(CardShape.SPACE, CardNumber.NINE), new Card(CardShape.SPACE, CardNumber.FIVE))) {
//            @Override
//            public boolean canReceiveCard() {
//                return false;
//            }
//        };
//    }

    @DisplayName("이름이 공백이면 예외가 발생한다.")
    @Test
    void validateBlankName() {
        Assertions.assertThatThrownBy(() -> new Player("",
                List.of(new Card(CardShape.SPACE, CardNumber.NINE), new Card(CardShape.SPACE, CardNumber.FIVE))) {
            @Override
            public boolean canReceiveCard() {
                return false;
            }
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이름이 null이면 예외가 발생한다.")
    @Test
    void validateNullName() {
        Assertions.assertThatThrownBy(() -> new Player(null,
                List.of(new Card(CardShape.SPACE, CardNumber.NINE), new Card(CardShape.SPACE, CardNumber.FIVE))) {
            @Override
            public boolean canReceiveCard() {
                return false;
            }
        }).isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> createCard() {
        return Stream.of(Arguments.of(
                List.of(new Card(CardShape.SPACE, CardNumber.NINE)),
                List.of(new Card(CardShape.SPACE, CardNumber.NINE), new Card(CardShape.SPACE, CardNumber.FIVE),
                        new Card(CardShape.SPACE, CardNumber.ACE))));
    }

    @DisplayName("초기 참가자는 카드를 2장만 받아야 한다.")
    @ParameterizedTest
    @MethodSource("createCard")
    void validateCardSize(List<Card> cards) {
        Assertions.assertThatThrownBy(() -> new Player("켬미", cards) {
            @Override
            public boolean canReceiveCard() {
                return false;
            }
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("카드를 추가할 수 있다.")
    @Test
    void addCard() {
        Player player = new Player("켬미",
                List.of(new Card(CardShape.SPACE, CardNumber.NINE), new Card(CardShape.SPACE, CardNumber.FIVE))) {
            @Override
            public boolean canReceiveCard() {
                return false;
            }
        };
        Card card = new Card(CardShape.CLOVER, CardNumber.EIGHT);
        player.addCard(card);
        assertThat(player.getCards()).hasSize(3);
    }

    @DisplayName("카드 여러 개를 추가할 수 있다.")
    @Test
    void addCards() {
        Player player = new Player("켬미",
                List.of(new Card(CardShape.SPACE, CardNumber.NINE), new Card(CardShape.SPACE, CardNumber.FIVE))) {
            @Override
            public boolean canReceiveCard() {
                return false;
            }
        };
        List<Card> cards = List.of(new Card(CardShape.CLOVER, CardNumber.EIGHT),
                new Card(CardShape.CLOVER, CardNumber.FIVE));
        player.addCards(cards);
        assertThat(player.getCards()).hasSize(4);
    }

    @DisplayName("카드의 합을 계산한다.")
    @Test
    void calculateScore() {
        Player player = new Player("켬미",
                List.of(new Card(CardShape.SPACE, CardNumber.NINE), new Card(CardShape.SPACE, CardNumber.FIVE))) {
            @Override
            public boolean canReceiveCard() {
                return false;
            }
        };

        assertThat(player.calculateScore()).isEqualTo(14);
    }

    @DisplayName("Ace가 나왔을 때는 21을 초과하지 않으며 21과 가까운 수가 되도록 11이나 1을 고른다.")
    @Test
    void calculateScoreWithAce() {
        Player player = new Player("켬미",
                List.of(new Card(CardShape.SPACE, CardNumber.NINE), new Card(CardShape.SPACE, CardNumber.FIVE))) {
            @Override
            public boolean canReceiveCard() {
                return false;
            }
        };
        player.addCard(new Card(CardShape.SPACE, CardNumber.ACE));

        assertThat(player.calculateScore()).isEqualTo(15);
    }
}
