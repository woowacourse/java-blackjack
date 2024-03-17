package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Deck;
import blackjack.domain.card.Number;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Round;
import blackjack.testutil.CustomDeck;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RefereeTest {
    @DisplayName("점수 판단으로 딜러가 이기는 게임의 최종 결과를 생성한다.")
    @Test
    void generateResultThatDealerScoreWin() {
        //given
        List<Number> numbers = List.of(Number.ACE, Number.SIX, Number.ACE, Number.EIGHT);
        Deck deck = new CustomDeck(numbers);
        List<Name> playerName = List.of(new Name("gamza"));
        Round round = new Round(playerName, deck);
        Referee referee = Referee.getInstance();

        //when
        Map<Player, HandResult> roundResult = round.generateResult(referee);

        //then
        assertThat(roundResult.values()).containsExactly(HandResult.LOSE);
    }

    @DisplayName("점수 판단으로 플레이어가 이기는 게임의 최종 결과를 생성한다.")
    @Test
    void generateResultThatPlayerScoreWin() {
        //given
        List<Number> numbers = List.of(Number.ACE, Number.NINE, Number.ACE, Number.EIGHT);
        Deck deck = new CustomDeck(numbers);
        List<Name> playerName = List.of(new Name("mason"));
        Round round = new Round(playerName, deck);
        Referee referee = Referee.getInstance();

        //when
        Map<Player, HandResult> roundResult = round.generateResult(referee);

        //then
        assertThat(roundResult.values()).containsExactly(HandResult.WIN);
    }

    @DisplayName("점수 판단으로 비기는 게임의 최종 결과를 생성한다.")
    @Test
    void generateResultThatScoreDraw() {
        //given
        List<Number> numbers = List.of(Number.SEVEN, Number.JACK, Number.QUEEN, Number.SEVEN);
        Deck deck = new CustomDeck(numbers);
        List<Name> playerName = List.of(new Name("pobi"));
        Round round = new Round(playerName, deck);
        Referee referee = Referee.getInstance();

        //when
        Map<Player, HandResult> roundResult = round.generateResult(referee);

        //then
        assertThat(roundResult.values()).containsExactly(HandResult.DRAW);
    }

    @DisplayName("블랙잭으로 플레이어가 이기는 게임의 최종 결과를 생성한다.")
    @Test
    void generateResultThatPlayerBlackjack() {
        //given
        List<Number> numbers = List.of(Number.ACE, Number.JACK, Number.QUEEN, Number.SIX, Number.FIVE);
        Deck deck = new CustomDeck(numbers);
        List<Name> playerName = List.of(new Name("mason"));
        Round round = new Round(playerName, deck);
        round.getDealer().addCard(deck);
        Referee referee = Referee.getInstance();

        //when
        Map<Player, HandResult> roundResult = round.generateResult(referee);

        //then
        assertThat(roundResult.values()).containsExactly(HandResult.BLACKJACK);
    }

    @DisplayName("블랙잭으로 딜러가 이기는 게임의 최종 결과를 생성한다.")
    @Test
    void generateResultThatDealerBlackjack() {
        //given
        List<Number> numbers = List.of(Number.ACE, Number.NINE, Number.QUEEN, Number.ACE, Number.ACE);
        Deck deck = new CustomDeck(numbers);
        List<Name> playerName = List.of(new Name("mason"));
        Round round = new Round(playerName, deck);
        round.getPlayers().getPlayerAtOrder().addCard(deck);
        Referee referee = Referee.getInstance();

        //when
        Map<Player, HandResult> roundResult = round.generateResult(referee);

        //then
        assertThat(roundResult.values()).containsExactly(HandResult.LOSE);
    }

    @DisplayName("블랙잭으로 비기는 게임의 최종 결과를 생성한다.")
    @Test
    void generateResultThatPlayerBlackjackDraw() {
        //given
        List<Number> numbers = List.of(Number.ACE, Number.JACK, Number.QUEEN, Number.ACE);
        Deck deck = new CustomDeck(numbers);
        List<Name> playerName = List.of(new Name("mason"));
        Round round = new Round(playerName, deck);
        Referee referee = Referee.getInstance();

        //when
        Map<Player, HandResult> roundResult = round.generateResult(referee);

        //then
        assertThat(roundResult.values()).containsExactly(HandResult.DRAW);
    }

    @DisplayName("버스트로 플레이어가 이기는 게임의 최종 결과를 생성한다.")
    @Test
    void generateResultThatDealerBust() {
        //given
        List<Number> numbers = List.of(Number.ACE, Number.NINE, Number.QUEEN, Number.SIX, Number.SIX);
        Deck deck = new CustomDeck(numbers);
        List<Name> playerName = List.of(new Name("mason"));
        Round round = new Round(playerName, deck);
        round.getDealer().addCard(deck);
        Referee referee = Referee.getInstance();

        //when
        Map<Player, HandResult> roundResult = round.generateResult(referee);

        //then
        assertThat(roundResult.values()).containsExactly(HandResult.WIN);
    }

    @DisplayName("버스트로 딜러가 이기는 게임의 최종 결과를 생성한다.")
    @Test
    void generateResultThatPlayerBust() {
        //given
        List<Number> numbers = List.of(Number.ACE, Number.NINE, Number.QUEEN, Number.NINE, Number.FIVE);
        Deck deck = new CustomDeck(numbers);
        List<Name> playerName = List.of(new Name("mason"));
        Round round = new Round(playerName, deck);
        round.getPlayers().getPlayerAtOrder().addCard(deck);
        Referee referee = Referee.getInstance();

        //when
        Map<Player, HandResult> roundResult = round.generateResult(referee);

        //then
        assertThat(roundResult.values()).containsExactly(HandResult.LOSE);
    }
}
