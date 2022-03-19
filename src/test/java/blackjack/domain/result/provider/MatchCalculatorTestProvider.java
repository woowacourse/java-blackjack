package blackjack.domain.result.provider;

import static blackjack.Fixture.DIAMOND_SIX;
import static blackjack.Fixture.HEART_FIVE;
import static blackjack.Fixture.HEART_SEVEN;
import static blackjack.Fixture.SPADE_ACE;
import static blackjack.Fixture.SPADE_FIVE;
import static blackjack.Fixture.SPADE_KING;
import static blackjack.Fixture.SPADE_SIX;
import static blackjack.Fixture.SPADE_TEN;
import static blackjack.Fixture.SPADE_THREE;
import static blackjack.Fixture.SPADE_TWO;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.provider.Arguments;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.result.MatchStatus;

public class MatchCalculatorTestProvider {

    public static Stream<Arguments> provideForPlayerLossIfPlayerBustTest() {
        return Stream.of(
                Arguments.of(
                        Named.of("Player : Bust(22)",
                                generatePlayer(
                                        List.of(SPADE_TEN, SPADE_KING),
                                        List.of(SPADE_TWO)
                                )
                        ),
                        Named.of("Dealer : Bust(26)",
                                generateDealer(
                                        List.of(SPADE_TEN, SPADE_SIX),
                                        List.of(SPADE_KING)
                                )
                        )
                ),
                Arguments.of(
                        Named.of("Player : Bust(22)",
                                generatePlayer(
                                        List.of(SPADE_TEN, SPADE_KING),
                                        List.of(SPADE_TWO)
                                )
                        ),
                        Named.of("Dealer : Blackjack(21)",
                                generateDealer(
                                        List.of(SPADE_TEN, SPADE_ACE),
                                        Collections.emptyList()
                                )
                        )
                )
        );
    }

    public static Stream<Arguments> provideForPlayerDrawIfBothBlackjackTest() {
        return Stream.of(
                Arguments.of(
                        Named.of("Player : Blackjack(21)",
                                generatePlayer(
                                        List.of(SPADE_ACE, SPADE_KING),
                                        Collections.emptyList()
                                )
                        ),
                        Named.of("Dealer : Blackjack(21)",
                                generateDealer(
                                        List.of(SPADE_ACE, SPADE_KING),
                                        Collections.emptyList()
                                )
                        )
                )
        );
    }

    public static Stream<Arguments> provideForPlayerWinIfOnlyPlayerBlackjackTest() {
        return Stream.of(
                Arguments.of(
                        Named.of("Player : Blackjack(21)",
                                generatePlayer(
                                        List.of(SPADE_ACE, SPADE_KING),
                                        Collections.emptyList()
                                )
                        ),
                        Named.of("Dealer : Stand(17)",
                                generateDealer(
                                        List.of(SPADE_TEN, HEART_SEVEN),
                                        Collections.emptyList()
                                )
                        )
                ),
                Arguments.of(
                        Named.of("Player : Blackjack(21)",
                                generatePlayer(
                                        List.of(SPADE_ACE, SPADE_KING),
                                        Collections.emptyList()
                                )
                        ),
                        Named.of("Dealer : Stand(21)",
                                generateDealer(
                                        List.of(SPADE_TEN, SPADE_FIVE),
                                        List.of(SPADE_SIX)
                                )
                        )
                ),
                Arguments.of(
                        Named.of("Player : Blackjack(21)",
                                generatePlayer(
                                        List.of(SPADE_ACE, SPADE_KING),
                                        Collections.emptyList()
                                )
                        ),
                        Named.of("Dealer : Bust(25)",
                                generateDealer(
                                        List.of(SPADE_TEN, SPADE_FIVE),
                                        List.of(SPADE_KING)

                                )
                        )
                )
        );
    }

    public static Stream<Arguments> provideForPlayerDrawIfBothNotBlackjackTest() {
        return Stream.of(
                Arguments.of(
                        Named.of("Player : Stand(21)",
                                generatePlayer(
                                        List.of(SPADE_TEN, HEART_FIVE),
                                        List.of(DIAMOND_SIX)
                                )
                        ),
                        Named.of("Dealer : Stand(21)",
                                generateDealer(
                                        List.of(SPADE_TEN, SPADE_FIVE),
                                        List.of(SPADE_SIX)
                                )
                        )
                ),
                Arguments.of(
                        Named.of("Player : Stand(18)",
                                generatePlayer(
                                        List.of(SPADE_TEN, SPADE_FIVE),
                                        List.of(SPADE_THREE)
                                )
                        ),
                        Named.of("Dealer : Stand(18)",
                                generateDealer(
                                        List.of(SPADE_TEN, SPADE_FIVE),
                                        List.of(SPADE_THREE)
                                )
                        )
                )
        );
    }

    public static Stream<Arguments> provideForJudgeMatchStatusIfPlayerStandTest() {
        return Stream.of(
                Arguments.of(
                        Named.of("Player : Stand(21)",
                                generatePlayer(
                                        List.of(SPADE_TEN, SPADE_FIVE),
                                        List.of(SPADE_SIX)
                                )
                        ),
                        Named.of("Dealer : Bust(25)",
                                generateDealer(
                                        List.of(SPADE_TEN, SPADE_FIVE),
                                        List.of(SPADE_TEN)
                                )
                        ),
                        MatchStatus.WIN
                ),
                Arguments.of(
                        Named.of("Player : Stand(21)",
                                generatePlayer(
                                        List.of(SPADE_TEN, SPADE_FIVE),
                                        List.of(SPADE_SIX)
                                )
                        ),
                        Named.of("Dealer : Blackjack(21)",
                                generateDealer(
                                        List.of(SPADE_TEN, SPADE_ACE),
                                        Collections.emptyList()
                                )
                        ),
                        MatchStatus.LOSS
                ),
                Arguments.of(
                        Named.of("Player : Stand(21)",
                                generatePlayer(
                                        List.of(SPADE_TEN, SPADE_FIVE),
                                        List.of(SPADE_SIX)
                                )
                        ),
                        Named.of("Dealer : Stand(20)",
                                generateDealer(
                                        List.of(SPADE_TEN, SPADE_KING),
                                        Collections.emptyList()
                                )
                        ),
                        MatchStatus.WIN
                ),
                Arguments.of(
                        Named.of("Player : Stand(16)",
                                generatePlayer(
                                        List.of(SPADE_TEN, SPADE_FIVE),
                                        List.of(SPADE_ACE)
                                )
                        ),
                        Named.of("Dealer : Stand(18)",
                                generateDealer(
                                        List.of(SPADE_TEN, SPADE_FIVE),
                                        List.of(SPADE_THREE)
                                )
                        ),
                        MatchStatus.LOSS
                )
        );
    }

    private static Player generatePlayer(final List<Card> initializedCards, List<Card> drewCards) {
        final Player player = Player.readyToPlay("name", initializedCards);
        player.betAmount(1000);
        drewCards.forEach(player::drawCard);
        if (player.isPossibleToDrawCard()) {
            player.stay();
        }
        return player;
    }

    private static Dealer generateDealer(final List<Card> initializedCards, List<Card> drewCards) {
        final Dealer dealer = Dealer.readyToPlay(initializedCards);
        drewCards.forEach(dealer::drawCard);
        return dealer;
    }

}
