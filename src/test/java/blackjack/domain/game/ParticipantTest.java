package blackjack.domain.game;

import static blackjack.domain.card.CardNumber.*;
import static blackjack.domain.card.CardSymbol.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.Name;
import blackjack.domain.TestDeck;
import blackjack.domain.card.Card;
import blackjack.domain.state.Bet;
import blackjack.domain.state.Blackjack;
import blackjack.domain.state.Bust;
import blackjack.domain.state.Running;
import blackjack.domain.state.Started;
import blackjack.domain.state.State;

public class ParticipantTest {

    private static final Bet BET = new Bet(1);

    @Test
    @DisplayName("생성한 참여자의 상태가 Started인지 확인")
    void checkStarted() {
        //given
        Participant participant = new Player(Name.of("pobi"), BET);

        //when
        State actual = participant.getState();

        //then
        assertThat(actual).isInstanceOf(Started.class);
    }

    @Test
    @DisplayName("초기화 후 참여자의 상태가 Running인지 확인")
    void checkRunning() {
        //given
        Participant participant = new Player(Name.of("pobi"), BET);

        //when
        participant.init(new Card(DIAMOND, JACK),
            new Card(HEART, JACK));
        State actual = participant.getState();

        //then
        assertThat(actual).isInstanceOf(Running.class);
    }

    @Test
    @DisplayName("초기화 후 카드 합이 21이면 상태가 Blackjack인지 확인")
    void checkBlackjack() {
        //given
        Participant participant = new Player(Name.of("pobi"), BET);

        //when
        participant.init(new Card(DIAMOND, JACK),
            new Card(HEART, ACE));
        State actual = participant.getState();

        //then
        assertThat(actual).isInstanceOf(Blackjack.class);
    }

    @Test
    @DisplayName("게임준비를 위해 가진 카드를 초기화한다.")
    void init() {
        // give
        CardDeck cardDeck = new CardDeck(new TestDeck());
        Participant player = new Player(Name.of("pobi"), BET);

        // when
        player.init(cardDeck.drawCard(), cardDeck.drawCard());
        int actual = player.getScore();

        // then
        assertThat(actual).isEqualTo(15);
    }

    @Test
    @DisplayName("카드의 합이 21을 초과하면 BUST를 반환한다.")
    void returnBust() {
        // give
        Participant player = new Player(Name.of("pobi"), BET);
        player.draw(new Card(DIAMOND, JACK));
        player.draw(new Card(DIAMOND, QUEEN));
        player.draw(new Card(DIAMOND, TWO));

        // when
        State actual = player.getState();

        // then
        assertThat(actual).isInstanceOf(Bust.class);
    }

    @Test
    @DisplayName("카드 목록 반환을 검증한다.")
    void getCards() {
        // give
        Participant player = new Player(Name.of("pobi"), BET);
        CardDeck cardDeck = new CardDeck(new TestDeck());

        // when
        player.init(cardDeck.drawCard(), cardDeck.drawCard());
        Set<Card> actual = player.getCards().getValue();

        // then
        assertThat(actual).isEqualTo(Set.of(new Card(DIAMOND, QUEEN), new Card(CLUB, FIVE)));
    }
}
