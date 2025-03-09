package domain;

import static domain.fixture.BlackjackDeckTestFixture.createRandomDeck;
import static domain.fixture.BlackjackGameTestFixture.createTestGame;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIterable;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;


@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BlackjackGameTest {

    @Test
    void 플레이어_수는_1명_이상이여야_한다() {
        // given
        List<String> names = List.of();
        BlackjackDeck blackjackDeck = createRandomDeck();

        // when & then
        assertThatThrownBy(() -> new BlackjackGame(names,
                blackjackDeck, new Dealer()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("블랙잭은 1-7명만 이용하실 수 있습니다.");
    }

    @Test
    void 플레이어_수가_7명을_초과하면_예외가_발생한다() {
        // given
        List<String> names = List.of("포비1", "포비2", "포비3", "포비4", "포비5", "포비6", "포비7", "포비8", "포비9");
        BlackjackDeck deck = createRandomDeck();
        Dealer dealer = new Dealer();

        //when & then
        assertThatThrownBy(() -> new BlackjackGame(names, deck, dealer))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("블랙잭은 1-7명만 이용하실 수 있습니다.");
    }

    @Test
    void 게임을_시작하면_플레이어는_두_장의_카드를_지급_받는다() {
        // given
        List<TrumpCard> drawOrder = List.of(new TrumpCard(Suit.DIAMOND, CardValue.EIGHT),
                new TrumpCard(Suit.DIAMOND, CardValue.J),
                new TrumpCard(Suit.DIAMOND, CardValue.K), new TrumpCard(Suit.SPADE, CardValue.EIGHT),
                new TrumpCard(Suit.HEART, CardValue.EIGHT), new TrumpCard(Suit.HEART, CardValue.J),
                new TrumpCard(Suit.HEART, CardValue.K), new TrumpCard(Suit.HEART, CardValue.EIGHT));
        List<String> names = List.of("포비1", "포비2");
        BlackjackGame blackjackGame = createTestGame(names, drawOrder);

        // when
        List<TrumpCard> cards = blackjackGame.playerCards("포비1");

        //then
        List<TrumpCard> expectedCards = List.of(new TrumpCard(Suit.DIAMOND, CardValue.EIGHT),
                new TrumpCard(Suit.DIAMOND, CardValue.J));

        assertThatIterable(cards).containsExactlyElementsOf(expectedCards);
    }

    @Test
    void 게임을_시작하면_딜러는_두_장을_받고_한장을_오픈한다() {
        // given
        List<TrumpCard> drawOrder = List.of(new TrumpCard(Suit.DIAMOND, CardValue.EIGHT),
                new TrumpCard(Suit.DIAMOND, CardValue.J),
                new TrumpCard(Suit.HEART, CardValue.K), new TrumpCard(Suit.HEART, CardValue.EIGHT));
        List<String> names = List.of("포비");
        BlackjackGame blackjackGame = createTestGame(names, drawOrder);

        // when
        TrumpCard firstCard = blackjackGame.dealerCardFirst();

        //then
        TrumpCard expectedCards = new TrumpCard(Suit.HEART, CardValue.K);

        assertThat(firstCard).isEqualTo(expectedCards);
    }

    @Test
    void 플레이어_블랙잭_결과() {
        // given
        List<TrumpCard> drawOrder = List.of(new TrumpCard(Suit.DIAMOND, CardValue.EIGHT),
                new TrumpCard(Suit.DIAMOND, CardValue.J),
                new TrumpCard(Suit.HEART, CardValue.K), new TrumpCard(Suit.HEART, CardValue.NINE),
                new TrumpCard(Suit.CLOVER, CardValue.EIGHT), new TrumpCard(Suit.CLOVER, CardValue.J));
        List<String> names = List.of("포비", "루키");
        BlackjackGame blackjackGame = createTestGame(names, drawOrder);

        // when
        List<BlackjackResult> blackjackResults = blackjackGame.currentPlayerBlackjackResult();

        // then
        List<Integer> expectedPlayersCardSum = List.of(18, 19);

        assertThatIterable(blackjackResults.stream()
                .map(BlackjackResult::cardSum)
                .toList()
        ).containsExactlyElementsOf(expectedPlayersCardSum);
    }

    @Test
    void 딜러_블랙잭_현재_현황_확인() {
        // given
        List<TrumpCard> drawOrder = List.of(new TrumpCard(Suit.DIAMOND, CardValue.EIGHT),
                new TrumpCard(Suit.DIAMOND, CardValue.J),
                new TrumpCard(Suit.CLOVER, CardValue.TWO), new TrumpCard(Suit.CLOVER, CardValue.THREE));
        List<String> names = List.of("포비");
        BlackjackGame blackjackGame = createTestGame(names, drawOrder);

        // when
        BlackjackResult blackjackResult = blackjackGame.currentDealerBlackjackResult();

        // then
        int expectedDealerCardSum = 5;
        assertThat(blackjackResult.cardSum())
                .isEqualTo(expectedDealerCardSum);
    }
}
