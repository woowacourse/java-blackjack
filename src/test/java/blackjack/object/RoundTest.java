package blackjack.object;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.object.card.Card;
import blackjack.object.card.CardDeck;
import blackjack.object.card.CardShape;
import blackjack.object.card.CardType;
import blackjack.object.card.Shuffler;
import blackjack.object.fake.TestShuffler;
import blackjack.object.gambler.Name;

import java.util.HashMap;
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
        assertThat(round.getCards(Name.getDealerName())).size().isEqualTo(2);
        assertThat(round.getCards(Name.getDealerName())).contains(card1, card2);
        assertThat(round.getScore(Name.getDealerName())).isEqualTo(16);
        assertThat(round.getCards(playerName)).size().isEqualTo(2);
        assertThat(round.getCards(playerName)).contains(card3, card4);
        assertThat(round.getScore(playerName)).isEqualTo(20);
    }

    @DisplayName("딜러와 플레이어의 카드 및 점수 정보를 가진 승패 판별기를 만든다")
    @CsvSource(value = {"딜러:10000", "라젤:-10000"}, delimiterString = ":")
    @ParameterizedTest
    void getWinningDiscriminator(Name type, int expected) {
        // given
        Card card1 = new Card(CardShape.CLOVER, CardType.TEN);
        Card card2 = new Card(CardShape.CLOVER, CardType.EIGHT);
        Card card3 = new Card(CardShape.CLOVER, CardType.SIX);
        Card card4 = new Card(CardShape.HEART, CardType.EIGHT);
        CardDeck cardDeck = new CardDeck(List.of(card1, card2, card3, card4), shuffler);

        Name playerName = new Name("라젤");
        List<Name> playerNames = List.of(playerName);
        Map<Name, Integer> bettingRecords = new HashMap<>(Map.of(
                playerName, 10000
        ));
        Round round = new Round(cardDeck, playerNames);
        round.distributeInitialCards();

        // when
        WinningDiscriminator result = round.getWinningDiscriminator(bettingRecords);
        Map<Name, Integer> dealerWinning = result.calculateGamblerProfit();

        // then
        assertThat(dealerWinning.get(type)).isEqualTo(expected);
    }

    @DisplayName("플레이어가 버스트인 경우를 판별한다")
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

    @DisplayName("딜러가 카드를 추가로 받아야 하는 경우인지 판별한다")
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
        boolean result = round.isGamblerCanReceiveCard(Name.getDealerName(), Round.DEALER_RECEIVE_CRITERIA); // 딜러의 점수 15

        // then
        assertThat(result).isTrue();
    }
}
