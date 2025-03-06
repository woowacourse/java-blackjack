package model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

public class PlayerTest {

    @Test
    @DisplayName("플레이어가 생성 확인")
    void newPlayer() {
        //given
        String expected = "pobi";
        //when
        Player player = Player.from("pobi");
        //then
        Assertions.assertThat(player.getNickname()).isEqualTo(expected);
    }

    @Test
    @DisplayName("카드 추가 기능이 잘 작동하는 지")
    void addCardsSuccess() {

        // given
        final int amount = 2;
        final List<Card> cards = new CardDeck().pickCard(2);

        // when
        Player player = Player.from("pobi");
        player.addCards(cards);

        // then
        Assertions.assertThat(player.getHands().size()).isEqualTo(amount);
    }

    @ParameterizedTest
    @MethodSource("createNotBustCards")
    @DisplayName("버스트 동작이 잘 작동하는지 : true")
    void bustSuccess(List<Card> cards) {
        //given
        Participant player = Player.from("pobi");
        player.addCards(cards);
        //when
        //then
        Assertions.assertThat(player.isNotBust()).isTrue();
    }

    @Test
    @DisplayName("버스트 동작이 잘 작동하는지 : false")
    void bustFailure() {
        //given
        List<Card> cards = List.of(
                new Card(SuitType.CLUBS, RankType.ACE),
                new Card(SuitType.HEARTS, RankType.ACE),
                new Card(SuitType.SPADES, RankType.KING),
                new Card(SuitType.SPADES, RankType.JACK),
                new Card(SuitType.SPADES, RankType.EIGHT)
        );
        Participant player = Player.from("pobi");
        player.addCards(cards);
        //when
        //then
        Assertions.assertThat(player.isNotBust()).isFalse();
    }

    private static Stream<Arguments> createNotBustCards() {
        return Stream.of(
                Arguments.arguments(
                        List.of(
                                new Card(SuitType.CLUBS, RankType.ACE),
                                new Card(SuitType.HEARTS, RankType.FIVE),
                                new Card(SuitType.SPADES, RankType.SEVEN)
                        )
                ),
                Arguments.arguments(
                        List.of(
                                new Card(SuitType.SPADES, RankType.NINE),
                                new Card(SuitType.CLUBS, RankType.ACE),
                                new Card(SuitType.HEARTS, RankType.ACE)
                        )
                ),
                Arguments.arguments(
                        List.of(
                                new Card(SuitType.CLUBS, RankType.ACE),
                                new Card(SuitType.SPADES, RankType.FIVE),
                                new Card(SuitType.HEARTS, RankType.ACE)
                        )
                )
        );
    }
}
