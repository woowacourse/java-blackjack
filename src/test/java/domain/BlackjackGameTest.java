package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.card.Card;
import domain.card.Number;
import domain.card.Suit;
import domain.participant.BettingMoney;
import domain.participant.Decision;
import domain.participant.Hand;
import domain.participant.Name;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    @Nested
    class 결과반환 {
        @Test
        void should_승패를판단하여수익을반환한다_when_calculateParticipantsRevenues호출시() {
            Players players = new Players(List.of(
                    new Player(new Name("포이"), new Hand(), new BettingMoney(1000)),
                    new Player(new Name("에밀"), new Hand(), new BettingMoney(2000))
            ));
            //given
            BlackjackGame blackjackGame = new BlackjackGame(players);
            blackjackGame.handOutInitialCards((cards) -> {
                cards.clear();
                cards.add(new Card(Suit.SPADE, Number.JACK));
                cards.add(new Card(Suit.SPADE, Number.TEN));
                cards.add(new Card(Suit.SPADE, Number.SEVEN));
                cards.add(new Card(Suit.SPADE, Number.NINE));
                cards.add(new Card(Suit.SPADE, Number.EIGHT));
                cards.add(new Card(Suit.SPADE, Number.TEN));
            });

            //when
            Map<String, Integer> outcome = blackjackGame.calculateParticipantsRevenues();

            //then
            assertAll(
                    () -> assertThat(outcome.get("포이")).isEqualTo(1000),
                    () -> assertThat(outcome.get("에밀")).isEqualTo(-2000)
            );
        }
    }
    @Nested
    class 게임진행 {
        @Test
        void should_이번턴의플레이어에게카드르준다_when_decision이HIT일시() {
            //given
            Players players = new Players(List.of(
                    new Player(new Name("포이"), new Hand(), new BettingMoney(1000)),
                    new Player(new Name("에밀"), new Hand(), new BettingMoney(2000))
            ));
            //given
            BlackjackGame blackjackGame = new BlackjackGame(players);
            blackjackGame.handOutInitialCards((cards) -> {
                cards.clear();
                cards.add(new Card(Suit.SPADE, Number.TWO));
                cards.add(new Card(Suit.SPADE, Number.THREE));
                cards.add(new Card(Suit.DIAMOND, Number.TWO));
                cards.add(new Card(Suit.DIAMOND, Number.THREE));
                cards.add(new Card(Suit.CLUB, Number.TWO));
                cards.add(new Card(Suit.CLUB, Number.THREE));
                cards.add(new Card(Suit.HEART, Number.TWO));
                cards.add(new Card(Suit.HEART, Number.THREE));
            });
            int expected = players.values().get(0).hand().size() + 1;

            //when
            blackjackGame.hitOrStand(Decision.HIT);
            int actual = players.values().get(0).hand().size();

            //then
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void should_현재플레이어가드로우를할수있더라도더이상카드를뽑지않는다_when_decision이STAND일시() {
            //given
            Player expected = new Player(new Name("에밀"), new Hand(), new BettingMoney(2000));
            Players players = new Players(List.of(
                    new Player(new Name("포이"), new Hand(), new BettingMoney(1000)),
                    expected)
            );
            //given
            BlackjackGame blackjackGame = new BlackjackGame(players);
            blackjackGame.handOutInitialCards((cards) -> {
                cards.clear();
                cards.add(new Card(Suit.SPADE, Number.TWO));
                cards.add(new Card(Suit.SPADE, Number.THREE));
                cards.add(new Card(Suit.DIAMOND, Number.TWO));
                cards.add(new Card(Suit.DIAMOND, Number.THREE));
                cards.add(new Card(Suit.CLUB, Number.TWO));
                cards.add(new Card(Suit.CLUB, Number.THREE));
                cards.add(new Card(Suit.HEART, Number.TWO));
                cards.add(new Card(Suit.HEART, Number.THREE));
            });

            //when
            blackjackGame.hitOrStand(Decision.STAND);
            Player actual = blackjackGame.findCurrentDrawablePlayer();

            //then
            assertThat(actual).isEqualTo(expected);
        }
    }
}