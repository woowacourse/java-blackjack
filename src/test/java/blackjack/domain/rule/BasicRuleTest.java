package blackjack.domain.rule;

import static blackjack.domain.participants.HandTest.*;
import static blackjack.domain.rule.MoneyRule.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.card.Card;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Player;

public class BasicRuleTest {
    static Stream<Arguments> playerCards() {
        return Stream.of(
            Arguments.of(CARDS_21_ACE_AS_ONE, false),
            Arguments.of(CARDS_22_BUSTED, true)
        );
    }

    static Stream<Arguments> playerCards2() {
        return Stream.of(
            Arguments.of(CARDS_21_ACE_AS_ONE, false),
            Arguments.of(CARDS_21_BLACKJACK, true)
        );
    }

    static Stream<Arguments> participantCards() {
        return Stream.of(
            Arguments.of(CARDS_8, CARDS_21_BLACKJACK, WIN_BLACK_JACK),
            Arguments.of(CARDS_22_BUSTED, CARDS_21_BLACKJACK, WIN_BLACK_JACK),
            Arguments.of(CARDS_8, CARDS_21_ACE_AS_ONE, WIN),
            Arguments.of(CARDS_22_BUSTED, CARDS_21_ACE_AS_ONE, WIN),
            Arguments.of(CARDS_21_BLACKJACK, CARDS_21_BLACKJACK, DRAW),
            Arguments.of(CARDS_8, CARDS_8, DRAW),
            Arguments.of(CARDS_21_BLACKJACK, CARDS_22_BUSTED, LOSE),
            Arguments.of(CARDS_21_ACE_AS_ONE, CARDS_22_BUSTED, LOSE),
            Arguments.of(CARDS_21_ACE_AS_ONE, CARDS_8, LOSE),
            Arguments.of(CARDS_22_BUSTED, CARDS_22_BUSTED, LOSE),
            Arguments.of(CARDS_8, CARDS_22_BUSTED, LOSE)
        );
    }

    @DisplayName("해당 점수가 BUST인지 테스트")
    @ParameterizedTest
    @MethodSource("playerCards")
    void isBustedTest(List<Card> cards, boolean expected) {
        Player player = new Player("bingbong");
        cards.forEach(player::draw);
        assertThat(player.isBust()).isEqualTo(expected);
    }

    @DisplayName("해당 플레이어가 블랙잭인지 테스트")
    @ParameterizedTest
    @MethodSource("playerCards2")
    void isBlackjackTest(List<Card> cards, boolean expected) {
        Player player = new Player("bingbong");
        cards.forEach(player::draw);
        assertThat(player.isBlackjack()).isEqualTo(expected);
    }

    @DisplayName("플레이어의 블랙잭 승, 승, 무, 패 테스트")
    @ParameterizedTest
    @MethodSource("participantCards")
    void ofPlayerTest(List<Card> dealerCards, List<Card> playerCards, MoneyRule expected) {
        Dealer dealer = new Dealer();
        Player player = new Player("bingbong");

        dealerCards.forEach(dealer::draw);
        playerCards.forEach(player::draw);

        assertThat(BasicRule.of(dealer, player)).isEqualTo(expected);
    }
}