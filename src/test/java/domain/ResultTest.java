package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.FixtureCard.*;
import static domain.PlayerGameResult.*;
import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {

    private final List<Card> bustCards = List.of(TEN_HEART, TEN_HEART, TWO_HEART);
    private final List<Card> blackJackCards = List.of(TEN_HEART, ACE_HEART);
    private final List<Card> maxScoreCards = List.of(TEN_HEART, TEN_HEART, ACE_HEART);
    private final List<Card> minScoreCards = List.of(TWO_HEART);

    @Nested
    @DisplayName("게임의 결과를 반환한다.")
    class resultTest {

        @DisplayName("한쪽만 버스트되면 상대가 이긴다.")
        @Test
        void resultWhenOnlyOneBust() {
            Dealer dealerWithBust = new Dealer(bustCards);
            Dealer dealerWithMinScore = new Dealer(minScoreCards);
            Player playerWithBust = new Player(bustCards);
            Player playerWithMinScore = new Player(minScoreCards);

            assertThat(PlayerGameResult.of(dealerWithBust, playerWithMinScore)).isEqualTo(WIN);
            assertThat(PlayerGameResult.of(dealerWithMinScore, playerWithBust)).isEqualTo(LOSE);
        }

        @DisplayName("둘 다 버스트되면 딜러가 이긴다.")
        @Test
        void resultWhenAllBust() {
            Dealer dealerWithBust = new Dealer(bustCards);
            Player playerWithBust = new Player(bustCards);

            assertThat(PlayerGameResult.of(dealerWithBust, playerWithBust)).isEqualTo(LOSE);
        }

        @DisplayName("둘 다 버스트되지 않으면 21에 가까운 쪽이 이긴다.")
        @Test
        void resultWhenNotBust() {
            Dealer dealerWithMaxScore = new Dealer(maxScoreCards);
            Dealer dealerWithMinScore = new Dealer(minScoreCards);
            Player playerWithMaxScore = new Player(maxScoreCards);
            Player playerWithMinScore = new Player(minScoreCards);

            assertThat(PlayerGameResult.of(dealerWithMinScore, playerWithMaxScore)).isEqualTo(WIN);
            assertThat(PlayerGameResult.of(dealerWithMaxScore, playerWithMinScore)).isEqualTo(LOSE);
        }

        @DisplayName("둘 다 버스트되지 않으면서 점수가 같으면 비긴다.")
        @Test
        void resultWhenNotBustAndTie() {
            Dealer dealerWithMaxScore = new Dealer(maxScoreCards);
            Player playerWithMaxScore = new Player(maxScoreCards);

            assertThat(PlayerGameResult.of(dealerWithMaxScore, playerWithMaxScore)).isEqualTo(PUSH);
        }

        @DisplayName("한쪽만 블랙잭이면 그쪽이 이긴다.")
        @Test
        void resultWhenOnlyOneBlackJack() {
            Dealer dealerWithBlackJack = new Dealer(blackJackCards);
            Dealer dealerWithMaxScore = new Dealer(maxScoreCards);
            Player playerWithBlackJack = new Player(blackJackCards);
            Player playerWithMaxScore = new Player(maxScoreCards);

            assertThat(PlayerGameResult.of(dealerWithMaxScore, playerWithBlackJack)).isEqualTo(BLACKJACK_WIN);
            assertThat(PlayerGameResult.of(dealerWithBlackJack, playerWithMaxScore)).isEqualTo(LOSE);
        }

        @DisplayName("둘 다 블랙잭이면 비긴다.")
        @Test
        void resultWhenAllBlackJack() {
            Dealer dealerWithBlackJack = new Dealer(blackJackCards);
            Player playerWithBlackJack = new Player(blackJackCards);

            assertThat(PlayerGameResult.of(dealerWithBlackJack, playerWithBlackJack)).isEqualTo(PUSH);
        }
    }
}
