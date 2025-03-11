package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.CardType;
import blackjack.domain.card.Shuffler;
import blackjack.domain.fake.TestShuffler;
import blackjack.domain.gambler.Name;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RoundTest {
    private final Shuffler shuffler = new TestShuffler();

    @DisplayName("특정 플레이어에게 지정한 장 수의 카드를 지급한다")
    @Test
    void distributeCardsTest() {
        // given
        Card card1 = new Card(CardShape.CLOVER, CardType.TEN);
        Card card2 = new Card(CardShape.HEART, CardType.EIGHT);
        CardDeck cardDeck = new CardDeck(List.of(card1, card2), shuffler);

        Name playerName = new Name("라젤");
        List<Name> playerNames = List.of(playerName);
        Round round = new Round(cardDeck, playerNames);

        // when
        round.distributeCards(playerName, 2);

        // then
        assertThat(round.getCards(playerName)).contains(card1, card2);
        assertThat(round.getScore(playerName)).isEqualTo(18);
    }

    @DisplayName("게임 시작 시, 플레이어와 딜러에게 2장씩 카드를 지급한다")
    @Test
    void distributeInitialCardsTest() {
        // given
        Card card1 = new Card(CardShape.CLOVER, CardType.FIVE);
        Card card2 = new Card(CardShape.HEART, CardType.ACE);
        Card card3 = new Card(CardShape.CLOVER, CardType.TEN);
        Card card4 = new Card(CardShape.HEART, CardType.KING);
        CardDeck cardDeck = new CardDeck(List.of(card1, card2, card3, card4), shuffler);

        Name playerName = new Name("라젤");
        List<Name> playerNames = List.of(playerName);
        Round round = new Round(cardDeck, playerNames);

        // when
        round.distributeInitialCards();

        // then
        assertThat(round.getScore(playerName)).isNotZero();
        assertThat(round.getCards(Name.createDealer())).size().isEqualTo(2);
        assertThat(round.getCards(Name.createDealer())).contains(card1, card2);
        assertThat(round.getScore(Name.createDealer())).isEqualTo(16);
        assertThat(round.getCards(playerName)).size().isEqualTo(2);
        assertThat(round.getCards(playerName)).contains(card3, card4);
        assertThat(round.getScore(playerName)).isEqualTo(20);
    }

    @DisplayName("딜러와 플레이어의 카드 및 점수 정보를 가진 승패 판별기를 만든다")
    @CsvSource(value = {"WIN:1", "DRAW:0", "DEFEAT:0"}, delimiterString = ":")
    @ParameterizedTest
    void getWinningDiscriminator(WinningType type, int expected) {
        // given
        Card card1 = new Card(CardShape.CLOVER, CardType.TEN);
        Card card2 = new Card(CardShape.HEART, CardType.FIVE);
        Card card3 = new Card(CardShape.CLOVER, CardType.SIX);
        Card card4 = new Card(CardShape.HEART, CardType.EIGHT);
        CardDeck cardDeck = new CardDeck(List.of(card1, card2, card3, card4), shuffler);

        Name playerName = new Name("라젤");
        List<Name> playerNames = List.of(playerName);
        Round round = new Round(cardDeck, playerNames);
        round.distributeInitialCards();

        // when
        WinningDiscriminator result = round.getWinningDiscriminator();
        Map<WinningType, Integer> dealerWinning = result.judgeDealerResult();

        // then
        assertThat(dealerWinning.get(type)).isEqualTo(expected);
    }

    @DisplayName("플레이어 버스트 테스트")
    @Test
    void isPlayerBustedTest() {
        // given
        Card card1 = new Card(CardShape.CLOVER, CardType.TEN);
        Card card2 = new Card(CardShape.HEART, CardType.FIVE);
        Card card3 = new Card(CardShape.CLOVER, CardType.SIX);
        Card card4 = new Card(CardShape.HEART, CardType.EIGHT);
        Card card5 = new Card(CardShape.CLOVER, CardType.EIGHT);
        CardDeck cardDeck = new CardDeck(List.of(card1, card2, card3, card4, card5), shuffler);

        Name playerName = new Name("라젤");
        List<Name> playerNames = List.of(playerName);
        Round round = new Round(cardDeck, playerNames);
        round.distributeInitialCards();
        round.distributeCards(playerName, 1);

        // when
        boolean result = !round.isGamblerCanReceiveCard(playerName, WinningDiscriminator.BLACK_JACK);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("ss")
    @Test
    void dealerMustReceiveCardTest() {
        // given
        Card card1 = new Card(CardShape.CLOVER, CardType.TEN);
        Card card2 = new Card(CardShape.HEART, CardType.FIVE);
        Card card3 = new Card(CardShape.CLOVER, CardType.SIX);
        Card card4 = new Card(CardShape.HEART, CardType.EIGHT);
        Card card5 = new Card(CardShape.HEART, CardType.EIGHT);
        CardDeck cardDeck = new CardDeck(List.of(card1, card2, card3, card4, card5), shuffler);

        Name playerName = new Name("라젤");
        List<Name> playerNames = List.of(playerName);
        Round round = new Round(cardDeck, playerNames);
        round.distributeInitialCards();

        // when
        boolean result = round.isGamblerCanReceiveCard(Name.createDealer(), Round.DEALER_RECEIVE_CRITERIA); // 딜러의 점수 15

        // then
        assertThat(result).isTrue();
    }
}
