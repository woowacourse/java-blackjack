package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

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
        Card cardA = new Card(CardType.CLUB, CardValue.KING);
        Card cardB = new Card(CardType.SPADE, CardValue.QUEEN);

        Name name = new Name("pobi");
        List<Card> emptyCards = new ArrayList<>();
        DrawnCards drawnCards = new DrawnCards(emptyCards);

        Player player = new Player(name, drawnCards);

        // when
        player.drawCard(cardA);
        player.drawCard(cardB);

        // then
        assertThat(drawnCards.getCards()).containsExactly(cardA, cardB);
    }

    @DisplayName("플레이어가 뽑은 카드의 점수 총합을 반환한다.")
    @Test
    void calculate_players_drawn_cards_score() {
        // given
        CardValue king = CardValue.KING;
        CardValue queen = CardValue.QUEEN;

        Card cardA = new Card(CardType.CLUB, king);
        Card cardB = new Card(CardType.SPADE, queen);

        Name name = new Name("pobi");
        List<Card> givenCards = List.of(cardA, cardB);
        DrawnCards drawnCards = new DrawnCards(givenCards);

        Player player = new Player(name, drawnCards);

        // when
        int expectedCardScore = player.calculateScore();

        // then
        assertThat(expectedCardScore).isEqualTo(king.getScore() + queen.getScore());
    }

    @DisplayName("플레이어가 뽑은 카드들을 반환하다.")
    @Test
    void returns_player_drawn_cards() {
        // given
        Card cardA = new Card(CardType.CLUB, CardValue.KING);
        Card cardB = new Card(CardType.SPADE, CardValue.QUEEN);

        Name name = new Name("pobi");
        List<Card> givenCards = List.of(cardA, cardB);
        DrawnCards drawnCards = new DrawnCards(givenCards);

        Player player = new Player(name, drawnCards);

        // when
        List<Card> expectedDrawnCards = player.openDrawnCards();

        // then
        assertThat(expectedDrawnCards).containsExactly(cardA, cardB);
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
}
