package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.card.Card;
import domain.card.Deck;
import domain.card.Rank;
import domain.card.Symbol;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("플레이어는 카드를 뽑을 수 있다.")
    @Test
    void hit() {
        //given
        Player player = new Player("ad");
        Card card = new Card(Symbol.HEART, Rank.FIVE);

        List<Card> cards = new ArrayList<>();
        cards.add(card);

        Deck deck = Deck.from(cards);

        //when //then
        assertThatCode(() -> player.hit(deck))
                .doesNotThrowAnyException();
    }

    @DisplayName("플레이어의 총 점수를 구할 수 있다")
    @Test
    void calculatePlayerScore() {
        //given
        Card card1 = new Card(Symbol.HEART, Rank.FIVE);
        Card card2 = new Card(Symbol.HEART, Rank.FOUR);
        Card card3 = new Card(Symbol.HEART, Rank.JACK);

        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        Deck deck = Deck.from(cards);

        Player player = new Player("ad");
        player.hit(deck);
        player.hit(deck);
        player.hit(deck);

        //when
        int score = player.getScore();

        //then
        assertThat(score).isEqualTo(19);
    }

    @DisplayName("플레이어는 버스트되면 카드를 더 뽑을 수 없다.")
    @Test
    void bustIsNotHit() {
        //given
        Card card1 = new Card(Symbol.HEART, Rank.KING);
        Card card2 = new Card(Symbol.HEART, Rank.TWO);
        Card card3 = new Card(Symbol.HEART, Rank.KING);
        Card card4 = new Card(Symbol.HEART, Rank.JACK);

        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);

        Deck deck = Deck.from(cards);

        Player player = new Player("ad");
        player.hit(deck);
        player.hit(deck);
        player.hit(deck);

        //when //then
        assertThatThrownBy(() -> player.hit(deck))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @DisplayName("플레이어는 버스트 되지 않으면 카드를 더 뽑을 수 있다.")
    @Test
    void notBustHit() {
        //given
        Card card1 = new Card(Symbol.HEART, Rank.FIVE);
        Card card2 = new Card(Symbol.COLVER, Rank.EIGHT);
        Card card3 = new Card(Symbol.SPADE, Rank.FIVE);
        Card card4 = new Card(Symbol.SPADE, Rank.FIVE);

        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);

        Deck deck = Deck.from(cards);

        Player player = new Player("ad");
        player.hit(deck);
        player.hit(deck);
        player.hit(deck);

        //when //then
        assertThatCode(() -> player.hit(deck))
                .doesNotThrowAnyException();
    }

    @DisplayName("게임이 시작되면 플레이어는 2장의 카드를 받는다.")
    @Test
    void startGameGiveDefaultCards() {
        //given
        Player player = new Player("ad");

        Card card1 = new Card(Symbol.HEART, Rank.FIVE);
        Card card2 = new Card(Symbol.COLVER, Rank.EIGHT);
        Card card3 = new Card(Symbol.SPADE, Rank.FIVE);

        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        Deck deck = Deck.from(cards);

        //when //then
        assertThatCode(() -> player.prepareGame(deck))
                .doesNotThrowAnyException();
    }

}
