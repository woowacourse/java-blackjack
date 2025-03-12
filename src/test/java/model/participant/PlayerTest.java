package model.participant;

import model.card.Card;
import model.card.CardDeck;
import model.card.Suit;
import model.card.AceRank;
import model.card.NormalRank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static util.TestCardDistributor.divideCard;

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
        final List<Card> expectedCards = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            expectedCards.add(new CardDeck().pickCard());
        }

        // when
        Player player = Player.from("pobi");
        divideCard(expectedCards, player);


        // then
        Assertions.assertThat(player.getCards()).containsAll(expectedCards);
    }

    @ParameterizedTest
    @MethodSource("createNotBustCards")
    @DisplayName("게임 진행 점수 조건이 충분한 지 : true")
    void isNotEnoughScoreConditionTrue(List<Card> cards) {
        //given

        Player player = Player.from("pobi");
        divideCard(cards, player);

        //when
        //then
        Assertions.assertThat(player.isHit()).isTrue();
    }

    private static Stream<Arguments> createNotBustCards() {
        return Stream.of(
                Arguments.arguments(
                        List.of(
                                new Card(Suit.CLUBS, AceRank.HARD_ACE),
                                new Card(Suit.HEARTS, NormalRank.FIVE),
                                new Card(Suit.SPADES, NormalRank.SEVEN)
                        )
                )
        );
    }

    @Test
    @DisplayName("게임 진행 점수 조건이 충분한 지 : false")
    void isNotEnoughScoreConditionFalse() {
        //given
        List<Card> cards = List.of(
                new Card(Suit.CLUBS, AceRank.SOFT_ACE),
                new Card(Suit.HEARTS, NormalRank.KING)
        );
        Participant player = Player.from("pobi");
        for (Card card : cards) {
            player.addCard(card);
        }
        //when
        //then
        Assertions.assertThat(player.isHit()).isFalse();
    }
}
