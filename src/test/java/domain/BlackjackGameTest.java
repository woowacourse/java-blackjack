package domain;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class BlackjackGameTest {

    @Nested
    class 결과반환 {
        @Test
        void should_승패를판단하여반환한다_when_getPlayerOutcome호출시() {
            //given
            BlackjackGame blackjackGame = new BlackjackGame();
            blackjackGame.setParticipants(List.of("포이", "에밀"));
            blackjackGame.makeBet(Map.of("포이", 1000, "에밀", 1000));
            blackjackGame.dealFirstHands((cards) -> {
                cards.clear();
                cards.add(Card.of(Suit.SPADE, Number.JACK));
                cards.add(Card.of(Suit.SPADE, Number.TEN));
                cards.add(Card.of(Suit.SPADE, Number.SEVEN));
                cards.add(Card.of(Suit.SPADE, Number.NINE));
                cards.add(Card.of(Suit.SPADE, Number.EIGHT));
                cards.add(Card.of(Suit.SPADE, Number.TEN));
            });

            //when
            Map<String, Integer> outcome = blackjackGame.getPlayersEarnings();

            //then
            assertAll(
                    () -> assertThat(outcome).containsEntry("포이", 1000),
                    () -> assertThat(outcome).containsEntry("에밀", -1000)
            );
        }
    }

    @Nested
    class 게임초기세팅 {
        @Test
        void should_첫패를나눠준다_when_dealFirstHands호출시() {
            //given
            BlackjackGame blackjackGame = new BlackjackGame();
            blackjackGame.setParticipants(List.of("포이", "에밀"));
            ShuffleStrategy shuffleStrategy = (cards) -> {
                cards.clear();
                cards.add(Card.of(Suit.SPADE, Number.JACK));
                cards.add(Card.of(Suit.SPADE, Number.TEN));
                cards.add(Card.of(Suit.SPADE, Number.SEVEN));
                cards.add(Card.of(Suit.SPADE, Number.NINE));
                cards.add(Card.of(Suit.SPADE, Number.EIGHT));
                cards.add(Card.of(Suit.SPADE, Number.TEN));
            };
            Predicate<Participant> expectedCondition = participant -> {
                List<Card> cards = participant.hand.getCards();
                return cards.size() == 2;
            };
            List<Participant> participants = blackjackGame.getParticipants();

            //when
            blackjackGame.dealFirstHands(shuffleStrategy);

            //then
            assertThat(participants).allMatch(expectedCondition);
        }
    }

    @Nested
    class hit와stand {
        @Test
        void should_hit나stand를시킨다_when_hitOrStand호출시() {
            //given
            BlackjackGame blackjackGame = new BlackjackGame();
            blackjackGame.setParticipants(List.of("포이", "에밀"));
            blackjackGame.dealFirstHands((cards) -> {
                cards.clear();
                cards.add(Card.of(Suit.SPADE, Number.TWO));
                cards.add(Card.of(Suit.SPADE, Number.JACK));
                cards.add(Card.of(Suit.SPADE, Number.TEN));
                cards.add(Card.of(Suit.SPADE, Number.SEVEN));
                cards.add(Card.of(Suit.SPADE, Number.NINE));
                cards.add(Card.of(Suit.SPADE, Number.EIGHT));
                cards.add(Card.of(Suit.SPADE, Number.TEN));
            });
            Participant poi = blackjackGame.getParticipants()
                                           .get(1);
            Participant emil = blackjackGame.getParticipants()
                                            .get(2);

            //when
            blackjackGame.hitOrStand(HitOrStand.STAND);
            blackjackGame.hitOrStand(HitOrStand.HIT);

            //then
            assertAll(
                    () -> assertThat((Player) poi).extracting("hitOrStand")
                                                  .isEqualTo(HitOrStand.STAND),
                    () -> assertThat((Player) emil).extracting("hitOrStand")
                                                  .isEqualTo(HitOrStand.HIT)
            );
        }
    }

    @Nested
    class 딜러와플레이어반환하기 {
        @Test
        void should_승패를판단하여반환한다_when_getPlayerOutcome호출시() {
            //given
            BlackjackGame blackjackGame = new BlackjackGame();
            blackjackGame.setParticipants(List.of("포이", "에밀"));
            blackjackGame.dealFirstHands((cards) -> {
                cards.clear();
                cards.add(Card.of(Suit.SPADE, Number.JACK));
                cards.add(Card.of(Suit.SPADE, Number.TEN));
                cards.add(Card.of(Suit.SPADE, Number.SEVEN));
                cards.add(Card.of(Suit.SPADE, Number.NINE));
                cards.add(Card.of(Suit.SPADE, Number.EIGHT));
                cards.add(Card.of(Suit.SPADE, Number.TEN));
            });

            //when
            List<Participant> participants = blackjackGame.getParticipants();

            //then
            assertAll(
                    () -> assertThat(participants.get(0).name()).isEqualTo("딜러"),
                    () -> assertThat(participants).hasSize(3)
            );
        }
    }
}