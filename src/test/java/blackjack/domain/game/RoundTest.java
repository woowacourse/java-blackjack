package blackjack.domain.game;

import static blackjack.domain.gambler.Dealer.DEALER_NAME;
import static org.assertj.core.api.Assertions.assertThat;

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
        Names playerNames = new Names(List.of(playerName));
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
        Names playerNames = new Names(List.of(playerName));
        Round round = new Round(cardDeck, playerNames);

        // when
        round.distributeInitialCards();

        // then
        assertThat(round.getScore(playerName)).isNotZero();
        assertThat(round.getCards(DEALER_NAME)).hasSize(2);
        assertThat(round.getCards(DEALER_NAME)).contains(card1, card2);
        assertThat(round.getScore(DEALER_NAME)).isEqualTo(16);
        assertThat(round.getCards(playerName)).hasSize(2);
        assertThat(round.getCards(playerName)).contains(card3, card4);
        assertThat(round.getScore(playerName)).isEqualTo(20);
    }
}
