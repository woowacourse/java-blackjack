package service;

import static org.assertj.core.api.Assertions.assertThat;

import constant.PlayerAction;
import constant.PolicyConstant;
import constant.Rank;
import constant.Suit;
import domain.bet.Money;
import domain.card.Card;
import domain.participant.Names;
import domain.participant.Participant;
import domain.participant.Players;
import dto.BlackjackResultDto;
import dto.ParticipantDto;
import java.util.List;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BlackjackServiceTest {

    private BlackjackService blackjackService;
    private Players players;

    @BeforeEach
    void init() {
        players = Players.from(Names.from("aa,bb,cc"));
        blackjackService = new BlackjackService(players);
    }

    @Nested
    class PlayerInfoTest {

        @Test
        void 참가자_정보를_정확히_조회할_수_있다() {

            // when
            List<String> names = blackjackService.getAllPlayerNames();
            int playerCount = blackjackService.getPlayerCount();
            String secondPlayerName = blackjackService.getPlayerName(1);

            // then
            assertThat(names).containsExactly("aa", "bb", "cc");
            assertThat(playerCount).isEqualTo(3);
            assertThat(secondPlayerName).isEqualTo("bb");
        }
    }

    @Nested
    class UpdatePlayerTest {

        @Test
        void 플레이어에게_카드를_한장_추가한다() {

            // given
            int beforeSize = blackjackService.createPlayerDto(0).hand().size();

            // when
            blackjackService.updatePlayer(0);
            int afterSize = blackjackService.createPlayerDto(0).hand().size();

            // then
            assertThat(afterSize).isEqualTo(beforeSize + 1);
        }
    }

    @Nested
    class DealInitialCardsTest {

        @Test
        void 초기_분배시_참가자는_두장_딜러_오픈카드는_한장이다() {

            // when
            blackjackService.dealInitialCards();
            List<ParticipantDto> participantDtos = blackjackService.getAllPlayerDto();
            ParticipantDto dealerOpenCardDto = blackjackService.getDealerPlayerDto();
            List<BlackjackResultDto> allResultDtos = blackjackService.generateBlackjackResultDto();

            // then
            assertThat(participantDtos).allSatisfy(participantDto -> assertThat(participantDto.hand()).hasSize(2));
            assertThat(dealerOpenCardDto.hand()).hasSize(1);
            assertThat(allResultDtos.getFirst().hand()).hasSize(2);
        }
    }

    @Nested
    class DrawDealerCardTest {

        @Test
        void 딜러가_카드를_뽑았는지_반환값과_손패_크기가_일치한다() {

            // given
            blackjackService.dealInitialCards();
            int beforeSize = blackjackService.generateBlackjackResultDto().getFirst().hand().size();

            // when
            boolean drewCard = blackjackService.drawDealerCard();
            int afterSize = blackjackService.generateBlackjackResultDto().getFirst().hand().size();

            // then
            if (drewCard) {
                assertThat(afterSize).isEqualTo(beforeSize + 1);
                return;
            }
            assertThat(afterSize).isEqualTo(beforeSize);
        }
    }

    @Nested
    class CreateParticipantDtoTest {

        @Test
        void 참가자_dto는_참가자_이름과_손패를_반영한다() {

            // given
            Card first = new Card(Rank.TEN, Suit.HEART);
            Card second = new Card(Rank.ACE, Suit.SPADE);
            players.getPlayerByIndex(0).addCard(List.of(first));
            players.getPlayerByIndex(0).addCard(List.of(second));

            // when
            ParticipantDto actual = blackjackService.createPlayerDto(0);

            // then
            assertThat(actual.name()).isEqualTo("aa");
            assertThat(actual.hand()).containsExactly(first.getName(), second.getName());
        }
    }

    @Nested
    class GenerateBlackjackResultDtoTest {

        @Test
        void 블랙잭_결과에는_딜러와_모든_참가자의_이름_손패_점수가_포함된다() {

            // given
            blackjackService.dealInitialCards();

            // when
            List<BlackjackResultDto> actual = blackjackService.generateBlackjackResultDto();

            // then
            List<String> expectedNames = List.of(
                PolicyConstant.DEALER_NAME,
                blackjackService.getAllPlayerNames().get(0),
                blackjackService.getAllPlayerNames().get(1),
                blackjackService.getAllPlayerNames().get(2)
            );

            assertThat(actual).hasSize(blackjackService.getPlayerCount() + 1);
            assertThat(actual.stream().map(BlackjackResultDto::name).toList())
                .containsExactlyElementsOf(expectedNames);
            assertThat(actual).allSatisfy(resultDto -> {
                assertThat(resultDto.hand()).isNotEmpty();
                assertThat(resultDto.score()).isGreaterThanOrEqualTo(0);
            });
        }
    }

    @Nested
    class ShouldRepeatTest {

        @Test
        void HIT이고_버스트가_아니면_한번_더_진행한다() {

            // given
            addCards(players.getPlayerByIndex(0), Rank.TEN, Rank.SIX);

            // when
            boolean actual = blackjackService.shouldRepeat(0, PlayerAction.HIT);

            // then
            assertThat(actual).isTrue();
        }

        @Test
        void HIT이어도_버스트면_진행하지_않는다() {

            // given
            addCards(players.getPlayerByIndex(1), Rank.TEN, Rank.TEN, Rank.TWO);

            // when
            boolean actual = blackjackService.shouldRepeat(1, PlayerAction.HIT);

            // then
            assertThat(actual).isFalse();
        }

        @Test
        void STAND이면_버스트_여부와_무관하게_진행하지_않는다() {

            // given
            addCards(players.getPlayerByIndex(2), Rank.TEN, Rank.SIX);

            // when
            boolean actual = blackjackService.shouldRepeat(2, PlayerAction.STAND);

            // then
            assertThat(actual).isFalse();
        }
    }

    @Nested
    class CalculateBlackjackResultTest {

        @Test
        void 플레이어_결과별_수익과_딜러_수익을_계산한다() {

            // given
            blackjackService.receivePlayerBets(0, Money.from("1000"));
            blackjackService.receivePlayerBets(1, Money.from("2000"));
            blackjackService.receivePlayerBets(2, Money.from("3000"));

            addCards(players.getPlayerByIndex(0), Rank.TEN, Rank.TEN, Rank.TWO);
            addCards(players.getPlayerByIndex(1), Rank.TEN, Rank.NINE);
            addCards(players.getPlayerByIndex(2), Rank.ACE, Rank.K);

            // when
            var actual = blackjackService.calculateBlackjackResult();

            // then
            assertThat(actual.dealerProfit()).isEqualTo(1000);
            assertThat(actual.playerResultDtoList())
                .extracting("name", "profit")
                .containsExactly(
                    Tuple.tuple("aa", -1000.0),
                    Tuple.tuple("bb", 2000.0),
                    Tuple.tuple("cc", 4500.0)
                );
        }

        @Test
        void 무승부는_0원_처리되고_딜러_수익에는_패배한_플레이어만_합산된다() {

            // given
            blackjackService.receivePlayerBets(0, Money.from("1000"));
            blackjackService.receivePlayerBets(1, Money.from("2000"));
            blackjackService.receivePlayerBets(2, Money.from("3000"));

            addCards(players.getPlayerByIndex(1), Rank.K, Rank.Q, Rank.TWO);

            // when
            var actual = blackjackService.calculateBlackjackResult();

            // then
            assertThat(actual.dealerProfit()).isEqualTo(2000);
            assertThat(actual.playerResultDtoList())
                .extracting("name", "profit")
                .containsExactly(
                    Tuple.tuple("aa", 0.0),
                    Tuple.tuple("bb", -2000.0),
                    Tuple.tuple("cc", 0.0)
                );
        }
    }

    @Nested
    class ReceivePlayerBetsTest {

        @Nested
        class Success {

            @ParameterizedTest
            @ValueSource(ints = {0, 1, 2})
            void 플레이어_인덱스에_맞게_베팅금액이_저장된다(int playerIndex) {

                // given
                Money betAmount = Money.from("1000");

                // when
                blackjackService.receivePlayerBets(playerIndex, betAmount);

                // then
                assertThat(players.getPlayerByIndex(playerIndex).getBetAmount()).isEqualTo(1000);
            }

        }
    }

    private void addCards(Participant participant, Rank... ranks) {
        for (Rank rank : ranks) {
            participant.addCard(List.of(new Card(rank, Suit.HEART)));
        }
    }
}
