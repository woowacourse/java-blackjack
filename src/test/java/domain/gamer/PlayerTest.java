package domain.gamer;

import domain.card.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {
    @Test
    @DisplayName("카드를 추가 지급 받는다")
    void addCard() {
        PlayingCards playingCards = new PlayingCards(new ArrayList<>());
        String name = "player";
        Player player = new Player(playingCards, name);
        Card card = new Card(Symbol.QUEEN, Type.CLOVER);
        player.addCard(card);
        assertThat(player.countCards()).isEqualTo(1);
    }

    @Test
    @DisplayName("카드를 덱에서 추가 지급 받는다")
    void hit() {
        PlayingCards playingCards = new PlayingCards(new ArrayList<>());
        String name = "player";
        Player player = new Player(playingCards, name);
        Deck deck = DeckFactory.create();
        player.hit(deck);
        assertThat(player.countCards()).isEqualTo(1);
    }

    @Test
    @DisplayName("플레이어가 생성된다")
    void constructor() {
        PlayingCards playingCards = new PlayingCards(new ArrayList<>());
        String name = "player";
        Player player = new Player(playingCards, name);
        assertThat(player).isNotNull();
    }

    @Test
    @DisplayName("플레이어가 생성 시 null 값이 들어오면 예외를 반환한다.")
    void constructorWithExceptionInputNull() {
        PlayingCards playingCards = new PlayingCards(new ArrayList<>());
        String name = null;
        assertThatThrownBy(() -> new Player(playingCards, name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("값을 올바르게 입력해주세요.");
    }

    @Test
    @DisplayName("플레이어가 생성 시 빈 값이 들어오면 예외를 반환한다.")
    void constructorWithExceptionInputEmpty() {
        PlayingCards playingCards = new PlayingCards(new ArrayList<>());
        String name = "";
        assertThatThrownBy(() -> new Player(playingCards, name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("값을 올바르게 입력해주세요.");
    }

    @Test
    @DisplayName("플레이어가 생성 시 공백 문자가 들어오면 예외를 반환한다.")
    void constructorWithExceptionInputWhiteSpace() {
        PlayingCards playingCards = new PlayingCards(new ArrayList<>());
        String name = "a b";
        assertThatThrownBy(() -> new Player(playingCards, name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("공백 문자가 입력되었습니다.");
    }

    @Test
    @DisplayName("플레이어가 버스트인지 확인")
    void isBust() {
        PlayingCards playingCards = new PlayingCards(new ArrayList<>());
        String name = "player";
        Player player = new Player(playingCards, name);
        Card card1 = new Card(Symbol.QUEEN, Type.CLOVER);
        Card card2 = new Card(Symbol.KING, Type.CLOVER);
        Card card3 = new Card(Symbol.JACK, Type.CLOVER);
        player.addCard(card1);
        player.addCard(card2);
        player.addCard(card3);

        assertThat(player.isBust()).isTrue();
    }

    @Test
    @DisplayName("플레이어가 버스트가 아닌지 확인")
    void isNotBust() {
        PlayingCards playingCards = new PlayingCards(new ArrayList<>());
        String name = "player";
        Player player = new Player(playingCards, name);
        Card card1 = new Card(Symbol.QUEEN, Type.CLOVER);
        Card card2 = new Card(Symbol.KING, Type.CLOVER);

        player.addCard(card1);
        player.addCard(card2);

        assertThat(player.isNotBust()).isTrue();
    }

    @Test
    @DisplayName("플레이어가 블랙잭인지 확인")
    void isBlackJack() {
        PlayingCards playingCards = new PlayingCards(new ArrayList<>());
        String name = "player";
        Player player = new Player(playingCards, name);
        Card card1 = new Card(Symbol.QUEEN, Type.CLOVER);
        Card card2 = new Card(Symbol.ACE, Type.CLOVER);
        player.addCard(card1);
        player.addCard(card2);

        assertThat(player.isBlackJack()).isTrue();
    }

    @Test
    @DisplayName("플레이어가 블랙잭이 아닌지 확인")
    void isNotBlackJack() {
        PlayingCards playingCards = new PlayingCards(new ArrayList<>());
        String name = "player";
        Player player = new Player(playingCards, name);
        Card card1 = new Card(Symbol.QUEEN, Type.CLOVER);
        Card card2 = new Card(Symbol.KING, Type.CLOVER);

        player.addCard(card1);
        player.addCard(card2);

        assertThat(player.isNotBlackJack()).isTrue();
    }

    @Test
    @DisplayName("한 장의 카드 리스트를 반환")
    void getFirstCard() {
        PlayingCards playingCards = new PlayingCards(new ArrayList<>());
        String name = "player";
        Player player = new Player(playingCards, name);
        Card card1 = new Card(Symbol.QUEEN, Type.CLOVER);
        Card card2 = new Card(Symbol.KING, Type.CLOVER);

        player.addCard(card1);
        player.addCard(card2);

        assertThat(player.getFirstCard()).hasSize(1);
    }
}
