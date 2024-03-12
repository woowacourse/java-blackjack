package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.FixtureCard.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ResultTest {
    @DisplayName("게임의 결과를 반환한다.")
    @Test
    void getGameResult() {
        List<Card> bustCards = List.of(TEN_HEART, TEN_HEART, TWO_HEART);
        List<Card> maxScoreCards = List.of(ACE_HEART, TEN_HEART);
        List<Card> minScoreCards = List.of(TWO_HEART);

        CardDeck cardDeck = new CardDeck();
        Dealer dealerWithBust = new Dealer(cardDeck);
        dealerWithBust.initHand(bustCards);
        Dealer dealerWithMaxScore = new Dealer(cardDeck);
        dealerWithMaxScore.initHand(maxScoreCards);
        Dealer dealerWithMinScore = new Dealer(cardDeck);
        dealerWithMinScore.initHand(minScoreCards);

        Player playerWithBust = new Player("버스트");
        playerWithBust.initHand(bustCards);
        Player playerWithMaxScore = new Player("최고 점수");
        playerWithMaxScore.initHand(maxScoreCards);
        Player playerWithMinScore = new Player("최저 점수");
        playerWithMinScore.initHand(minScoreCards);

        assertAll(
                () -> assertThat(Result.of(dealerWithBust, playerWithBust)).isEqualTo(Result.DEALER_WIN),
                () -> assertThat(Result.of(dealerWithMinScore, playerWithBust)).isEqualTo(Result.DEALER_WIN),
                () -> assertThat(Result.of(dealerWithMaxScore, playerWithMinScore)).isEqualTo(Result.DEALER_WIN),
                () -> assertThat(Result.of(dealerWithMaxScore, playerWithMaxScore)).isEqualTo(Result.PUSH),
                () -> assertThat(Result.of(dealerWithBust, playerWithMinScore)).isEqualTo(Result.PLAYER_WIN),
                () -> assertThat(Result.of(dealerWithMinScore, playerWithMaxScore)).isEqualTo(Result.PLAYER_WIN)
        );
    }
}
