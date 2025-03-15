package model.participant;

import model.card.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import setupSettings.NicknameGenerator;
import setupSettings.PlayerGenerator;

import java.util.List;
import java.util.stream.Stream;

public class PlayerTest {

    Player player;

    @BeforeEach
    void setUp() {
        player = PlayerGenerator.generatePlayer();
    }

    @Test
    @DisplayName("플레이어가 생성 확인")
    void newPlayer() {

        //given
        Nickname nickname = NicknameGenerator.generateNickname();
        //when
        player = PlayerGenerator.generatePlayerBy(nickname);
        //then
        Assertions.assertThat(player.getNickname()).isEqualTo(nickname.getValue());
    }

    @Test
    @DisplayName("카드 추가 기능이 잘 작동하는 지")
    void addCardsSuccess() {

        // given
        final int amount = 2;
        final List<Card> cards = new CardDeck().pickCard(2);

        // when
        player.addCards(cards);

        // then
        Assertions.assertThat(player.getHands().size()).isEqualTo(amount);
    }

    @ParameterizedTest
    @MethodSource("createNotBustCards")
    @DisplayName("게임 진행 점수 조건이 충분한 지 : true")
    void isNotEnoughScoreConditionTrue(List<Card> cards) {
        //given
        player.addCards(cards);
        //when
        //then
        Assertions.assertThat(player.ableToDraw()).isTrue();
    }

    private static Stream<Arguments> createNotBustCards() {
        return Stream.of(
                Arguments.arguments(
                        List.of(
                                new MultiScoreCard(Suit.CLUBS, Rank.ACE),
                                new SingleScoreCard(Suit.HEARTS, Rank.FIVE),
                                new SingleScoreCard(Suit.SPADES, Rank.SEVEN)
                        )
                ),
                Arguments.arguments(
                        List.of(
                                new MultiScoreCard(Suit.CLUBS, Rank.ACE),
                                new SingleScoreCard(Suit.SPADES, Rank.FIVE),
                                new MultiScoreCard(Suit.HEARTS, Rank.ACE)
                        )
                )
        );
    }

    @Test
    @DisplayName("게임 진행 점수 조건이 충분한 지 : false")
    void isNotEnoughScoreConditionFalse() {
        //given
        List<Card> cards = List.of(
                new MultiScoreCard(Suit.CLUBS, Rank.ACE),
                new SingleScoreCard(Suit.HEARTS, Rank.KING)
        );

        for (Card card : cards) {
            System.out.println(card.getRank());
        }
        player.addCards(cards);
        //when
        //then
        Assertions.assertThat(player.ableToDraw()).isFalse();
    }
}
