package domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import domain.state.GameState;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class GameResultTest {

    private static final String GUMP = "gump";

    Hand normalHand = Hand.of(
            new Card(CardShape.다이아몬드, CardContents.A),
            new Card(CardShape.다이아몬드, CardContents.TWO)
    );
    Hand blackJackHand = Hand.of(
            new Card(CardShape.클로버, CardContents.A),
            new Card(CardShape.클로버, CardContents.TEN)
    );
    Hand handThatValue16 = Hand.of(
            new Card(CardShape.스페이드, CardContents.TEN),
            new Card(CardShape.스페이드, CardContents.SIX)
    );

    @Nested
    class decidePlayerResultTest {
        @Nested
        class blackJackCaseTest {

            @Test
            @DisplayName("플레이어가 블랙잭이면 플레이어가 이긴 결과를 도출한다")
            void decidePlayerResult_player_blackjack() {
                //given
                Dealer testDealer = Dealer.from(GameState.createInitialGameState(normalHand));
                Player testPlayer = Player.from(GUMP, GameState.createInitialGameState(blackJackHand));

                //when
                GameResult result = GameResult.decidePlayerResult(testPlayer, testDealer);

                //then
                assertEquals(GameResult.승, result);
            }

            @Test
            @DisplayName("딜러가 블랙잭이면 플레이어가 진 결과를 도출한다")
            void decidePlayerResult_dealer_blackjack() {
                //given
                Dealer testDealer = Dealer.from(GameState.createInitialGameState(blackJackHand));
                Player testPlayer = Player.from(GUMP, GameState.createInitialGameState(normalHand));

                //when
                GameResult result = GameResult.decidePlayerResult(testPlayer, testDealer);

                //then
                assertEquals(GameResult.패, result);
            }

            @Test
            @DisplayName("딜러, 플레이어 둘 다가 블랙잭이면 플레이어가 무승부라는 결과를 도출한다")
            void decidePlayerResult_both_blackjack() {
                //given
                Hand anotherBlackJackHand = Hand.of(
                        new Card(CardShape.하트, CardContents.A),
                        new Card(CardShape.하트, CardContents.TEN)
                );

                Dealer testDealer = Dealer.from(GameState.createInitialGameState(blackJackHand));
                Player testPlayer = Player.from(GUMP, GameState.createInitialGameState(anotherBlackJackHand));

                //when
                GameResult result = GameResult.decidePlayerResult(testPlayer, testDealer);

                //then
                assertEquals(GameResult.무, result);
            }
        }

        @Nested
        class bustCaseTest {
            Queue<Card> onlyTwoTenCards = new LinkedList<>(
                    List.of(
                            new Card(CardShape.다이아몬드, CardContents.TEN),
                            new Card(CardShape.다이아몬드, CardContents.Q)
                    )
            );
            CardCreationStrategy testStrategy = () -> new ArrayDeque<>(onlyTwoTenCards);
            Deck testTotalDeck = Deck.createDeck(testStrategy);

            @Test
            @DisplayName("플레이어가 bust 이면 패 결과를 반환")
            void player_bust_case() {
                //given

                Player testPlayer = Player.from(GUMP, GameState.createInitialGameState(handThatValue16));
                Dealer testDealer = Dealer.from(GameState.createInitialGameState(normalHand));
                testPlayer = testPlayer.hit(testTotalDeck::drawCard);

                //when
                GameResult result = GameResult.decidePlayerResult(testPlayer, testDealer);

                //then
                assertEquals(GameResult.패, result);
            }

            @Test
            @DisplayName("딜러가 bust 이면 승 결과를 반환")
            void dealer_bust_case() {
                //given
                Player testPlayer = Player.from(GUMP, GameState.createInitialGameState(normalHand));
                Dealer testDealer = Dealer.from(GameState.createInitialGameState(handThatValue16));

                testDealer = testDealer.addCard(testTotalDeck::drawCard);

                //when
                GameResult result = GameResult.decidePlayerResult(testPlayer, testDealer);

                //then
                assertEquals(GameResult.승, result);
            }
        }
    }

    @Nested
    class reverseTest {
        @Test
        @DisplayName("승 -> 패")
        void reverseToLose() {
            assertEquals(GameResult.패, GameResult.승.reverse());
        }

        @Test
        @DisplayName("패 -> 승")
        void reverseToWin() {
            assertEquals(GameResult.승, GameResult.패.reverse());
        }

        @Test
        @DisplayName("무 -> 무")
        void reverseToDraw() {
            assertEquals(GameResult.무, GameResult.무.reverse());
        }
    }

}