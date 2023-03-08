package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import domain.BlackJackResult;
import domain.card.Card;
import domain.card.CardType;
import domain.card.CardValue;
import domain.card.DrawnCards;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
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

        Player player = new Player(name, drawnCards);
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

        Player player = new Player(name, drawnCards);
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

        Player player = new Player(name, drawnCards);
        // when
        List<Card> actual = player.openDrawnCards();
        // then
        assertThat(actual).containsExactly(expectedA, expectedB);
    }

    @DisplayName("플레이어가 burst라면 반드시 패배한다.")
    @Test
    void lose_if_player_burst() {
        // given
        Card cardA = new Card(CardType.CLUB, CardValue.KING);
        Card cardB = new Card(CardType.SPADE, CardValue.QUEEN);
        Card cardC = new Card(CardType.DIAMOND, CardValue.QUEEN);

        Name name = new Name("pobi");
        List<Card> givenBurstCards = List.of(cardA, cardB, cardC);
        List<Card> givenNotBurstCards = List.of(cardA, cardB);

        Player player = new Player(name, new DrawnCards(givenBurstCards));
        Dealer dealer = new Dealer(new DrawnCards(givenNotBurstCards));

        boolean expected = false;
        // when
        boolean actual = player.isWin(dealer);
        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("플레이어가 burst가 아니고 딜러가 burst라면 반드시 승리한다.")
    @Test
    void win_if_player_not_burst_and_dealer_burst() {
        // given
        Card cardA = new Card(CardType.CLUB, CardValue.KING);
        Card cardB = new Card(CardType.SPADE, CardValue.QUEEN);
        Card cardC = new Card(CardType.DIAMOND, CardValue.QUEEN);

        Name name = new Name("pobi");
        List<Card> givenBurstCards = List.of(cardA, cardB, cardC);
        List<Card> givenNotBurstCards = List.of(cardA, cardB);

        Player player = new Player(name, new DrawnCards(givenNotBurstCards));
        Dealer dealer = new Dealer(new DrawnCards(givenBurstCards));

        boolean expected = true;
        // when
        boolean actual = player.isWin(dealer);
        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("둘 다 burst가 아니라면 점수가 더 높아야 승리한다.")
    @Test
    void win_if_player_high_score() {
        // given
        Card cardA = new Card(CardType.CLUB, CardValue.KING);
        Card cardB = new Card(CardType.SPADE, CardValue.QUEEN);

        Name name = new Name("pobi");
        List<Card> givenHighScore = List.of(cardA, cardB);
        List<Card> givenLowScore = List.of(cardA);

        Player player = new Player(name, new DrawnCards(givenHighScore));
        Dealer dealer = new Dealer(new DrawnCards(givenLowScore));

        boolean expected = true;
        // when
        boolean actual = player.isWin(dealer);
        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("점수가 같다면 딜러가 승리한다.")
    @Test
    void lose_if_player_same_score_with_dealer() {
        // given
        Card cardA = new Card(CardType.CLUB, CardValue.KING);
        Card cardB = new Card(CardType.SPADE, CardValue.QUEEN);

        Name name = new Name("pobi");
        List<Card> givenCard = List.of(cardA, cardB);

        Player player = new Player(name, new DrawnCards(givenCard));
        Dealer dealer = new Dealer(new DrawnCards(givenCard));

        boolean expected = false;
        // when
        boolean actual = player.isWin(dealer);
        // then
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("BlackJack이라면 true를 반환한다.")
    @Test
    void is_blackJack() {
        // given
        List<Card> cards = new ArrayList<>();

        cards.add(new Card(CardType.SPADE, CardValue.ACE));
        cards.add(new Card(CardType.SPADE, CardValue.TEN));

        Player player = new Player(new Name("pobi"), new DrawnCards(cards));
        // when
        boolean actual = player.isBlackJack();
        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("점수가 21점이 아니라면 블랙잭이 아니다.")
    @Test
    void is_not_blackJack_by_score() {

        // given
        List<Card> cards = new ArrayList<>();

        cards.add(new Card(CardType.SPADE, CardValue.ACE));
        cards.add(new Card(CardType.SPADE, CardValue.TWO));

        Player player = new Player(new Name("pobi"), new DrawnCards(cards));
        // when
        boolean actual = player.isBlackJack();
        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("카드가 2장이 아니라면 블랙잭이 아니다.")
    @Test
    void is_not_blackJack_by_card_size() {
        // given
        List<Card> cards = new ArrayList<>();

        cards.add(new Card(CardType.SPADE, CardValue.TEN));
        cards.add(new Card(CardType.SPADE, CardValue.NINE));
        cards.add(new Card(CardType.SPADE, CardValue.TWO));

        Player player = new Player(new Name("pobi"), new DrawnCards(cards));
        // when
        boolean actual = player.isBlackJack();
        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("플레이어와 딜러가 모두 블랙잭이다.")
    @Test
    void is_each_blackJack() {
        // given
        List<Card> cards = new ArrayList<>();
        cards.add(new Card(CardType.SPADE, CardValue.ACE));
        cards.add(new Card(CardType.SPADE, CardValue.TEN));
        DrawnCards blackJackCards = new DrawnCards(cards);

        Player player = new Player(new Name("pobi"), blackJackCards);
        Dealer dealer = new Dealer(blackJackCards);
        // when
        BlackJackResult actual = player.getResult(dealer);
        // then
        assertThat(actual).isEqualTo(BlackJackResult.EACH_BLACKJACK);
    }

    @DisplayName("플레이어가 버스트이다.")
    @Test
    void is_Burst() {
        // given
        List<Card> cards = new ArrayList<>();
        Card cardA = new Card(CardType.SPADE, CardValue.TEN);
        Card cardB = new Card(CardType.HEART, CardValue.TEN);
        Card cardC = new Card(CardType.DIAMOND, CardValue.TEN);

        List<Card> burstCards = List.of(cardA, cardB, cardC);
        List<Card> notBurstCards = List.of(cardA, cardB);

        Player player = new Player(new Name("pobi"), new DrawnCards(burstCards));
        Dealer dealer = new Dealer(new DrawnCards(notBurstCards));

        // when
        BlackJackResult actual = player.getResult(dealer);
        // then
        assertThat(actual).isEqualTo(BlackJackResult.BURST);
    }

    @DisplayName("플레이어가 승리하였다 - 딜러가 버스트인 경우.")
    @Test
    void is_win_by_dealer_burst() {
        // given
        List<Card> cards = new ArrayList<>();
        Card cardA = new Card(CardType.SPADE, CardValue.TEN);
        Card cardB = new Card(CardType.HEART, CardValue.TEN);
        Card cardC = new Card(CardType.DIAMOND, CardValue.TEN);

        List<Card> burstCards = List.of(cardA, cardB, cardC);
        List<Card> notBurstCards = List.of(cardA, cardB);

        Player player = new Player(new Name("pobi"), new DrawnCards(notBurstCards));
        Dealer dealer = new Dealer(new DrawnCards(burstCards));

        // when
        BlackJackResult actual = player.getResult(dealer);
        // then
        assertThat(actual).isEqualTo(BlackJackResult.WIN);
    }

    @DisplayName("플레이거가 승리한 경우 - 점수가 높은 경우.")
    @Test
    void is_win_by_high_score() {
        // given
        List<Card> cards = new ArrayList<>();
        Card cardA = new Card(CardType.SPADE, CardValue.TEN);
        Card cardB = new Card(CardType.DIAMOND, CardValue.NINE);
        Card cardC = new Card(CardType.DIAMOND, CardValue.THREE);

        List<Card> highScoreCards = List.of(cardA, cardB);
        List<Card> lowScoreCards = List.of(cardA, cardC);

        Player player = new Player(new Name("pobi"), new DrawnCards(highScoreCards));
        Dealer dealer = new Dealer(new DrawnCards(lowScoreCards));

        // when
        BlackJackResult actual = player.getResult(dealer);
        // then
        assertThat(actual).isEqualTo(BlackJackResult.WIN);
    }

    @DisplayName("플레이거가 패배한 경우 - 점수가 낮은 경우.")
    @Test
    void is_lose_by_low_score() {
        // given
        List<Card> cards = new ArrayList<>();
        Card cardA = new Card(CardType.SPADE, CardValue.TEN);
        Card cardB = new Card(CardType.DIAMOND, CardValue.NINE);
        Card cardC = new Card(CardType.DIAMOND, CardValue.THREE);

        List<Card> highScoreCards = List.of(cardA, cardB);
        List<Card> lowScoreCards = List.of(cardA, cardC);

        Player player = new Player(new Name("pobi"), new DrawnCards(lowScoreCards));
        Dealer dealer = new Dealer(new DrawnCards(highScoreCards));

        // when
        BlackJackResult actual = player.getResult(dealer);
        // then
        assertThat(actual).isEqualTo(BlackJackResult.LOSE);
    }
}
