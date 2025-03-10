package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("플레이어는 이름으로 구별된다")
    @Test
    void playerName() {
        //given
        String name1 = "ad";
        String name2 = "dogy";

        //when
        Player player1 = new Player(name1);
        Player player2 = new Player(name2);
        Player player3 = new Player(name1);

        //then
        assertThat(player1).isNotEqualTo(player2).isEqualTo(player3);
    }

//    @DisplayName("플레이어는 카드를 뽑을 수 있다.")
//    @Test
//    void hit() {
//        //given
//        deck.add(new Card(Symbol.HEART, Rank.FIVE));
//        Deck deck = Deck.from();
//
//        Player player = new Player("ad");
//
//        //when
//        player.hit(deck);
//        Deck expected = new Deck();
//        expected.add(new Card(Symbol.HEART, Rank.FIVE));
//
//        //then
//        assertThat(player.getDeck()).isEqualTo(expected);
//    }
//
//    @DisplayName("플레이어의 총 점수를 구할 수 있다")
//    @Test
//    void calculatePlayerScore() {
//        //given
//        Player player = new Player("ad");
//        Deck deck = new Deck();
//        deck.add(new Card(Symbol.HEART, Rank.FIVE));
//        deck.add(new Card(Symbol.HEART, Rank.FOUR));
//        deck.add(new Card(Symbol.HEART, Rank.JACK));
//
//        player.hit(deck);
//        player.hit(deck);
//        player.hit(deck);
//
//        //when
//        int score = player.getScore();
//        //then
//        assertThat(score).isEqualTo(19);
//    }
//
//    @DisplayName("플레이어는 버스트되면 카드를 더 뽑을 수 없다.")
//    @Test
//    void bustIsNotHit() {
//        //given
//        Player player = new Player("ad");
//        Deck deck = new Deck();
//        deck.add(new Card(Symbol.HEART, Rank.KING));
//        deck.add(new Card(Symbol.HEART, Rank.JACK));
//        deck.add(new Card(Symbol.HEART, Rank.TWO));
//
//        player.hit(deck);
//        player.hit(deck);
//        player.hit(deck);
//
//        //when //then
//        assertThatThrownBy(() -> player.hit(deck))
//                .isInstanceOf(IllegalStateException.class)
//                .hasMessageStartingWith("[ERROR]");
//    }
//
//    @DisplayName("플레이어는 버스트 되지 않으면 카드를 더 뽑을 수 있다.")
//    @Test
//    void notBustHit() {
//        //given
//        Player player = new Player("ad");
//        Deck deck = new Deck();
//
//        deck.add(new Card(Symbol.HEART, Rank.FIVE));
//        deck.add(new Card(Symbol.HEART, Rank.FIVE));
//        deck.add(new Card(Symbol.HEART, Rank.FIVE));
//        deck.add(new Card(Symbol.HEART, Rank.FIVE));
//        deck.add(new Card(Symbol.HEART, Rank.FIVE));
//
//        player.hit(deck);
//        player.hit(deck);
//        player.hit(deck);
//        player.hit(deck);
//
//        //when //then
//        assertThatCode(() -> player.hit(deck))
//                .doesNotThrowAnyException();
//    }
//
//    @DisplayName("게임이 시작되면 플레이어는 2장의 카드를 받는다.")
//    @Test
//    void startGameGiveDefaultCards() {
//        //given
//        Player player = new Player("ad");
//
//        Deck deck = new Deck();
//        Card card1 = new Card(Symbol.COLVER, Rank.ACE);
//        Card card2 = new Card(Symbol.COLVER, Rank.ACE);
//        Card card3 = new Card(Symbol.COLVER, Rank.ACE);
//        Card card4 = new Card(Symbol.COLVER, Rank.ACE);
//
//        deck.add(card1);
//        deck.add(card2);
//        deck.add(card3);
//        deck.add(card4);
//
//        //when //then
//        assertThatCode(() -> player.prepareGame(deck))
//                .doesNotThrowAnyException();
//    }

}
