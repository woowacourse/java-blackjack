package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ParticipantsTest {

    @Test
    @DisplayName("이름들을 받아 딜러를 포함하여 players를 생성한다")
    void createPlayersTest() {
        List<String> names = List.of("jamie", "boxster");

        Participants participants = Participants.from(names);

        assertThat(participants.toList()).hasSize(3);
    }

    @Test
    @DisplayName("중복인 이름이 있는 경우 예외를 반환한다")
    void duplicateNamesTest() {
        List<String> names = List.of("jamie", "jamie");

        assertThatThrownBy(() -> Participants.from(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복인 이름은 입력하실 수 없습니다.");
    }

    @Test
    @DisplayName("플레이어는 두 장씩 카드를 받는다")
    void addTwoCardsTest() {
        List<String> names = List.of("jamie", "boxster");

        Participants participants = Participants.from(names);
        participants.addTwoCards(new CardDeck());

        assertThat(participants.toList()
                               .get(0)
                               .getCards()).hasSize(2);
    }

    @Test
    @DisplayName("게임 승패를 반환한다")
    void getGameResultTest() {
        List<String> names = List.of("jamie", "boxster");

        Participants participants = Participants.from(names);
        List<Card> cards = new ArrayList<>(List.of(
                new Card(CardSuit.HEART, CardNumber.TWO), new Card(CardSuit.HEART, CardNumber.SEVEN),
                new Card(CardSuit.HEART, CardNumber.JACK), new Card(CardSuit.SPADE, CardNumber.KING),
                new Card(CardSuit.SPADE, CardNumber.KING), new Card(CardSuit.HEART,
                        CardNumber.THREE)));
        participants.addTwoCards(new CardDeck(cards));

        assertThat(participants.getResult()).containsEntry(new Player("jamie"), GameResult.LOSE);
    }
}
