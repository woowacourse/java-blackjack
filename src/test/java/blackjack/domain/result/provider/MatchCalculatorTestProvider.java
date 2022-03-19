package blackjack.domain.result.provider;

import static blackjack.Fixture.DIAMOND_ACE;
import static blackjack.Fixture.DIAMOND_KING;
import static blackjack.Fixture.DIAMOND_SIX;
import static blackjack.Fixture.DIAMOND_TEN;
import static blackjack.Fixture.DIAMOND_THREE;
import static blackjack.Fixture.DIAMOND_TWO;
import static blackjack.Fixture.HEART_ACE;
import static blackjack.Fixture.HEART_FIVE;
import static blackjack.Fixture.HEART_KING;
import static blackjack.Fixture.HEART_SEVEN;
import static blackjack.Fixture.SPADE_ACE;
import static blackjack.Fixture.SPADE_KING;
import static blackjack.Fixture.SPADE_TEN;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.ManualDeckGenerator;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.result.MatchStatus;

public class MatchCalculatorTestProvider {

    public static final List<Card> BLACKJACK_INITIAL_CARDS = List.of(
            SPADE_ACE,
            DIAMOND_KING
    );

    public static Stream<Arguments> provideForPlayerLossIfPlayerBustTest() {
        final Player bustStateOfPlayer = generatePlayer(
                List.of(
                        SPADE_KING,
                        DIAMOND_KING
                ),
                List.of(
                        HEART_KING
                )
        );
        return Stream.of(
                Arguments.of(
                        generateDealer(
                                List.of(
                                        SPADE_TEN,
                                        HEART_SEVEN
                                ),
                                List.of(
                                        DIAMOND_TWO
                                )
                        ),
                        bustStateOfPlayer
                ),
                Arguments.of(
                        generateDealer(
                                BLACKJACK_INITIAL_CARDS,
                                Collections.emptyList()
                        ),
                        bustStateOfPlayer
                )
        );
    }

    public static Stream<Arguments> provideForPlayerDrawIfBothBlackjackTest() {
        return Stream.of(
                Arguments.of(
                        generateDealer(
                                BLACKJACK_INITIAL_CARDS,
                                Collections.emptyList()
                        ),
                        generatePlayer(
                                BLACKJACK_INITIAL_CARDS,
                                Collections.emptyList()
                        )
                )
        );
    }

    public static Stream<Arguments> provideForPlayerWinIfOnlyPlayerBlackjackTest() {
        final Player blackjackStateOfPlayer = generatePlayer(
                BLACKJACK_INITIAL_CARDS,
                Collections.emptyList()
        );
        return Stream.of(
                Arguments.of(
                        generateDealer(
                                List.of(
                                        SPADE_TEN,
                                        HEART_SEVEN
                                ),
                                List.of(
                                        DIAMOND_TWO
                                )
                        ),
                        blackjackStateOfPlayer
                ),
                Arguments.of(
                        generateDealer(
                                List.of(
                                        SPADE_TEN,
                                        HEART_SEVEN
                                ),
                                List.of(
                                        DIAMOND_KING
                                )
                        ),
                        blackjackStateOfPlayer
                )
        );
    }

    public static Stream<Arguments> provideForPlayerDrawIfBothNotBlackjackTest() {
        return Stream.of(
                Arguments.of(
                        generateDealer(
                                List.of(
                                        SPADE_TEN,
                                        HEART_FIVE
                                ),
                                List.of(
                                        DIAMOND_SIX
                                )
                        ),
                        generatePlayer(
                                List.of(
                                        SPADE_TEN,
                                        HEART_FIVE
                                ),
                                List.of(
                                        DIAMOND_SIX
                                )
                        )
                ),
                Arguments.of(
                        generateDealer(
                                List.of(
                                        SPADE_TEN,
                                        HEART_FIVE
                                ),
                                List.of(
                                        DIAMOND_THREE,
                                        HEART_ACE
                                )
                        ),
                        generatePlayer(
                                List.of(
                                        SPADE_TEN,
                                        HEART_FIVE
                                ),
                                List.of(
                                        DIAMOND_THREE
                                )
                        )
                )
        );
    }

    public static Stream<Arguments> provideForJudgeMatchStatusIfPlayerStandTest() {
        return Stream.of(
                Arguments.of(
                        generateDealer(
                                List.of(
                                        SPADE_TEN,
                                        HEART_FIVE
                                ),
                                List.of(
                                        DIAMOND_TEN
                                )
                        ),
                        generatePlayer(
                                List.of(
                                        SPADE_TEN,
                                        HEART_FIVE
                                ),
                                List.of(
                                        DIAMOND_SIX
                                )
                        ),
                        MatchStatus.WIN
                ),
                Arguments.of(
                        generateDealer(
                                List.of(
                                        SPADE_TEN,
                                        HEART_FIVE
                                ),
                                List.of(
                                        DIAMOND_THREE,
                                        HEART_ACE
                                )
                        ),
                        generatePlayer(
                                List.of(
                                        SPADE_TEN,
                                        HEART_FIVE
                                ),
                                List.of(
                                        DIAMOND_ACE
                                )
                        ),
                        MatchStatus.LOSS
                )
        );
    }

    private static Dealer generateDealer(final List<Card> initializedCards, List<Card> drewCards) {
        final Deck deck = generateDeck(initializedCards, drewCards);
        final Dealer dealer = Dealer.readyToPlay(deck);
        drewCards.forEach(card -> dealer.drawCard(deck.drawCard()));
        return dealer;
    }

    private static Player generatePlayer(final List<Card> initializedCards, List<Card> drewCards) {
        final Deck deck = generateDeck(initializedCards, drewCards);
        final Player player = Player.readyToPlay("name", deck);
        player.betAmount(1000);
        drewCards.forEach(card -> player.drawCard(deck.drawCard(), true));
        return player;
    }

    private static Deck generateDeck(final List<Card> initializedCards, final List<Card> drewCards) {
        final List<Card> cards = appendCards(initializedCards, drewCards);
        final ManualDeckGenerator manualDeckGenerator = new ManualDeckGenerator();
        manualDeckGenerator.initCards(cards);
        return Deck.generate(manualDeckGenerator);
    }

    private static List<Card> appendCards(final List<Card> cards1, final List<Card> cards2) {
        final List<Card> cards = new ArrayList<>();
        cards.addAll(cards1);
        cards.addAll(cards2);
        return cards;
    }

}
