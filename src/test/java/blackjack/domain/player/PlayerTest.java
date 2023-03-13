package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Letter;
import blackjack.domain.card.Suit;
import blackjack.domain.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PlayerTest {

    static Player player;

    @BeforeEach
    void setUp() {
        player = new Player("IO", "3000",
                new ArrayList<>(List.of(Card.of(Suit.DIAMOND, Letter.EIGHT), Card.of(Suit.HEART, Letter.JACK))));
    }

    @Test
    @DisplayName("카드 한장씩 잘 받는지 테스트")
    void receiveCardSuccess() {
        Card card = Card.of(Suit.CLOVER, Letter.FIVE);

        player.hit(card);

        List<Card> cards = player.getHand().getCards();
        assertThat(cards.get(cards.size() - 1)).isEqualTo(card);
    }

    @Test
    @DisplayName("카드 새로 뽑을 수 있는지 판단 - 성공")
    void canReceiveNewCard() {
        assertThat(player.isAbleToReceive()).isTrue();
    }

    @Test
    @DisplayName("카드 새로 뽑을 수 있는지 판단 - 실패")
    void cannotReceiveNewCard() {
        player.hit(Card.of(Suit.HEART, Letter.KING));

        assertThat(player.isAbleToReceive()).isFalse();
    }
}
