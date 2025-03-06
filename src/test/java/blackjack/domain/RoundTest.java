package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardShape;
import blackjack.domain.card.CardType;
import blackjack.domain.gambler.Name;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
        round.distributeCards(new Name("라젤"), 2);

        // then
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
        assertThat(round.getCards(new Name("딜러")).size()).isEqualTo(2);
        assertThat(round.getScore(new Name("딜러"))).isEqualTo(16);
        assertThat(round.getCards(playerName).size()).isEqualTo(2);
        assertThat(round.getScore(playerName)).isEqualTo(20);
    }

    @DisplayName("게임 시작 시, 플레이어와 딜러에게 2장씩 카드를 지급한다")
    @Test
    void getWinningDiscriminator() {
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
        Map<String, Integer> dealerWinning = result.judgeDealerResult();

        // then
        assertThat(dealerWinning.get("승")).isEqualTo(1);
        assertThat(dealerWinning.get("무")).isEqualTo(0);
        assertThat(dealerWinning.get("패")).isEqualTo(0);
    }
}
