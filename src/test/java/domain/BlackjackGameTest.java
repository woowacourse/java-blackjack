package domain;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
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

            final Map<String, Integer> bettingAmounts = new LinkedHashMap<>();
            bettingAmounts.put("포이", 1000);
            bettingAmounts.put("에밀", 1000);
            final BlackjackGame blackjackGame = new BlackjackGame();
            blackjackGame.setParticipants(bettingAmounts);
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
            final Map<String, Integer> outcome = blackjackGame.getPlayersEarnings();

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
            final Map<String, Integer> bettingAmounts = Map.of("에밀", 1000,
                    "포이", 500);
            final BlackjackGame blackjackGame = new BlackjackGame();
            blackjackGame.setParticipants(bettingAmounts);
            final ShuffleStrategy shuffleStrategy = (cards) -> {
                cards.clear();
                cards.add(Card.of(Suit.SPADE, Number.JACK));
                cards.add(Card.of(Suit.SPADE, Number.TEN));
                cards.add(Card.of(Suit.SPADE, Number.SEVEN));
                cards.add(Card.of(Suit.SPADE, Number.NINE));
                cards.add(Card.of(Suit.SPADE, Number.EIGHT));
                cards.add(Card.of(Suit.SPADE, Number.TEN));
            };
            final Predicate<Participant> expectedCondition = participant -> {
                final List<Card> cards = participant.hand.getCards();
                return cards.size() == 2;
            };
            final List<Participant> participants = blackjackGame.getParticipants();

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
            final Map<String, Integer> bettingAmounts = Map.of("에밀", 1000,
                    "포이", 500);
            final BlackjackGame blackjackGame = new BlackjackGame();
            blackjackGame.setParticipants(bettingAmounts);
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
            final Participant poi = blackjackGame.getParticipants()
                                                 .get(1);
            final Participant emil = blackjackGame.getParticipants()
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
            final Map<String, Integer> bettingAmounts = Map.of("에밀", 1000,
                    "포이", 500);
            final BlackjackGame blackjackGame = new BlackjackGame();
            blackjackGame.setParticipants(bettingAmounts);
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
            final List<Participant> participants = blackjackGame.getParticipants();

            //then
            assertAll(
                    () -> assertThat(participants.get(0)
                                                 .name()).isEqualTo("딜러"),
                    () -> assertThat(participants).hasSize(3)
            );
        }
    }

    @Nested
    class 수익금계산 {
        @Test
        void should_수익금을생성_when_배팅금액과게임결과를통해서() {
            // given
            final Map<String, Integer> bettingAmounts = new LinkedHashMap<>();
            bettingAmounts.put("에밀", 1000);
            bettingAmounts.put("포이", 1000);
            bettingAmounts.put("오리", 1000);
            bettingAmounts.put("연어", 1000);
            final BlackjackGame blackjackGame = new BlackjackGame();
            blackjackGame.setParticipants(bettingAmounts);
            blackjackGame.dealFirstHands((deck) -> {
                deck.clear();
                deck.add(Card.of(Suit.SPADE, Number.EIGHT));
                deck.add(Card.of(Suit.SPADE, Number.NINE));
                deck.add(Card.of(Suit.SPADE, Number.JACK));
                deck.add(Card.of(Suit.SPADE, Number.ACE));
                deck.add(Card.of(Suit.SPADE, Number.TEN));
                deck.add(Card.of(Suit.SPADE, Number.TEN));
                deck.add(Card.of(Suit.SPADE, Number.TEN));
                deck.add(Card.of(Suit.SPADE, Number.TEN));
                deck.add(Card.of(Suit.SPADE, Number.NINE));
                deck.add(Card.of(Suit.SPADE, Number.TEN));

            });

            // when
            final Map<String, Integer> earnings = blackjackGame.getPlayersEarnings();

            // then
            assertThat(earnings).containsEntry("에밀", 1500)
                                .containsEntry("포이", 1000)
                                .containsEntry("오리", 0)
                                .containsEntry("연어", -1000);
        }
    }
}