package domain.user;

import domain.card.Card;
import domain.card.PlayingCards;
import domain.card.Symbol;
import domain.card.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        PlayingCards playingCards = new PlayingCards(new ArrayList<>());
        String name = "player";
        player = new Player(playingCards, name);
    }

    @Test
    @DisplayName("카드를 추가 지급 받는다")
    void addCard() {
        Card card = new Card(Symbol.QUEEN, Type.CLOVER);
        player.addCard(card);
        assertThat(player.countCards()).isEqualTo(1);
    }

    @Test
    @DisplayName("플레이어가 생성된다")
    void constructor() {
        assertThat(player).isNotNull();
    }

    @Test
    @DisplayName("플레이어가 버스트인지 확인")
    void isBust() {
        Card card1 = new Card(Symbol.QUEEN, Type.CLOVER);
        Card card2 = new Card(Symbol.KING, Type.CLOVER);
        Card card3 = new Card(Symbol.JACK, Type.CLOVER);
        player.addCard(card1);
        player.addCard(card2);
        player.addCard(card3);

        assertThat(player.isBust()).isTrue();
    }

    @Test
    @DisplayName("플레이어가 버스트인지 확인")
    void isNotBust() {
        Card card1 = new Card(Symbol.QUEEN, Type.CLOVER);
        Card card2 = new Card(Symbol.KING, Type.CLOVER);
        player.addCard(card1);
        player.addCard(card2);

        assertThat(player.isBust()).isFalse();
    }

    @Test
    @DisplayName("플레이어가 히트를 원함")
    void wantToHit() {
        assertThat(player.wantToHit("y")).isTrue();
    }

    @Test
    @DisplayName("플레이어가 스테이를 원함")
    void wantToStay() {
        assertThat(player.wantToHit("n")).isFalse();
    }

    @Test
    @DisplayName("#hit() : should add card without return")
    void hit() {
        //given
        Card card = new Card(Symbol.QUEEN, Type.SPADE);
        int defaultSizeOfCards = player.countCards();

        //when
        player.hit(card);

        //then
        assertThat(player.countCards()).isEqualTo(defaultSizeOfCards + 1);
    }
}
