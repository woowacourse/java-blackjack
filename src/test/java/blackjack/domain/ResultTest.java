package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardDeckShuffleStrategy;
import blackjack.domain.card.RandomCardDeckShuffleStrategy;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static blackjack.domain.PlayerGameResult.*;
import static blackjack.domain.fixture.FixtureCard.*;
import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {

    private final List<Card> bustCards = List.of(TEN_HEART, TEN_HEART, TWO_HEART);
    private final List<Card> blackJackCards = List.of(TEN_HEART, ACE_HEART);
    private final List<Card> maxScoreCards = List.of(TEN_HEART, TEN_HEART, ACE_HEART);
    private final List<Card> minScoreCards = List.of(TWO_HEART);
    private final CardDeckShuffleStrategy cardDeckShuffleStrategy = new RandomCardDeckShuffleStrategy();
    private final CardDeck cardDeck = CardDeck.of(cardDeckShuffleStrategy);


    @Nested
    @DisplayName("게임의 결과를 반환한다.")
    class resultTest {

        @DisplayName("한쪽만 버스트되면 상대가 이긴다.")
        @Test
        void resultWhenOnlyOneBust() {
            Dealer dealerWithBust = new Dealer(cardDeck);
            Dealer dealerWithMinScore = new Dealer(cardDeck);
            dealerWithBust.initHand(bustCards);
            dealerWithMinScore.initHand(minScoreCards);
            Player playerWithBust = new Player("버스트", 10);
            Player playerWithMinScore = new Player("최저 점수", 10);
            playerWithBust.initHand(bustCards);
            playerWithBust.initHand(minScoreCards);

            assertThat(PlayerGameResult.of(dealerWithBust, playerWithMinScore)).isEqualTo(WIN);
            assertThat(PlayerGameResult.of(dealerWithMinScore, playerWithBust)).isEqualTo(LOSE);
        }

        @DisplayName("둘 다 버스트되면 딜러가 이긴다.")
        @Test
        void resultWhenAllBust() {
            Dealer dealerWithBust = new Dealer(cardDeck);
            dealerWithBust.initHand(bustCards);
            Player playerWithBust = new Player("버스트", 10);
            playerWithBust.initHand(bustCards);

            assertThat(PlayerGameResult.of(dealerWithBust, playerWithBust)).isEqualTo(LOSE);
        }

        @DisplayName("둘 다 버스트되지 않으면 21에 가까운 쪽이 이긴다.")
        @Test
        void resultWhenNotBust() {
            Dealer dealerWithMaxScore = new Dealer(cardDeck);
            Dealer dealerWithMinScore = new Dealer(cardDeck);
            dealerWithMinScore.initHand(minScoreCards);
            dealerWithMaxScore.initHand(maxScoreCards);
            Player playerWithMaxScore = new Player("최고 점수", 10);
            Player playerWithMinScore = new Player("최저 점수", 10);
            playerWithMaxScore.initHand(maxScoreCards);
            playerWithMinScore.initHand(minScoreCards);

            assertThat(PlayerGameResult.of(dealerWithMinScore, playerWithMaxScore)).isEqualTo(WIN);
            assertThat(PlayerGameResult.of(dealerWithMaxScore, playerWithMinScore)).isEqualTo(LOSE);
        }

        @DisplayName("둘 다 버스트되지 않으면서 점수가 같으면 비긴다.")
        @Test
        void resultWhenNotBustAndTie() {
            Dealer dealerWithMaxScore = new Dealer(cardDeck);
            dealerWithMaxScore.initHand(maxScoreCards);
            Player playerWithMaxScore = new Player("최고 점수", 10);
            playerWithMaxScore.initHand(maxScoreCards);

            assertThat(PlayerGameResult.of(dealerWithMaxScore, playerWithMaxScore)).isEqualTo(PUSH);
        }

        @DisplayName("한쪽만 블랙잭이면 그쪽이 이긴다.")
        @Test
        void resultWhenOnlyOneBlackJack() {
            Dealer dealerWithMaxScore = new Dealer(cardDeck);
            Dealer dealerWithBlackJack = new Dealer(cardDeck);
            dealerWithMaxScore.initHand(maxScoreCards);
            dealerWithBlackJack.initHand(blackJackCards);

            Player playerWithBlackJack = new Player("블랙잭", 10);
            Player playerWithMaxScore = new Player("최고 점수", 10);
            playerWithBlackJack.initHand(blackJackCards);
            playerWithMaxScore.initHand(maxScoreCards);

            assertThat(PlayerGameResult.of(dealerWithMaxScore, playerWithBlackJack)).isEqualTo(BLACKJACK_WIN);
            assertThat(PlayerGameResult.of(dealerWithBlackJack, playerWithMaxScore)).isEqualTo(LOSE);
        }

        @DisplayName("둘 다 블랙잭이면 비긴다.")
        @Test
        void resultWhenAllBlackJack() {
            Dealer dealerWithBlackJack = new Dealer(cardDeck);
            dealerWithBlackJack.initHand(blackJackCards);
            Player playerWithBlackJack = new Player("블랙잭", 10);
            playerWithBlackJack.initHand(blackJackCards);

            assertThat(PlayerGameResult.of(dealerWithBlackJack, playerWithBlackJack)).isEqualTo(PUSH);
        }
    }
}
