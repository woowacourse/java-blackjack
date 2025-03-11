package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import fixture.CardsInitializerFixture;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {


    @DisplayName("플레이어는 카드를 뽑을 수 있다.")
    @Test
    void hit() {
        //given
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardSymbol.COLVER, CardRank.EIGHT));
        CardDeck deck = new CardDeck(new CardsInitializerFixture(cards));

        Player player = new Player("ad");

        //when
        player.hit(deck);
        //then
        assertThat(player.getHand().getCards()).hasSize(1);
    }

    @DisplayName("플레이어의 총 점수를 구할 수 있다")
    @Test
    void calculatePlayerScore() {
        //given
        Player player = new Player("ad");
        Card card1 = new Card(CardSymbol.HEART, CardRank.FIVE);
        Card card2 = new Card(CardSymbol.HEART, CardRank.FOUR);
        Card card3 = new Card(CardSymbol.HEART, CardRank.JACK);

        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);

        CardDeck deck = new CardDeck(new CardsInitializerFixture(cards));

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
    void burstIsNotHit() {
        //given
        Player player = new Player("ad");
        Card card1 = new Card(CardSymbol.HEART, CardRank.TWO);
        Card card2 = new Card(CardSymbol.HEART, CardRank.KING);
        Card card3 = new Card(CardSymbol.HEART, CardRank.JACK);
        Card card4 = new Card(CardSymbol.HEART, CardRank.TWO);

        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);

        CardDeck deck = new CardDeck(new CardsInitializerFixture(cards));

        player.prepareGame(deck);
        player.hit(deck);

        //when //then
        assertThatThrownBy(() -> player.hit(deck))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageStartingWith("[ERROR]");
    }

    @DisplayName("플레이어는 버스트 되지 않으면 카드를 더 뽑을 수 있다.")
    @Test
    void notBurstHit() {
        //given
        Player player = new Player("ad");
        Card card1 = new Card(CardSymbol.HEART, CardRank.FIVE);
        Card card2 = new Card(CardSymbol.HEART, CardRank.FIVE);
        Card card3 = new Card(CardSymbol.HEART, CardRank.FIVE);
        Card card4 = new Card(CardSymbol.HEART, CardRank.FIVE);

        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);

        CardDeck deck = new CardDeck(new CardsInitializerFixture(cards));

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

        Card card1 = new Card(CardSymbol.HEART, CardRank.FIVE);
        Card card2 = new Card(CardSymbol.HEART, CardRank.FIVE);
        Card card3 = new Card(CardSymbol.HEART, CardRank.FIVE);
        Card card4 = new Card(CardSymbol.HEART, CardRank.FIVE);

        List<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);

        CardDeck deck = new CardDeck(new CardsInitializerFixture(cards));

        //when //then
        assertThatCode(() -> player.prepareGame(deck))
                .doesNotThrowAnyException();
    }

}
