package domain.participants;

import static domain.game.GameResult.DRAW;
import static domain.game.GameResult.LOSE;
import static domain.game.GameResult.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Deck;
import domain.game.GameResult;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerTest {
    @Test
    @DisplayName("특정 카드를 뽑는지 확인합니다.")
    void addCardTest() {
        //given
        Card card = Card.CLOVER_EIGHT;
        Player player = new Player(new PlayerName("코기"), new BettingAmount(10000));
        //when
        player.addCard(List.of(card));
        //then
        assertThat(player.getCards()).contains(card);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("카드 숫자의 전체 합을 계산합니다.")
    void calculateCardScoreTest(List<Card> cards, int expected) {
        //given
        Player player = new Player(new PlayerName("코기"), new BettingAmount(10000));
        player.addCard(cards);
        //when
        final int cardScore = player.calculateScore();
        //then
        assertThat(cardScore).isEqualTo(expected);
    }

    public static Stream<Arguments> calculateCardScoreTest() {
        return Stream.of(
                Arguments.of(List.of(Card.HEART_ACE, Card.CLOVER_JACK, Card.CLOVER_THREE), 14),
                Arguments.of(List.of(Card.HEART_ACE, Card.CLOVER_JACK, Card.CLOVER_QUEEN), 21),
                Arguments.of(List.of(Card.HEART_ACE, Card.CLOVER_JACK, Card.CLOVER_ACE), 12),
                Arguments.of(List.of(Card.HEART_JACK, Card.CLOVER_JACK, Card.CLOVER_THREE), 23),
                Arguments.of(List.of(Card.HEART_JACK, Card.CLOVER_JACK, Card.CLOVER_ACE, Card.HEART_ACE), 22)
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("플레이어가 카드를 더 받을 수 있는지 확인한다.")
    void canGetMoreCardTest(List<Card> cards, boolean expected) {
        //given
        Player player = new Player(new PlayerName("코기"), new BettingAmount(10000));
        player.addCard(cards);
        int playStandard = 21;
        // when
        boolean canGetMoreCard = player.isDrawable();
        // then
        assertThat(canGetMoreCard).isEqualTo(expected);
    }

    public static Stream<Arguments> canGetMoreCardTest() {
        return Stream.of(
                Arguments.of(List.of(Card.HEART_ACE, Card.CLOVER_JACK, Card.CLOVER_THREE), true),
                Arguments.of(List.of(Card.HEART_ACE, Card.CLOVER_JACK, Card.CLOVER_QUEEN), true),
                Arguments.of(List.of(Card.HEART_ACE, Card.CLOVER_JACK, Card.CLOVER_ACE), true),
                Arguments.of(List.of(Card.HEART_JACK, Card.CLOVER_JACK, Card.CLOVER_THREE), false),
                Arguments.of(List.of(Card.HEART_JACK, Card.CLOVER_JACK, Card.CLOVER_ACE, Card.HEART_ACE), false)
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("딜러 숫자가 21 이하일 때, 승무패를 계산하는지 확인합니다.")
    void decideGameResultTest(List<Card> cards, GameResult expected) {
        //given
        Player player = new Player(new PlayerName("코기"), new BettingAmount(10000));
        player.addCard(cards);
        Dealer dealer = new Dealer(new Deck(new ArrayList<>()));
        dealer.addCard(List.of(Card.HEART_JACK, Card.SPADE_QUEEN));
        //when
        GameResult gameResult = player.decideGameResult(dealer);
        // then
        assertThat(gameResult).isEqualTo(expected);
    }

    public static Stream<Arguments> decideGameResultTest() {
        return Stream.of(
                Arguments.of(List.of(Card.HEART_ACE, Card.CLOVER_JACK, Card.CLOVER_THREE), LOSE),
                Arguments.of(List.of(Card.HEART_ACE, Card.CLOVER_JACK, Card.CLOVER_QUEEN), WIN),
                Arguments.of(List.of(Card.HEART_ACE, Card.CLOVER_JACK, Card.CLOVER_ACE), LOSE),
                Arguments.of(List.of(Card.HEART_SEVEN, Card.CLOVER_JACK, Card.CLOVER_THREE), DRAW),
                Arguments.of(List.of(Card.HEART_JACK, Card.CLOVER_JACK, Card.CLOVER_ACE, Card.HEART_ACE), LOSE)
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("딜러와 플레이어가 둘 다 Bust 상황일때 무승부인지 확인합니다.")
    void decideGameResultBustTest(List<Card> cards, GameResult expected) {
        //given
        Player player = new Player(new PlayerName("코기"), new BettingAmount(10000));
        player.addCard(cards);
        Dealer dealer = new Dealer(new Deck(new ArrayList<>()));
        dealer.addCard(List.of(Card.HEART_JACK, Card.SPADE_QUEEN, Card.CLOVER_SEVEN));
        //when
        GameResult gameResult = player.decideGameResult(dealer);
        // then
        assertThat(gameResult).isEqualTo(expected);
    }

    public static Stream<Arguments> decideGameResultBustTest() {
        return Stream.of(
                Arguments.of(List.of(Card.HEART_QUEEN, Card.CLOVER_JACK, Card.CLOVER_THREE), DRAW),
                Arguments.of(List.of(Card.HEART_THREE, Card.CLOVER_JACK, Card.CLOVER_QUEEN), DRAW),
                Arguments.of(List.of(Card.HEART_TWO, Card.CLOVER_JACK, Card.CLOVER_TEN), DRAW)
        );
    }

    @Test
    @DisplayName("초기 설정 테스트")
    void setBettingAmountTest() {
        // given
        Player player = new Player(new PlayerName("a"), new BettingAmount(10000));
        // when
        BettingAmount bettingAmount = player.getBettingAmount();
        // then
        assertThat(bettingAmount.getMoney()).isEqualTo(10000);
    }

    @ParameterizedTest
    @DisplayName("같은 이름인지 정확히 확인한다.")
    @CsvSource({"a,true", "b,false"})
    void hasSameNameTest(String username, boolean expected) {
        // given
        Player player = new Player(new PlayerName("a"), new BettingAmount(10000));
        PlayerName playerName = new PlayerName(username);
        // when
        boolean isSameName = player.hasSameName(playerName);
        // then
        assertThat(isSameName).isEqualTo(expected);
    }
}