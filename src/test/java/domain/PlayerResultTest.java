package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PlayerResultTest {
    private void addCardsToParticipantDeck(Participant participant, Card... cards) {
        for (Card card : cards) {
            participant.addCard(card);
        }
    }

    @Nested
    class DeterminePlayerResultTest {
        @Test
        @DisplayName("플레이어가 버스트라면 딜러의 버스트 여부와 상관없이 무조건 패배한다.")
        void shouldReturnLossWhenPlayerIsBust() {
            // given
            Dealer dealer = new Dealer();
            addCardsToParticipantDeck(dealer,
                    new Card(CardShape.SPADE, CardContents.TEN),
                    new Card(CardShape.CLOVER, CardContents.THREE),
                    new Card(CardShape.HEART, CardContents.TEN)
            );

            Player player = new Player(new Name("플레이어"), new BetMoney(1000));
            addCardsToParticipantDeck(player,
                    new Card(CardShape.DIAMOND, CardContents.TEN),
                    new Card(CardShape.HEART, CardContents.THREE),
                    new Card(CardShape.SPADE, CardContents.TEN)
            );

            // when
            PlayerResult playerResult = PlayerResult.determinePlayerResult(dealer, player);

            // then
            assertThat(playerResult).isEqualTo(PlayerResult.LOSS);
        }

        @Test
        @DisplayName("플레이어는 버스트가 아니고 딜러만 버스트인 경우 승리를 반환한다.")
        void shouldReturnWinWhenPlayerIsNotBustButDealerIsBust() {
            // given
            Dealer dealer = new Dealer();
            addCardsToParticipantDeck(dealer,
                    new Card(CardShape.SPADE, CardContents.TEN),
                    new Card(CardShape.CLOVER, CardContents.THREE),
                    new Card(CardShape.HEART, CardContents.TEN)
            );

            Player player = new Player(new Name("플레이어"), new BetMoney(1000));
            addCardsToParticipantDeck(player,
                    new Card(CardShape.DIAMOND, CardContents.TEN),
                    new Card(CardShape.HEART, CardContents.THREE)
            );

            // when
            PlayerResult playerResult = PlayerResult.determinePlayerResult(dealer, player);

            // then
            assertThat(playerResult).isEqualTo(PlayerResult.WIN);
        }

        @Test
        @DisplayName("플레이어와 딜러 모두 버스트가 아니고, 플레이어 점수가 딜러 점수보다 높으면 승리를 반환한다.")
        void shouldReturnWinWhenNeitherIsBustAndPlayerScoreIsHigherThanDealerScore() {
            // given
            Dealer dealer = new Dealer();
            addCardsToParticipantDeck(dealer,
                    new Card(CardShape.SPADE, CardContents.TEN),
                    new Card(CardShape.CLOVER, CardContents.SEVEN)
            );

            Player player = new Player(new Name("플레이어"), new BetMoney(1000));
            addCardsToParticipantDeck(player,
                    new Card(CardShape.DIAMOND, CardContents.TEN),
                    new Card(CardShape.HEART, CardContents.EIGHT)
            );

            // when
            PlayerResult playerResult = PlayerResult.determinePlayerResult(dealer, player);

            // then
            assertThat(playerResult).isEqualTo(PlayerResult.WIN);
        }

        @Test
        @DisplayName("플레이어와 딜러 모두 버스트가 아니고, 플레이어 점수가 딜러 점수보다 낮으면 패배를 반환한다.")
        void shouldReturnLossWhenNeitherIsBustAndPlayerScoreIsLowerThanDealerScore() {
            // given
            Dealer dealer = new Dealer();
            addCardsToParticipantDeck(dealer,
                    new Card(CardShape.SPADE, CardContents.TEN),
                    new Card(CardShape.CLOVER, CardContents.TEN)
            );

            Player player = new Player(new Name("플레이어"), new BetMoney(1000));
            addCardsToParticipantDeck(player,
                    new Card(CardShape.DIAMOND, CardContents.TEN),
                    new Card(CardShape.HEART, CardContents.NINE)
            );

            // when
            PlayerResult playerResult = PlayerResult.determinePlayerResult(dealer, player);

            // then
            assertThat(playerResult).isEqualTo(PlayerResult.LOSS);
        }

        @Test
        @DisplayName("플레이어와 딜러 모두 버스트가 아니고, 플레이어 점수와 딜러 점수가 같다면 무승부를 반환한다.")
        void shouldReturnTieWhenNeitherIsBustAndScoresAreEqual() {
            // given
            Dealer dealer = new Dealer();
            addCardsToParticipantDeck(dealer,
                    new Card(CardShape.SPADE, CardContents.TEN),
                    new Card(CardShape.CLOVER, CardContents.SEVEN)
            );

            Player player = new Player(new Name("플레이어"), new BetMoney(1000));
            addCardsToParticipantDeck(player,
                    new Card(CardShape.DIAMOND, CardContents.TEN),
                    new Card(CardShape.HEART, CardContents.SEVEN)
            );

            // when
            PlayerResult playerResult = PlayerResult.determinePlayerResult(dealer, player);

            // then
            assertThat(playerResult).isEqualTo(PlayerResult.TIE);
        }
    }
}
