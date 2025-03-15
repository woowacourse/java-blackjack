package blackjack.domain.game;

import static blackjack.domain.card.CardType.ACE;
import static blackjack.domain.card.CardType.EIGHT;
import static blackjack.domain.card.CardType.FIVE;
import static blackjack.domain.card.CardType.KING;
import static blackjack.domain.card.CardType.NINE;
import static blackjack.domain.card.CardType.SIX;
import static blackjack.domain.card.CardType.TEN;
import static blackjack.domain.fixture.CardFixture.createCards;
import static blackjack.domain.gambler.Dealer.DEALER_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.CardType;
import blackjack.domain.card.Shuffler;
import blackjack.domain.fake.TestShuffler;
import blackjack.domain.gambler.Name;
import blackjack.domain.gambler.Names;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RoundTest {
    private final Shuffler shuffler = new TestShuffler();
    private final Name name = new Name("라젤");

    @DisplayName("특정 플레이어에게 지정한 장 수의 카드를 지급한다")
    @Test
    void distributeCardsTest() {
        // given
        Card card1 = new Card(CardShape.CLOVER, TEN);
        Card card2 = new Card(CardShape.HEART, EIGHT);
        CardDeck cardDeck = new CardDeck(List.of(card1, card2), shuffler);

        Names playerNames = new Names(List.of(name));
        Round round = new Round(cardDeck, playerNames);

        // when
        round.distributeCards(name, 2);

        // then
        assertAll(
            () -> assertThat(round.getCards(name)).contains(card1, card2),
            () -> assertThat(round.getScore(name)).isEqualTo(18)
        );
    }

    @DisplayName("게임 시작 시, 플레이어와 딜러에게 2장씩 카드를 지급한다")
    @Test
    void distributeInitialCardsTest() {
        // given
        Card card1 = new Card(CardShape.CLOVER, FIVE);
        Card card2 = new Card(CardShape.HEART, ACE);
        Card card3 = new Card(CardShape.CLOVER, TEN);
        Card card4 = new Card(CardShape.HEART, KING);
        CardDeck cardDeck = new CardDeck(List.of(card1, card2, card3, card4), shuffler);

        Names playerNames = new Names(List.of(name));
        Round round = new Round(cardDeck, playerNames);

        // when
        round.distributeInitialCards();

        // then
        assertAll(
            () -> assertThat(round.getScore(name)).isEqualTo(20),
            () -> assertThat(round.getCards(name)).hasSize(2),
            () -> assertThat(round.getCards(name)).contains(card3, card4),
            () -> assertThat(round.getScore(DEALER_NAME)).isEqualTo(16),
            () -> assertThat(round.getCards(DEALER_NAME)).hasSize(2),
            () -> assertThat(round.getCards(DEALER_NAME)).contains(card1, card2)
        );
    }

    @DisplayName("해당_이름을_가진_겜블러가_버스트_되었는지_여부를_반환한다")
    @CsvSource(value = {"TWO:false", "THREE:true"}, delimiterString = ":")
    @ParameterizedTest
    void isBusted(CardType type, boolean expected) {
        // given
        List<Card> cards = createCards(KING, NINE, type);
        CardDeck cardDeck = new CardDeck(cards, shuffler);
        Names playerNames = new Names(List.of(name));
        Round round = new Round(cardDeck, playerNames);

        round.distributeCards(name, 3);

        // when
        boolean result = round.isBust(name);

        // then
        assertThat(result).isEqualTo(expected);
    }
    
    @DisplayName("딜러가_한장을_더_받아야_하는지_여부를_반환한다")
    @CsvSource(value = {"TWO:true", "THREE:false"}, delimiterString = ":")
    @ParameterizedTest
    void dealerMustDraw(CardType type, boolean expected) {
        // given
        List<Card> cards = createCards(EIGHT, SIX, type);
        CardDeck cardDeck = new CardDeck(cards, shuffler);
        Names playerNames = new Names(List.of(name));
        Round round = new Round(cardDeck, playerNames);

        round.distributeCards(DEALER_NAME, 3);

        // when
        boolean result = round.dealerMustDraw();

        // then
        assertThat(result).isEqualTo(expected);
    }
}
