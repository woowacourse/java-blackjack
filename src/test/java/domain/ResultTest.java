package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.FixtureCard.*;
import static domain.FixtureCardDeck.NOT_SHUFFLED_CARD_DECK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class ResultTest {
    @DisplayName("게임의 결과를 반환한다.")
    @Test
    void getGameResult() {
        List<Card> bustCards = List.of(TEN_HEART, TEN_HEART, TWO_HEART);
        List<Card> blackJackCards = List.of(TEN_HEART, ACE_HEART);
        List<Card> maxScoreCards = List.of(TEN_HEART, TEN_HEART, ACE_HEART);
        List<Card> minScoreCards = List.of(TWO_HEART);

        Dealer dealerWithBust = new Dealer(NOT_SHUFFLED_CARD_DECK);
        dealerWithBust.initHand(bustCards);
        Dealer dealerWithBlackJack = new Dealer(NOT_SHUFFLED_CARD_DECK);
        dealerWithBlackJack.initHand(blackJackCards);
        Dealer dealerWithMaxScore = new Dealer(NOT_SHUFFLED_CARD_DECK);
        dealerWithMaxScore.initHand(maxScoreCards);
        Dealer dealerWithMinScore = new Dealer(NOT_SHUFFLED_CARD_DECK);
        dealerWithMinScore.initHand(minScoreCards);

        Player playerWithBust = new Player("버스트");
        playerWithBust.initHand(bustCards);
        Player playerWithBlackJack = new Player("블랙잭");
        playerWithBlackJack.initHand(blackJackCards);
        Player playerWithMaxScore = new Player("최고 점수");
        playerWithMaxScore.initHand(maxScoreCards);
        Player playerWithMinScore = new Player("최저 점수");
        playerWithMinScore.initHand(minScoreCards);

        assertAll("한쪽만 버스트되면 상대가 이긴다. 둘 다 버스트되면 딜러가 이긴다.",
                () -> assertThat(Result.of(dealerWithBust, playerWithMinScore)).isEqualTo(Result.PLAYER_WIN),
                () -> assertThat(Result.of(dealerWithMinScore, playerWithBust)).isEqualTo(Result.DEALER_WIN),
                () -> assertThat(Result.of(dealerWithBust, playerWithBust)).isEqualTo(Result.DEALER_WIN));

        assertAll("둘 다 버스트되지 않으면 21에 가까운 쪽이 이긴다. 점수가 같으면 비긴다.",
                () -> assertThat(Result.of(dealerWithMaxScore, playerWithMinScore)).isEqualTo(Result.DEALER_WIN),
                () -> assertThat(Result.of(dealerWithMinScore, playerWithMaxScore)).isEqualTo(Result.PLAYER_WIN),
                () -> assertThat(Result.of(dealerWithMaxScore, playerWithMaxScore)).isEqualTo(Result.PUSH));

        assertAll("한쪽만 블랙잭이면 그쪽이 이긴다. 둘 다 블랙잭이면 비긴다.",
                () -> assertThat(Result.of(dealerWithMaxScore, playerWithBlackJack)).isEqualTo(Result.PLAYER_BLACK_JACK_WIN),
                () -> assertThat(Result.of(dealerWithBlackJack, playerWithMaxScore)).isEqualTo(Result.DEALER_WIN),
                () -> assertThat(Result.of(dealerWithBlackJack, playerWithBlackJack)).isEqualTo(Result.PUSH)
        );
    }
}
