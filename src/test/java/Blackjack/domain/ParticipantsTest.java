package Blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import Blackjack.domain.card.Card;
import Blackjack.domain.card.Rank;
import Blackjack.domain.card.Suit;
import Blackjack.domain.game.GameResult;
import Blackjack.domain.game.GameStatus;
import Blackjack.domain.game.Participants;
import Blackjack.exception.ErrorException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ParticipantsTest {

    @Test
    @DisplayName("이름이 중복되는 참여자 예외 테스트")
    void duplicateParticipantNamesTest() {
        // given
        String name = "pobi,jason,pobi";
        List<String> names = Arrays.stream(name.split(",")).toList();
        // when & then
        assertThatThrownBy(() -> new Participants(names))
                .isInstanceOf(ErrorException.class)
                .hasMessageContaining("[ERROR] 참여자 이름은 중복될 수 없습니다.");
    }

    /*@ParameterizedTest
    @CsvSource({"pobi,0,0,1,THREE", "jason,0,1,0,FIVE", "neo,1,0,0,SEVEN"})
    @DisplayName("플레이어 게임 결과 계산 기능 테스트")
    void playerGameResultTest(String name, int win, int tie, int lose, String rankName) {
        // given
        List<Rank> ranks = List.of(Rank.JACK, Rank.JACK, Rank.JACK, Rank.JACK);
        List<Card> cards = ranks.stream().map(rank -> new Card(rank, Suit.HEART)).toList();
        Participants participants = new Participants(List.of("pobi", "jason", "neo"));
        participants.addInitialCards(cards);
        participants.addCardTo("딜러", new Card(Rank.FIVE, Suit.HEART));

        // when
        participants.addCardTo(name, new Card(Rank.valueOf(rankName), Suit.HEART));
        Map<String, GameResult> gameResults = participants.determineGameResult();
        GameResult actualGameResult = gameResults.get(name);

        // then
        assertAll(
                () -> assertEquals(win, actualGameResult.getStatusCount(GameStatus.WIN)),
                () -> assertEquals(tie, actualGameResult.getStatusCount(GameStatus.TIE)),
                () -> assertEquals(lose, actualGameResult.getStatusCount(GameStatus.LOSE))
        );
    }

    @Test
    @DisplayName("딜러 게임 결과 계산 기능 테스트")
    void dealerGameResultTest() {
        // given
        Participants participants = new Participants(List.of("pobi", "jason", "neo"));
        List<Rank> ranks = List.of(Rank.JACK, Rank.JACK, Rank.JACK, Rank.JACK);
        List<Card> cards = ranks.stream().map(rank -> new Card(rank, Suit.HEART)).toList();
        participants.addInitialCards(cards);
        participants.addCardTo("딜러", new Card(Rank.FIVE, Suit.HEART));
        participants.addCardTo("pobi", new Card(Rank.THREE, Suit.HEART));
        participants.addCardTo("jason", new Card(Rank.FIVE, Suit.HEART));
        participants.addCardTo("neo", new Card(Rank.SEVEN, Suit.HEART));

        // when
        Map<String, GameResult> gameResults = participants.determineGameResult();
        GameResult actualGameResult = gameResults.get("딜러");

        // then
        assertAll(
                () -> assertEquals(1, actualGameResult.getStatusCount(GameStatus.WIN)),
                () -> assertEquals(1, actualGameResult.getStatusCount(GameStatus.TIE)),
                () -> assertEquals(1, actualGameResult.getStatusCount(GameStatus.LOSE))
        );
    }*/
}
