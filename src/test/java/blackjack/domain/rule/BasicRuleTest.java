package blackjack.domain.rule;

import static blackjack.domain.participants.HandTest.*;
import static blackjack.domain.rule.BasicRule.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.card.Card;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;

public class BasicRuleTest {
    static Stream<Arguments> players() {
        return Stream.of(
            Arguments.of(CARDS_21_ACE_AS_ONE, false),
            Arguments.of(CARDS_21_ACE_AS_ELEVEN, true)
        );
    }

    static Stream<Arguments> dealerAndPlayerCards() {
        return Stream.of(
            Arguments.of(CARDS_8, CARDS_21_ACE_AS_ELEVEN, WIN_BLACK_JACK),
            Arguments.of(CARDS_8, CARDS_21_ACE_AS_ONE, WIN),
            Arguments.of(CARDS_8, CARDS_8, DRAW),
            Arguments.of(CARDS_21_ACE_AS_ONE, CARDS_8, LOSE)
        );
    }

    @DisplayName("해당 점수가 BUST인지 테스트")
    @ParameterizedTest
    @CsvSource({"21, false", "22, true"})
    void isBustedTest(int score, boolean expected) {
        assertThat(BasicRule.isBusted(score)).isEqualTo(expected);
    }

    @DisplayName("해당 플레이어가 블랙잭인지 테스트")
    @ParameterizedTest
    @MethodSource("playerCards")
    void isBlackjackTest(List<Card> cards, boolean expected) {
        Player player = new Player("bingbong");
        cards.forEach(player::draw);
        assertThat(BasicRule.isBlackjack(player)).isEqualTo(expected);
    }

    @DisplayName("플레이어의 블랙잭 승, 승, 무, 패 테스")
    @ParameterizedTest
    @MethodSource("dealerAndPlayerCards")
    void getResultOfPlayerTest(List<Card> dealerCards, List<Card> playerCards, BasicRule expected) {
        Dealer dealer = new Dealer();
        Player player = new Player("bingbong");

        dealerCards.forEach(dealer::draw);
        playerCards.forEach(player::draw);
        assertThat(BasicRule.getResultOfPlayer(dealer, player)).isEqualTo(expected);
    }

}