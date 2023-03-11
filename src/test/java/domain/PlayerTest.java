package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.DrawnCards;
import domain.card.Type;
import domain.card.Value;
import domain.participants.Account;
import domain.participants.Name;
import domain.participants.Player;
import domain.participants.Status;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("플레이어가 카드를 뽑으면 그 카드가 뽑은 카드에 포함된다.")
    @Test
    void drawn_cards_contains_when_player_pick() {
        // given
        Card cardA = new Card(Type.CLUB, Value.KING);
        Card cardB = new Card(Type.SPADE, Value.QUEEN);

        Name name = new Name("pobi");
        Account account = new Account(10000);

        List<Card> emptyCards = new ArrayList<>();
        DrawnCards drawnCards = new DrawnCards(emptyCards);

        Player player = new Player(new Status(name, account), drawnCards);

        // when
        player.pickCard(cardA);
        player.pickCard(cardB);

        // then
        assertThat(drawnCards.getCards()).containsExactly(cardA, cardB);
    }

    @DisplayName("플레이어가 뽑은 카드의 점수 총합을 반환한다.")
    @Test
    void calculate_players_drawn_cards_score() {
        // given
        Value king = Value.KING;
        Value queen = Value.QUEEN;

        Card cardA = new Card(Type.CLUB, king);
        Card cardB = new Card(Type.SPADE, queen);

        Name name = new Name("pobi");
        Account account = new Account(10000);

        List<Card> givenCards = List.of(cardA, cardB);
        DrawnCards drawnCards = new DrawnCards(givenCards);

        Player player = new Player(new Status(name, account), drawnCards);

        // when
        int expectedCardScore = player.calculateCardScore();

        // then
        assertThat(expectedCardScore).isEqualTo(king.getScore() + queen.getScore());
    }

    @DisplayName("플레이어가 뽑은 카드들을 반환하다.")
    @Test
    void returns_player_drawn_cards() {
        // given
        Card cardA = new Card(Type.CLUB, Value.KING);
        Card cardB = new Card(Type.SPADE, Value.QUEEN);

        Name name = new Name("pobi");
        Account account = new Account(10000);

        List<Card> givenCards = List.of(cardA, cardB);
        DrawnCards drawnCards = new DrawnCards(givenCards);

        Player player = new Player(new Status(name, account), drawnCards);

        // when
        List<Card> expectedDrawnCards = player.openDrawnCards();

        // then
        assertThat(expectedDrawnCards).containsExactly(cardA, cardB);
    }

    @Test
    @DisplayName("버스트시 금액을 모두 잃는다.")
    void losing_all_account_when_player_bust() {
        // given
        int givenAccount = 1000;
        Player player = new Player(new Status(new Name("pobi"), new Account(givenAccount)),
                new DrawnCards(new ArrayList<>()));

        // when
        player.bustAccount();

        // then
        assertThat(player.getAccount()).isEqualTo(givenAccount * -1);
    }

    @Test
    @DisplayName("게임에서 승리시 상금을 얻는다.")
    void earn_money_when_player_win() {
        // given
        int givenMoney = 1000;
        int winningPrice = (int) (givenMoney * 0.5);
        Player player = new Player(new Status(new Name("pobi"), new Account(1000)), new DrawnCards(new ArrayList<>()));

        // when
        player.winGame();

        // then
        assertThat(player.getAccount()).isEqualTo(givenMoney + winningPrice);
    }

    @Test
    @DisplayName("뽑은 카드가 블랙잭인지 확인한다.")
    void check_is_black_jack() {
        // given
        Card card1 = new Card(Type.CLUB, Value.ACE);
        Card card2 = new Card(Type.SPADE, Value.KING);
        List<Card> givenCards = List.of(card1, card2);
        DrawnCards drawnCards = new DrawnCards(givenCards);

        Player player = new Player(
                new Status(
                        new Name("pobi"),
                        new Account(1000)
                ),
                drawnCards
        );

        // when
        boolean isBlackjack = player.isBlackjack();

        // then
        assertThat(isBlackjack).isTrue();
    }
}
