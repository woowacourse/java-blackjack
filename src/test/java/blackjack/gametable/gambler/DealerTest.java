package blackjack.gametable.gambler;

import static blackjack.constant.TrumpRank.ACE;
import static blackjack.constant.TrumpRank.EIGHT;
import static blackjack.constant.TrumpRank.FIVE;
import static blackjack.constant.TrumpRank.FOUR;
import static blackjack.constant.TrumpRank.JACK;
import static blackjack.constant.TrumpRank.KING;
import static blackjack.constant.TrumpRank.NINE;
import static blackjack.constant.TrumpRank.QUEEN;
import static blackjack.constant.TrumpRank.TEN;
import static blackjack.constant.TrumpRank.THREE;
import static blackjack.constant.TrumpRank.TWO;
import static blackjack.constant.TrumpSuit.CLOVER;
import static blackjack.constant.TrumpSuit.DIAMOND;
import static blackjack.constant.TrumpSuit.SPADE;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.constant.TrumpRank;
import blackjack.constant.TrumpSuit;
import blackjack.gametable.card.Card;
import blackjack.gametable.card.Cards;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class DealerTest {

    @Test
    void 딜러의_초기_카드를_등록한다() {
        // given
        Cards cards = createCards(
                createCard(ACE, DIAMOND),
                createCard(TWO, SPADE)
        );
        Dealer dealer = new Dealer();

        // when
        dealer.drawInitializeHand(cards);

        // then
        assertThat(dealer.getCards().getSize()).isEqualTo(2);
    }

    @Test
    void 딜러의_초기_카드를_오픈한다() {
        // given
        Cards cards = createCards(
                createCard(ACE, DIAMOND),
                createCard(TWO, SPADE)
        );
        Dealer dealer = new Dealer();
        dealer.drawInitializeHand(cards);

        // when
        List<Card> openedCards = dealer.openInitialCards();

        // then
        assertThat(openedCards).hasSize(1);
        assertThat(openedCards.getFirst().getRank()).isEqualTo(ACE);
        assertThat(openedCards.getFirst().getSuit()).isEqualTo(DIAMOND);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "ACE, TWO, true",
            "ACE, FIVE, true",
            "ACE, SIX, false",
            "ACE, KING, false"
    })
    void 딜러가_카드를_추가로_받아야하는지_확인한다(TrumpRank rank1, TrumpRank rank2, boolean expected) {
        // given
        Cards cards = createCards(
                createCard(rank1, DIAMOND),
                createCard(rank2, CLOVER)
        );
        Dealer dealer = new Dealer();
        dealer.drawInitializeHand(cards);

        // when
        boolean dealerShouldDrawCard = dealer.shouldDrawCard();

        // then
        assertThat(dealerShouldDrawCard).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideTestCards")
    void 플레이어들의_카드를_받아_배팅금액을_계산한다(
            Cards dealerCards, Cards player1Cards, Cards player2Cards, int expectedBetAmount
    ) {
        // given
        Dealer dealer = new Dealer();
        Player player1 = new Player(new PlayerName("pobi"));
        Player player2 = new Player(new PlayerName("jason"));

        dealer.drawInitializeHand(dealerCards);
        player1.drawInitializeHand(player1Cards);
        player1.applyBetResult(10000);
        player2.drawInitializeHand(player2Cards);
        player2.applyBetResult(20000);
        Players players = new Players(List.of(player1, player2));

        // when
        dealer.applyBetAmounts(players);

        // then
        assertThat(dealer.getBetAmount()).isEqualTo(expectedBetAmount);
    }

    static Stream<Arguments> provideTestCards() {
        return Stream.of(
                Arguments.of(
                        createCards(createCard(NINE, DIAMOND), createCard(EIGHT, SPADE)),
                        createCards(createCard(ACE, CLOVER), createCard(KING, DIAMOND)),
                        createCards(createCard(THREE, SPADE), createCard(FOUR, DIAMOND)),
                        5000
                ),
                Arguments.of(
                        createCards(createCard(NINE, DIAMOND), createCard(EIGHT, SPADE)),
                        createCards(createCard(ACE, CLOVER), createCard(THREE, DIAMOND)),
                        createCards(createCard(THREE, SPADE), createCard(FOUR, DIAMOND)),
                        30000
                ),
                Arguments.of(
                        createCards(createCard(NINE, DIAMOND), createCard(EIGHT, SPADE)),
                        createCards(createCard(ACE, CLOVER), createCard(KING, DIAMOND)),
                        createCards(createCard(ACE, SPADE), createCard(QUEEN, DIAMOND)),
                        -45000
                ),
                Arguments.of(
                        createCards(createCard(JACK, DIAMOND), createCard(QUEEN, SPADE)),
                        createCards(createCard(FIVE, CLOVER), createCard(KING, DIAMOND)),
                        createCards(createCard(QUEEN, SPADE), createCard(TEN, DIAMOND)),
                        10000
                )
        );
    }

    private static Cards createCards(Card... cards) {
        return Arrays.stream(cards)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Cards::new));
    }

    private static Card createCard(TrumpRank rank, TrumpSuit suit) {
        return new Card(rank, suit);
    }

}
