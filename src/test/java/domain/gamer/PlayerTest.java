package domain.gamer;

import domain.card.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        Player player = new Player(name, 10000, playingCards);
        player.addCard(deck);
        assertThat(player.countCards()).isEqualTo(1);
    }

    @Test
    @DisplayName("플레이어가 생성된다")
    void constructor() {
        PlayingCards playingCards = new PlayingCards(new ArrayList<>());
        String name = "player";
        Player player = new Player(name, 10000, playingCards);
        assertThat(player).isNotNull();
    }

    @Test
    @DisplayName("플레이어가 버스트인지 확인")
    void isBust() {
        List<Card> cards = Arrays.asList(new Card(Symbol.TEN, Type.CLOVER), new Card(Symbol.TEN, Type.HEART), new Card(Symbol.NINE, Type.SPADE));
        PlayingCards playingCards = new PlayingCards(cards);
        String name = "player";
        Player player = new Player(name, 10000, playingCards);
        assertThat(player.isBust()).isTrue();
    }

    @Test
    @DisplayName("플레이어가 버스트인지 확인")
    void isNotBust() {
        PlayingCards playingCards = new PlayingCards(new ArrayList<>());
        String name = "player";
        Player player = new Player(name, 10000, playingCards);
        Deck deck = Deck.create();

        player.addCard(deck);

        assertThat(player.isBust()).isFalse();
    }
}
