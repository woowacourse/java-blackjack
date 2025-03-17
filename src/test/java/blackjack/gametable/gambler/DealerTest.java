package blackjack.gametable.gambler;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.constant.TrumpRank;
import blackjack.constant.TrumpSuit;
import blackjack.gametable.card.Card;
import blackjack.gametable.card.Cards;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DealerTest {

    @Test
    void 딜러의_초기_카드를_등록한다() {
        // given
        Cards cards = createCards(
                createCard(TrumpRank.ACE, TrumpSuit.DIAMOND),
                createCard(TrumpRank.TWO, TrumpSuit.SPADE)
        );
        Dealer dealer = new Dealer();

        // when
        dealer.initializeHand(cards);

        // then
        assertThat(dealer.getCards().getSize()).isEqualTo(2);
    }

    @Test
    void 딜러의_초기_카드를_오픈한다() {
        // given
        Cards cards = createCards(
                createCard(TrumpRank.ACE, TrumpSuit.DIAMOND),
                createCard(TrumpRank.TWO, TrumpSuit.SPADE)
        );
        Dealer dealer = new Dealer();
        dealer.initializeHand(cards);

        // when
        List<Card> openedCards = dealer.openInitialCards();

        // then
        assertThat(openedCards).hasSize(1);
        assertThat(openedCards.getFirst().getRank()).isEqualTo(TrumpRank.ACE);
        assertThat(openedCards.getFirst().getSuit()).isEqualTo(TrumpSuit.DIAMOND);
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
                createCard(rank1, TrumpSuit.DIAMOND),
                createCard(rank2, TrumpSuit.CLOVER)
        );
        Dealer dealer = new Dealer();
        dealer.initializeHand(cards);

        // when
        boolean dealerShouldDrawCard = dealer.shouldDrawCard();

        // then
        assertThat(dealerShouldDrawCard).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "NINE, EIGHT, ACE, KING, THREE, FOUR, 5000",
            "NINE, EIGHT, ACE, THREE, THREE, FOUR, 30000",
            "NINE, EIGHT, ACE, KING, ACE, QUEEN, -45000",
    })
    void 플레이어들의_카드를_받아_배팅금액을_계산한다(
            TrumpRank dealerRank1, TrumpRank dealerRank2,
            TrumpRank player1Rank1, TrumpRank player1Rank2,
            TrumpRank player2Rank1, TrumpRank player2Rank2,
            int expectedBetAmount)
    {
        // given
        Cards dealerCards = createCards(
                createCard(dealerRank1, TrumpSuit.DIAMOND),
                createCard(dealerRank2, TrumpSuit.SPADE)
        );
        Cards player1Cards = createCards(
                createCard(player1Rank1, TrumpSuit.CLOVER),
                createCard(player1Rank2, TrumpSuit.DIAMOND)
        );
        Cards player2Cards = createCards(
                createCard(player2Rank1, TrumpSuit.SPADE),
                createCard(player2Rank2, TrumpSuit.DIAMOND)
        );
        Dealer dealer = new Dealer();
        Player player1 = new Player(new PlayerName("pobi"));
        Player player2 = new Player(new PlayerName("jason"));

        dealer.initializeHand(dealerCards);
        player1.initializeHand(player1Cards);
        player1.updateBetAmount(10000);
        player2.initializeHand(player2Cards);
        player2.updateBetAmount(20000);
        Players players = new Players(List.of(player1, player2));

        // when
        dealer.updateBetAmounts(players);

        // then
        assertThat(dealer.getBetAmount()).isEqualTo(expectedBetAmount);
    }

    private Cards createCards(Card... cards) {
        return Arrays.stream(cards)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Cards::new));
    }

    private Card createCard(TrumpRank rank, TrumpSuit suit) {
        return new Card(rank, suit);
    }

}
