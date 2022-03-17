package blackjack.domain.result.provider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardPattern;
import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.ManualDeckGenerator;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.result.MatchStatus;

public class MatchCalculatorTestProvider {

    public static final List<Card> BLACKJACK_INITIAL_CARDS = List.of(
            new Card(CardNumber.ACE, CardPattern.SPADE),
            new Card(CardNumber.KING, CardPattern.DIAMOND)
    );

    public static Stream<Arguments> provideForPlayerLossIfPlayerBustTest() {
        final Player bustStateOfPlayer = generatePlayer(
                List.of(
                        new Card(CardNumber.KING, CardPattern.SPADE),
                        new Card(CardNumber.KING, CardPattern.DIAMOND)
                ),
                List.of(
                        new Card(CardNumber.KING, CardPattern.HEART)
                )
        );
        return Stream.of(
                Arguments.of(
                        generateDealer(
                                List.of(
                                        new Card(CardNumber.TEN, CardPattern.SPADE),
                                        new Card(CardNumber.SEVEN, CardPattern.HEART)
                                ),
                                List.of(
                                        new Card(CardNumber.TWO, CardPattern.DIAMOND)
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
                                        new Card(CardNumber.TEN, CardPattern.SPADE),
                                        new Card(CardNumber.SEVEN, CardPattern.HEART)
                                ),
                                List.of(
                                        new Card(CardNumber.TWO, CardPattern.DIAMOND)
                                )
                        ),
                        blackjackStateOfPlayer
                ),
                Arguments.of(
                        generateDealer(
                                List.of(
                                        new Card(CardNumber.TEN, CardPattern.SPADE),
                                        new Card(CardNumber.SEVEN, CardPattern.HEART)
                                ),
                                List.of(
                                        new Card(CardNumber.KING, CardPattern.DIAMOND)
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
                                        new Card(CardNumber.TEN, CardPattern.SPADE),
                                        new Card(CardNumber.FIVE, CardPattern.HEART)
                                ),
                                List.of(
                                        new Card(CardNumber.SIX, CardPattern.DIAMOND)
                                )
                        ),
                        generatePlayer(
                                List.of(
                                        new Card(CardNumber.TEN, CardPattern.SPADE),
                                        new Card(CardNumber.FIVE, CardPattern.HEART)
                                ),
                                List.of(
                                        new Card(CardNumber.SIX, CardPattern.DIAMOND)
                                )
                        )
                ),
                Arguments.of(
                        generateDealer(
                                List.of(
                                        new Card(CardNumber.TEN, CardPattern.SPADE),
                                        new Card(CardNumber.FIVE, CardPattern.HEART)
                                ),
                                List.of(
                                        new Card(CardNumber.THREE, CardPattern.DIAMOND),
                                        new Card(CardNumber.ACE, CardPattern.HEART)
                                )
                        ),
                        generatePlayer(
                                List.of(
                                        new Card(CardNumber.TEN, CardPattern.SPADE),
                                        new Card(CardNumber.FIVE, CardPattern.HEART)
                                ),
                                List.of(
                                        new Card(CardNumber.THREE, CardPattern.DIAMOND)
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
                                        new Card(CardNumber.TEN, CardPattern.SPADE),
                                        new Card(CardNumber.FIVE, CardPattern.HEART)
                                ),
                                List.of(
                                        new Card(CardNumber.TEN, CardPattern.DIAMOND)
                                )
                        ),
                        generatePlayer(
                                List.of(
                                        new Card(CardNumber.TEN, CardPattern.SPADE),
                                        new Card(CardNumber.FIVE, CardPattern.HEART)
                                ),
                                List.of(
                                        new Card(CardNumber.SIX, CardPattern.DIAMOND)
                                )
                        ),
                        MatchStatus.WIN
                ),
                Arguments.of(
                        generateDealer(
                                List.of(
                                        new Card(CardNumber.TEN, CardPattern.SPADE),
                                        new Card(CardNumber.FIVE, CardPattern.HEART)
                                ),
                                List.of(
                                        new Card(CardNumber.THREE, CardPattern.DIAMOND),
                                        new Card(CardNumber.ACE, CardPattern.HEART)
                                )
                        ),
                        generatePlayer(
                                List.of(
                                        new Card(CardNumber.TEN, CardPattern.SPADE),
                                        new Card(CardNumber.FIVE, CardPattern.HEART)
                                ),
                                List.of(
                                        new Card(CardNumber.ACE, CardPattern.DIAMOND)
                                )
                        ),
                        MatchStatus.LOSS
                )
        );
    }

    private static Dealer generateDealer(final List<Card> initializedCards, List<Card> drewCards) {
        final Deck deck = generateDeck(initializedCards, drewCards);
        final Dealer dealer = Dealer.readyToPlay(deck);
        drewCards.forEach(card -> dealer.drawCard(deck));
        return dealer;
    }

    private static Player generatePlayer(final List<Card> initializedCards, List<Card> drewCards) {
        final Deck deck = generateDeck(initializedCards, drewCards);
        final Player player = Player.readyToPlay("name", deck);
        player.betAmount(1000);
        drewCards.forEach(card -> player.drawCard(deck, true));
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
