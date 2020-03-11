package domain.Gamer;

import domain.Card.Card;
import domain.Card.PlayingCards;
import domain.Card.Symbol;
import domain.Card.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {
    @Test
    @DisplayName("카드를 추가 지급 받는다")
    void addCard() {
        PlayingCards playingCards = new PlayingCards(new ArrayList<>());
        String name = "player";
        Player player = new Player(name, playingCards);
        Card card = new Card(Symbol.QUEEN, Type.CLOVER);
        player.addCard(card);
        assertThat(player.countCards()).isEqualTo(1);
    }

    @Test
    @DisplayName("플레이어가 생성된다")
    void constructor() {
        PlayingCards playingCards = new PlayingCards(new ArrayList<>());
        String name = "player";
        Player player = new Player(name, playingCards);
        assertThat(player).isNotNull();
    }

    @Test
    @DisplayName("플레이어가 버스트인지 확인")
    void isBust() {
        PlayingCards playingCards = new PlayingCards(new ArrayList<>());
        String name = "player";
        Player player = new Player(name, playingCards);
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
        PlayingCards playingCards = new PlayingCards(new ArrayList<>());
        String name = "player";
        Player player = new Player(name, playingCards);
        Card card1 = new Card(Symbol.QUEEN, Type.CLOVER);
        Card card2 = new Card(Symbol.KING, Type.CLOVER);

        player.addCard(card1);
        player.addCard(card2);

        assertThat(player.isBust()).isFalse();
    }
}
