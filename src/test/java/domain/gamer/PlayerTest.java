package domain.gamer;

import domain.card.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {
    Deck deck;
    @BeforeEach
    void setUp() {
        deck = Deck.create();
    }
    @Test
    @DisplayName("카드를 추가 지급 받는다")
    void addCard() {
        PlayingCards playingCards = new PlayingCards(new ArrayList<>());
        String name = "player";
        Player player = new Player(playingCards, name);
        player.addCard(deck);
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
    @DisplayName("플레이어가 버스트인지 확인")
    void isBust() {
        PlayingCards playingCards = new PlayingCards(new ArrayList<>());
        String name = "player";
        Player player = new Player(playingCards, name);
        Card card1 = new Card(Symbol.QUEEN, Type.CLOVER);
        Card card2 = new Card(Symbol.KING, Type.CLOVER);
        Card card3 = new Card(Symbol.JACK, Type.CLOVER);
        player.addCard(deck);
        player.addCard(deck);
        player.addCard(deck);

        assertThat(player.isBust()).isTrue();
    }

    @Test
    @DisplayName("플레이어가 버스트인지 확인")
    void isNotBust() {
        PlayingCards playingCards = new PlayingCards(new ArrayList<>());
        String name = "player";
        Player player = new Player(playingCards, name);
        Deck deck = Deck.create();

        player.addCard(deck);

        assertThat(player.isBust()).isFalse();
    }
}
