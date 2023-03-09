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

    @DisplayName("플레이어가 승리한 경우 - 점수가 높은 경우.")
    @Test
    void is_win_by_high_score() {
        // given
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

    @DisplayName("플레이어가 패배한 경우 - 점수가 낮은 경우.")
    @Test
    void is_lose_by_low_score() {
        // given
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

    @DisplayName("이름이 같으면 true를 반환한다.")
    @Test
    void has_same_name() {
        // given
        String givenName = "name";
        List<Card> emptyCards = new ArrayList<>();
        Player player = new Player(new Name(givenName), new DrawnCards(emptyCards));
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
        Player player = new Player(new Name(givenName), new DrawnCards(emptyCards));
        // when
        boolean actual = player.hasSameName(givenName + "any");
        // then
        assertThat(actual).isFalse();
    }
}
