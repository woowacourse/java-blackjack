package blackjack.domain.machine;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Fixtures;
import blackjack.domain.participant.Guest;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    private BlackjackGame blackjackGame;

    @BeforeEach
    void setUp() {
        blackjackGame = new BlackjackGame(List.of("eden"));
        blackjackGame.hasNextGuest();
    }

    @Test
    @DisplayName("처음 턴일 때 게스트가 있는지 확인")
    void checkExistGuest() {
        assertThat(blackjackGame.hasNextGuest()).isTrue();
    }

    @Test
    @DisplayName("카드를 준 뒤 받는 응답 객체가 해당 플레이어의 응답인지 확인")
    void checkAddCardToPlayer() {
        GameResponse gameResponse = blackjackGame.addCardToPlayer();
        assertThat(gameResponse.getName()).isEqualTo("eden");
    }

    @Test
    @DisplayName("bust 점수일 때 터지는지 확인")
    void checkOverLimit() {
        blackjackGame.getTurnPlayer().addCard(Fixtures.SPADE_JACK);
        blackjackGame.getTurnPlayer().addCard(Fixtures.SPADE_TEN);
        blackjackGame.getTurnPlayer().addCard(Fixtures.SPADE_NINE);
        assertThat(blackjackGame.checkOverLimit()).isTrue();
    }

    @Test
    @DisplayName("두 명이 참가한 상태에서 결과 객체가 딜러와 참가자 1명 총 2명인지 확인")
    void checkCalculateResult() {
        Map<Name, Double> bettingBox = new LinkedHashMap<>();
        Player player = new Guest("eden");
        bettingBox.put(player.getName(), (double) 1000);
        MatchResults matchResults = blackjackGame.calculateResult(bettingBox);
        assertThat(matchResults.getKeys().size()).isEqualTo(2);
    }
}
