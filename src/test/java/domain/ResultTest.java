package domain;

import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

public class ResultTest {
    private static final Dealer DEALER_BUST = new Dealer(BlackjackGame.DEALER_NAME);
    private static final Dealer DEALER_21 = new Dealer(BlackjackGame.DEALER_NAME);
    private static final Dealer DEALER_20 = new Dealer(BlackjackGame.DEALER_NAME);
    private static final Player PLAYER_BUST = new Player("BUST");
    private static final Player PLAYER_21 = new Player("21");
    private static final Player PLAYER_20 = new Player("20");

    static {
        List<Card> scoreBustCards = List.of(new Card(Suit.CLUB, Rank.Q), new Card(Suit.HEART, Rank.Q), new Card(Suit.DIAMOND, Rank.Q));
        List<Card> score20Cards = List.of(new Card(Suit.CLUB, Rank.Q), new Card(Suit.HEART, Rank.Q));
        List<Card> score21Cards = List.of(new Card(Suit.CLUB, Rank.Q), new Card(Suit.HEART, Rank.ACE));

        DEALER_BUST.addCards(scoreBustCards);
        DEALER_21.addCards(score21Cards);
        DEALER_20.addCards(score20Cards);
        PLAYER_BUST.addCards(scoreBustCards);
        PLAYER_21.addCards(score21Cards);
        PLAYER_20.addCards(score20Cards);
    }

    @ParameterizedTest
    @MethodSource("provideConditions")
    void 정확한_승패로_판정되어야_한다(Player player, Dealer dealer, Result result) {
        Result judgement = Result.judge(player, dealer);
        Assertions.assertEquals(judgement, result);
    }

    static Stream<Arguments> provideConditions() {
        return Stream.of(
                Arguments.of(PLAYER_21, DEALER_20, Result.WIN),
                Arguments.of(PLAYER_BUST, DEALER_21, Result.LOSE),
                Arguments.of(PLAYER_20, DEALER_21, Result.LOSE),
                Arguments.of(PLAYER_20, DEALER_20, Result.DRAW),
                Arguments.of(PLAYER_BUST, DEALER_BUST, Result.DRAW)
        );
    }


}
