package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Deck;
import blackjack.domain.card.HandGenerator;
import blackjack.domain.card.Number;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participants;
import blackjack.testutil.CustomDeck;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RefereeTest {
    @DisplayName("딜러가 이기는 게임의 최종 결과를 생성한다.")
    @Test
    void generateResultThatDealerWin() {
        //given
        List<Number> numbers =  List.of(Number.ACE, Number.SIX, Number.ACE, Number.EIGHT);
        Deck deck = new CustomDeck(numbers);
        HandGenerator handGenerator = new HandGenerator(deck);
        List<Name> playerName = List.of(new Name("gamza"));
        Participants participants = new Participants(playerName, handGenerator);
        Referee referee = Referee.getInstance();

        //when
        BlackjackResult blackjackResult = participants.generateResult(referee);

        //then
        assertThat(blackjackResult.getPlayersResult().values()).containsExactly(HandResult.LOSE);
    }

    @DisplayName("플레이어가 이기는 게임의 최종 결과를 생성한다.")
    @Test
    void generateResultThatPlayerWin() {
        //given
        List<Number> numbers =  List.of(Number.ACE, Number.KING, Number.ACE, Number.EIGHT);
        Deck deck = new CustomDeck(numbers);
        HandGenerator handGenerator = new HandGenerator(deck);
        List<Name> playerName = List.of(new Name("mason"));
        Participants participants = new Participants(playerName, handGenerator);
        Referee referee = Referee.getInstance();

        //when
        BlackjackResult blackjackResult = participants.generateResult(referee);

        //then
        assertThat(blackjackResult.getPlayersResult().values()).containsExactly(HandResult.WIN);
    }

    @DisplayName("비기는 게임의 최종 결과를 생성한다.")
    @Test
    void generateResultThatDraw() {
        //given
        List<Number> numbers =  List.of(Number.SEVEN, Number.JACK, Number.QUEEN, Number.SEVEN);
        Deck deck = new CustomDeck(numbers);
        HandGenerator handGenerator = new HandGenerator(deck);
        List<Name> playerName = List.of(new Name("pobi"));
        Participants participants = new Participants(playerName, handGenerator);
        Referee referee = Referee.getInstance();

        //when
        BlackjackResult blackjackResult = participants.generateResult(referee);

        //then
        assertThat(blackjackResult.getPlayersResult().values()).containsExactly(HandResult.DRAW);
    }
}
