package blackjack.model.participant;

import static blackjack.model.card.CardFixtures.NO_SHUFFLER;
import static blackjack.model.card.CardFixtures.SPADE_ACE_CARD;
import static blackjack.model.card.CardFixtures.SPADE_FIVE_CARD;
import static blackjack.model.card.CardFixtures.SPADE_SIX_CARD;
import static blackjack.model.card.CardFixtures.SPADE_TEN_CARD;
import static blackjack.model.card.CardFixtures.createCard;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.model.MatchResult;
import blackjack.model.card.Card;
import blackjack.model.card.CardValue;
import blackjack.model.card.Deck;
import blackjack.model.card.Suit;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("딜러 테스트")
class DealerTest {

    @DisplayName("플레이어에게 카드를 한장 준다.")
    @Test
    void drawFromDeckTest() {
        // given
        List<Card> cards = List.of(SPADE_ACE_CARD);
        Deck deck = Deck.createDeckByCards(cards, NO_SHUFFLER);
        Dealer dealer = new Dealer(deck);
        Player player = new Player("포비");

        // when
        dealer.dealCard(player);

        // then
        assertThat(player.getHandCards())
                .contains(SPADE_ACE_CARD);
        assertThat(player.getHandCards())
                .hasSize(1);
    }

    @DisplayName("딜러 자신에게 카드를 한장 준다.")
    @Test
    void receiveHandTest() {
        // given
        List<Card> cards = List.of(SPADE_ACE_CARD);
        Dealer dealer = new Dealer(Deck.createDeckByCards(cards, NO_SHUFFLER));

        // when
        dealer.dealCard(dealer);

        // then
        assertThat(dealer.getHandCards())
                .contains(SPADE_ACE_CARD);
        assertThat(dealer.getHandCards())
                .hasSize(1);
    }

    @DisplayName("가진 패의 총합이 21 이상일 때 카드를 받는 경우 예외가 발생한다.")
    @Test
    void shouldThrowException_WhenReceiveCardAfterHandExceeds21() {
        // given
        List<Card> cards = List.of(SPADE_ACE_CARD, SPADE_TEN_CARD, SPADE_SIX_CARD);
        Dealer dealer = new Dealer(Deck.createDeckByCards(cards, NO_SHUFFLER));

        // when
        dealer.dealCard(dealer);
        dealer.dealCard(dealer);

        // then
        assertThatCode(() -> dealer.dealCard(dealer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("블랙잭이라 카드를 더 받을 수 없습니다.");
    }

    @DisplayName("블랙잭이면 끝난다.")
    @ParameterizedTest
    @CsvSource({
            "TEN, ACE, true",
            "TEN, FIVE, false",
    })
    void isBlackjackTest(CardValue cardValue1, CardValue cardValue2, boolean expected) {
        // given
        Card card1 = createCard(Suit.SPADES, cardValue1);
        Card card2 = createCard(Suit.SPADES, cardValue2);
        List<Card> cards = List.of(card1, card2);
        Dealer dealer = new Dealer(Deck.createDeckByCards(cards, NO_SHUFFLER));
        dealer.dealCard(dealer);
        dealer.dealCard(dealer);

        // when
        boolean isFinished = dealer.isFinished();

        // then
        assertThat(isFinished)
                .isSameAs(expected);
    }

    @DisplayName("21이 초과하면 끝난다.")
    @ParameterizedTest
    @CsvSource({
            "TEN, FIVE, TEN, true",
            "TWO, TWO, ACE, false",
    })
    void isBustTest(CardValue cardValue1, CardValue cardValue2, CardValue cardValue3, boolean expected) {
        // given
        Card card1 = createCard(Suit.SPADES, cardValue1);
        Card card2 = createCard(Suit.SPADES, cardValue2);
        Card card3 = createCard(Suit.SPADES, cardValue3);
        List<Card> cards = List.of(card1, card2, card3);
        Dealer dealer = new Dealer(Deck.createDeckByCards(cards, NO_SHUFFLER));
        dealer.dealCard(dealer);
        dealer.dealCard(dealer);
        dealer.dealCard(dealer);

        // when
        boolean isFinished = dealer.isFinished();

        // then
        assertThat(isFinished)
                .isSameAs(expected);
    }

    @DisplayName("보여줄 때 첫 번째 카드 한장을 반환한다.")
    @Test
    void getVisibleCardTest() {
        // give
        List<Card> cards = List.of(SPADE_ACE_CARD, SPADE_TEN_CARD);
        Dealer dealer = new Dealer(Deck.createDeckByCards(cards, NO_SHUFFLER));
        dealer.dealCard(dealer);
        dealer.dealCard(dealer);

        // when
        Card visibleCard = dealer.getVisibleCard();

        // then
        assertThat(visibleCard)
                .isEqualTo(SPADE_ACE_CARD);
        assertThat(visibleCard)
                .isNotEqualTo(SPADE_TEN_CARD);
    }

    @DisplayName("보여줄 때 가진 패가 없는 경우 예외가 발생한다.")
    @Test
    void shouldThrowException_WhenNoHandGetVisibleCardTest() {
        // given
        Dealer dealer = Dealer.createWithShuffledStandardDeck();

        // when, then
        assertThatCode(dealer::getVisibleCard)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("딜러가 가진 패가 없습니다.");
    }

    @DisplayName("딜러는 참가자와 비교하여 승부를 낼 수 있다.")
    @Nested
    class CompareWithTest {

        @DisplayName("참가자가 버스트인 경우 참가자가 진다.")
        @Test
        void lose_WhenPlayerBust() {
            // given
            Player player = new Player("포비");
            player.receiveCard(SPADE_TEN_CARD);
            player.receiveCard(SPADE_TEN_CARD);
            player.receiveCard(SPADE_TEN_CARD);
            Dealer dealer = new Dealer(Deck.createStandardDeck(NO_SHUFFLER));
            dealer.receiveCard(SPADE_TEN_CARD);
            dealer.receiveCard(SPADE_TEN_CARD);

            // when
            MatchResult matchResult = dealer.evaluateFromPlayerPerspective(player);

            // then
            assertThat(matchResult)
                    .isSameAs(MatchResult.LOSE);
        }

        @DisplayName("딜러만 버스트인 경우 플레이어가 이긴다.")
        @Test
        void win_WhenDealerBust() {
            // given
            Player player = new Player("포비");
            player.receiveCard(SPADE_TEN_CARD);
            player.receiveCard(SPADE_TEN_CARD);
            player.stand();
            Dealer dealer = new Dealer(Deck.createStandardDeck(NO_SHUFFLER));
            dealer.receiveCard(SPADE_TEN_CARD);
            dealer.receiveCard(SPADE_FIVE_CARD);
            dealer.receiveCard(SPADE_TEN_CARD);

            // when
            MatchResult matchResult = dealer.evaluateFromPlayerPerspective(player);

            // then
            assertThat(matchResult)
                    .isSameAs(MatchResult.WIN);
        }

        @DisplayName("둘 다 블랙잭인 경우 무승부이다.")
        @Test
        void draw_WhenAllBlackjack() {
            // given
            Player player = new Player("포비");
            player.receiveCard(SPADE_ACE_CARD);
            player.receiveCard(SPADE_TEN_CARD);
            Dealer dealer = new Dealer(Deck.createStandardDeck(NO_SHUFFLER));
            dealer.receiveCard(SPADE_ACE_CARD);
            dealer.receiveCard(SPADE_TEN_CARD);

            // when
            MatchResult matchResult = dealer.evaluateFromPlayerPerspective(player);

            // then
            assertThat(matchResult)
                    .isSameAs(MatchResult.DRAW);
        }

        @DisplayName("참가자만 블랙잭이면 이긴다.(블랙잭이다.)")
        @Test
        void win_WhenOnlyPlayerBlackjack() {
            // given
            Player player = new Player("포비");
            player.receiveCard(SPADE_ACE_CARD);
            player.receiveCard(SPADE_TEN_CARD);
            Dealer dealer = new Dealer(Deck.createStandardDeck(NO_SHUFFLER));
            dealer.receiveCard(SPADE_TEN_CARD);
            dealer.receiveCard(SPADE_TEN_CARD);

            // when
            MatchResult matchResult = dealer.evaluateFromPlayerPerspective(player);

            // then
            assertThat(matchResult)
                    .isSameAs(MatchResult.BLACKJACK);
        }

        @DisplayName("딜러만 블랙잭이면 참가자는 진다.")
        @Test
        void lose_WhenOnlyDealerBlackjack() {
            // given
            Player player = new Player("포비");
            player.receiveCard(SPADE_TEN_CARD);
            player.receiveCard(SPADE_TEN_CARD);
            player.stand();
            Dealer dealer = new Dealer(Deck.createStandardDeck(NO_SHUFFLER));
            dealer.receiveCard(SPADE_ACE_CARD);
            dealer.receiveCard(SPADE_TEN_CARD);

            // when
            MatchResult matchResult = dealer.evaluateFromPlayerPerspective(player);

            // then
            assertThat(matchResult)
                    .isSameAs(MatchResult.LOSE);
        }

        @DisplayName("모두 블랙잭이나 버스트가 아닌 경우 숫자로 승부를 낸다.")
        @ParameterizedTest
        @CsvSource({
                "KING, EIGHT, KING, KING, LOSE",
                "KING, NINE, KING, NINE, DRAW",
                "KING, KING, KING, EIGHT, WIN"
        })
        void evaluateOutcomeTest(CardValue playerCardValue1, CardValue playerCardValue2,
                                 CardValue dealerCardValue1, CardValue dealerCardValue2,
                                 MatchResult expected) {
            // given
            Player player = new Player("포비");
            player.receiveCard(createCard(Suit.SPADES, playerCardValue1));
            player.receiveCard(createCard(Suit.SPADES, playerCardValue2));
            player.stand();
            Dealer dealer = new Dealer(Deck.createStandardDeck(NO_SHUFFLER));
            dealer.receiveCard(createCard(Suit.SPADES, dealerCardValue1));
            dealer.receiveCard(createCard(Suit.SPADES, dealerCardValue2));

            // when
            MatchResult matchResult = dealer.evaluateFromPlayerPerspective(player);

            // then
            assertThat(matchResult)
                    .isSameAs(expected);
        }
    }

    @DisplayName("플레이어의 턴이 종료되지 않은 경우, 승부를 판단할 때 예외가 발생한다.")
    @Test
    void shouldThrowException_WhenPlayerTurnNotFinished() {
        // given
        Player player = new Player("포비");
        player.receiveCard(SPADE_TEN_CARD);
        player.receiveCard(SPADE_TEN_CARD);
        Dealer dealer = new Dealer(Deck.createStandardDeck(NO_SHUFFLER));
        dealer.receiveCard(SPADE_TEN_CARD);
        dealer.receiveCard(SPADE_TEN_CARD);

        // when, then
        assertThatCode(() -> dealer.evaluateFromPlayerPerspective(player))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 턴이 종료되지 않았습니다.");
    }

    @DisplayName("딜러의 턴이 종료되지 않은 경우, 승부를 판단할 때 예외가 발생한다.")
    @Test
    void shouldThrowException_WhenDealerTurnNotFinished() {
        // given
        Player player = new Player("포비");
        player.receiveCard(SPADE_TEN_CARD);
        player.receiveCard(SPADE_TEN_CARD);
        player.stand();
        Dealer dealer = new Dealer(Deck.createStandardDeck(NO_SHUFFLER));

        // when, then
        assertThatCode(() -> dealer.evaluateFromPlayerPerspective(player))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("딜러의 턴이 종료되지 않았습니다.");
    }

    @DisplayName("딜러 패의 총합을 반환한다.")
    @Test
    void getHandTest() {
        // given
        List<Card> cards = List.of(SPADE_ACE_CARD, SPADE_TEN_CARD);
        Dealer dealer = new Dealer(Deck.createDeckByCards(cards, NO_SHUFFLER));
        dealer.dealCard(dealer);
        dealer.dealCard(dealer);

        // when
        int total = dealer.getTotal();

        // then
        assertThat(total)
                .isEqualTo(21);
    }
}
