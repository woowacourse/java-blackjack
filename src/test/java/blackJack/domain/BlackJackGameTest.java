package blackJack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackJack.domain.card.Card;
import blackJack.domain.card.Denomination;
import blackJack.domain.card.Symbol;
import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Participants;
import blackJack.domain.participant.Player;
import blackJack.domain.result.MatchResult;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {

    private Player player1;
    private Player player2;
    private Player player3;
    private Dealer dealer;

    @BeforeEach
    void setUp() {
        player1 = new Player("kei");
        player2 = new Player("rookie");
        player3 = new Player("parang");
        dealer = new Dealer();
    }

    @Test
    @DisplayName("BlackJackGame 생성 테스트")
    void createValidDealer() {
        Participants participants = new Participants(dealer, List.of(player1, player2, player3));

        assertThat(new BlackJackGame(participants)).isNotNull();
    }

    @Test
    @DisplayName("게임 시작시 최초 카드 분배 기능 테스트")
    void firstCardDispensing() {
        Participants participants = new Participants(dealer, List.of(player1, player2, player3));
        BlackJackGame blackJackGame = new BlackJackGame(participants);

        blackJackGame.firstCardDispensing();

        assertThat(player1.getCards().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어들의 승패 결과 테스트")
    void getPlayersGameResult() {
        player1.hit(Card.from(Symbol.SPADE, Denomination.EIGHT));
        player2.hit(Card.from(Symbol.SPADE, Denomination.JACK));
        player3.hit(Card.from(Symbol.SPADE, Denomination.ACE));
        dealer.hit(Card.from(Symbol.SPADE, Denomination.NINE));

        Participants participants = new Participants(dealer, List.of(player1, player2, player3));
        BlackJackGame blackJackGame = new BlackJackGame(participants);

        assertThat(blackJackGame.getPlayersGameResult()).contains(
                Map.entry(player1, MatchResult.LOSE),
                Map.entry(player2, MatchResult.WIN),
                Map.entry(player3, MatchResult.WIN)
        );
    }

    @Test
    @DisplayName("딜러의 승패 결과 테스트")
    void getDealerGameResult() {
        player1.hit(Card.from(Symbol.SPADE, Denomination.EIGHT));
        player2.hit(Card.from(Symbol.SPADE, Denomination.JACK));
        player3.hit(Card.from(Symbol.SPADE, Denomination.ACE));
        dealer.hit(Card.from(Symbol.SPADE, Denomination.NINE));

        Participants participants = new Participants(dealer, List.of(player1, player2, player3));
        BlackJackGame blackJackGame = new BlackJackGame(participants);

        assertThat(blackJackGame.getDealerGameResult()).contains(
                Map.entry(MatchResult.WIN, 1),
                Map.entry(MatchResult.DRAW, 0),
                Map.entry(MatchResult.LOSE, 2)
        );
    }
}
