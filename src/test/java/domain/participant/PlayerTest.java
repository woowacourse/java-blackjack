package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.BettingMoney;
import domain.card.Card;
import domain.card.CardType;
import domain.card.CardValue;
import domain.card.DrawnCards;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


class PlayerTest {

    @DisplayName("플레이어가 카드를 뽑으면 그 카드가 뽑은 카드에 포함된다.")
    @Test
    void drawn_cards_contains_when_player_pick() {
        // given
        Card expectedA = new Card(CardType.CLUB, CardValue.KING);
        Card expectedB = new Card(CardType.SPADE, CardValue.QUEEN);

        Name name = new Name("pobi");
        List<Card> emptyCards = new ArrayList<>();
        DrawnCards drawnCards = new DrawnCards(emptyCards);

        Player player = new Player(name, drawnCards, new BettingMoney(1000));
        // when
        player.drawCard(expectedA);
        player.drawCard(expectedB);

        List<Card> actual = drawnCards.getCards();
        // then
        assertThat(actual).containsExactly(expectedA, expectedB);
    }

    @DisplayName("플레이어가 뽑은 카드의 점수 총합을 반환한다.")
    @Test
    void calculate_players_drawn_cards_score() {
        // given
        CardValue expectedA = CardValue.KING;
        CardValue expectedB = CardValue.QUEEN;

        Card cardA = new Card(CardType.CLUB, expectedA);
        Card cardB = new Card(CardType.SPADE, expectedB);

        Name name = new Name("pobi");
        List<Card> givenCards = List.of(cardA, cardB);
        DrawnCards drawnCards = new DrawnCards(givenCards);

        Player player = new Player(name, drawnCards, new BettingMoney(1000));
        // when
        int actual = player.calculateScore();
        // then
        assertThat(actual).isEqualTo(expectedA.getScore() + expectedB.getScore());
    }

    @DisplayName("플레이어가 뽑은 카드들을 반환하다.")
    @Test
    void returns_player_drawn_cards() {
        // given
        Card expectedA = new Card(CardType.CLUB, CardValue.KING);
        Card expectedB = new Card(CardType.SPADE, CardValue.QUEEN);

        Name name = new Name("pobi");
        List<Card> givenCards = List.of(expectedA, expectedB);
        DrawnCards drawnCards = new DrawnCards(givenCards);

        Player player = new Player(name, drawnCards, new BettingMoney(1000));
        // when
        List<Card> actual = player.openDrawnCards();
        // then
        assertThat(actual).containsExactly(expectedA, expectedB);
    }


    @DisplayName("블랙잭을 판별할때 ")
    @Nested
    class is_blackJack {

        @DisplayName("조건을 만족하면 true를 반환한다.")
        @Test
        void return_true() {
            // given
            Card cardA = new Card(CardType.SPADE, CardValue.ACE);
            Card cardB = new Card(CardType.SPADE, CardValue.TEN);

            DrawnCards blackJackCards = new DrawnCards(List.of(cardA, cardB));
            Player player = new Player(new Name("pobi"), blackJackCards, new BettingMoney(1000));
            // when
            boolean actual = player.isBlackJack();
            // then
            assertThat(actual).isTrue();
        }

        @DisplayName("점수가 21점이 아니라면 블랙잭이 아니다.")
        @Test
        void return_false_by_score() {
            // given
            Card cardA = new Card(CardType.SPADE, CardValue.TWO);
            Card cardB = new Card(CardType.SPADE, CardValue.TEN);

            DrawnCards notBlackJackCards = new DrawnCards(List.of(cardA, cardB));
            Player player = new Player(new Name("pobi"), notBlackJackCards, new BettingMoney(1000));
            // when
            boolean actual = player.isBlackJack();
            // then
            assertThat(actual).isFalse();
        }

        @DisplayName("카드가 2장이 아니라면 블랙잭이 아니다.")
        @Test
        void return_false_by_card_size() {
            // given
            Card cardA = new Card(CardType.SPADE, CardValue.TWO);
            Card cardB = new Card(CardType.SPADE, CardValue.TEN);
            Card cardC = new Card(CardType.SPADE, CardValue.NINE);

            DrawnCards notBlackJackCards = new DrawnCards(List.of(cardA, cardB, cardC));
            Player player = new Player(new Name("pobi"), notBlackJackCards, new BettingMoney(1000));
            // when
            boolean actual = player.isBlackJack();
            // then
            assertThat(actual).isFalse();
        }
    }

    @DisplayName("수익금은 플레이어가")
    @Nested
    class result {

        @DisplayName("블랙잭이면 배팅금액의 1.5배이다.")
        @Test
        void is_blackJack() {
            // given
            int givenMoney = 1000;
            int expected =  (int) (givenMoney * 1.5);
            BettingMoney bettingMoney = new BettingMoney(givenMoney);
            Card cardA = new Card(CardType.SPADE, CardValue.ACE);
            Card cardB = new Card(CardType.SPADE, CardValue.TEN);
            Card cardC = new Card(CardType.SPADE, CardValue.TWO);

            DrawnCards blackJackCards = new DrawnCards(List.of(cardA, cardB));
            DrawnCards notBlackJackCards = new DrawnCards(List.of(cardA, cardB, cardC));

            Player player = new Player(new Name("pobi"), blackJackCards, bettingMoney);
            Dealer dealer = new Dealer(notBlackJackCards);

            // when
            int actual = player.calculatePrize(dealer);
            // then
            assertThat(actual).isEqualTo(expected);
        }

        @DisplayName("블랙잭이고 딜러도 블랙잭이면 수익은 0원이다.")
        @Test
        void is_each_blackJack() {
            // given
            int givenMoney = 1000;
            int expected =  0;

            Card cardA = new Card(CardType.SPADE, CardValue.ACE);
            Card cardB = new Card(CardType.SPADE, CardValue.TEN);

            DrawnCards blackJackCards = new DrawnCards(List.of(cardA, cardB));

            Player player = new Player(new Name("pobi"), blackJackCards, new BettingMoney(givenMoney));
            Dealer dealer = new Dealer(blackJackCards);
            // when
            int actual = player.calculatePrize(dealer);
            // then
            assertThat(actual).isEqualTo(expected);
        }

        @DisplayName("버스트이면 배팅 금액의 * -1 이다.")
        @Test
        void is_Burst() {
            // given
            int givenMoney = 1000;
            int expected =  givenMoney * -1;

            Card cardA = new Card(CardType.SPADE, CardValue.TEN);
            Card cardB = new Card(CardType.HEART, CardValue.TEN);
            Card cardC = new Card(CardType.DIAMOND, CardValue.TEN);

            DrawnCards burstCards = new DrawnCards(List.of(cardA, cardB, cardC));
            DrawnCards notBurstCards = new DrawnCards(List.of(cardA, cardB));

            Player player = new Player(new Name("pobi"), burstCards, new BettingMoney(givenMoney));
            Dealer dealer = new Dealer(notBurstCards);
            // when
            int actual = player.calculatePrize(dealer);
            // then
            assertThat(actual).isEqualTo(expected);
        }

        @DisplayName("승리하면 배팅 금액과 동일하다 - 딜러가 버스트인 경우")
        @Test
        void is_win_by_dealer_burst() {
            // given
            int givenMoney = 1000;
            int expected =  givenMoney;

            Card cardA = new Card(CardType.SPADE, CardValue.TEN);
            Card cardB = new Card(CardType.HEART, CardValue.TEN);
            Card cardC = new Card(CardType.DIAMOND, CardValue.TEN);

            DrawnCards burstCards = new DrawnCards(List.of(cardA, cardB, cardC));
            DrawnCards notBurstCards = new DrawnCards(List.of(cardA, cardB));

            Player player = new Player(new Name("pobi"), notBurstCards, new BettingMoney(givenMoney));
            Dealer dealer = new Dealer(burstCards);

            // when
            int actual = player.calculatePrize(dealer);
            // then
            assertThat(actual).isEqualTo(expected);
        }

        @DisplayName("승리하면 배팅 금액과 동일하다 - 점수가 높은 경우.")
        @Test
        void is_win_by_high_score() {
            // given
            int givenMoney = 1000;
            int expected =  givenMoney;

            Card cardA = new Card(CardType.SPADE, CardValue.TEN);
            Card cardB = new Card(CardType.DIAMOND, CardValue.NINE);
            Card cardC = new Card(CardType.DIAMOND, CardValue.THREE);

            DrawnCards highScoreCards = new DrawnCards(List.of(cardA, cardB));
            DrawnCards lowScoreCards = new DrawnCards(List.of(cardA, cardC));

            Player player = new Player(new Name("pobi"), highScoreCards, new BettingMoney(givenMoney));
            Dealer dealer = new Dealer(lowScoreCards);

            // when
            int actual = player.calculatePrize(dealer);
            // then
            assertThat(actual).isEqualTo(expected);
        }

        @DisplayName("패배하면 배팅 금액의 * -1 이다.")
        @Test
        void is_lose_by_low_score() {
            // given
            int givenMoney = 1000;
            int expected =  givenMoney * -1;

            Card cardA = new Card(CardType.SPADE, CardValue.TEN);
            Card cardB = new Card(CardType.DIAMOND, CardValue.NINE);
            Card cardC = new Card(CardType.DIAMOND, CardValue.THREE);

            DrawnCards highScoreCards = new DrawnCards(List.of(cardA, cardB));
            DrawnCards lowScoreCards = new DrawnCards(List.of(cardA, cardC));

            Player player = new Player(new Name("pobi"), lowScoreCards, new BettingMoney(givenMoney));
            Dealer dealer = new Dealer(highScoreCards);
            // when
            int actual = player.calculatePrize(dealer);
            // then
            assertThat(actual).isEqualTo(expected);
        }
    }

    @DisplayName("이름이 같으면 true를 반환한다.")
    @Test
    void has_same_name() {
        // given
        String givenName = "name";
        List<Card> emptyCards = new ArrayList<>();
        Player player = new Player(new Name(givenName), new DrawnCards(emptyCards), new BettingMoney(1000));
        // when
        boolean actual = player.hasSameName(givenName);
        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("이름이 다르면 false 반환한다.")
    @Test
    void has_not_same_name() {
        // given
        String givenName = "name";
        List<Card> emptyCards = new ArrayList<>();
        Player player = new Player(new Name(givenName), new DrawnCards(emptyCards), new BettingMoney(1000));
        // when
        boolean actual = player.hasSameName(givenName + "any");
        // then
        assertThat(actual).isFalse();
    }
}
