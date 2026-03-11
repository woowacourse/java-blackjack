package service;

import static org.assertj.core.api.Assertions.assertThat;

import constant.PlayerAction;
import constant.PolicyConstant;
import constant.Rank;
import constant.Result;
import constant.Suit;
import domain.Card;
import domain.participant.Names;
import domain.participant.Participant;
import domain.participant.Players;
import dto.BlackjackResultDto;
import dto.DealerResultDto;
import dto.ParticipantDto;
import dto.PlayerResultDto;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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
    class CalculatePlayerResultsTest {

        @Test
        void 참가자_결과는_참가자_수와_이름_순서를_유지한다() {

            // given
            blackjackService.dealInitialCards();

            // when
            List<PlayerResultDto> actual = blackjackService.calculatePlayerResults();

            // then
            assertThat(actual).hasSize(blackjackService.getPlayerCount());
            assertThat(actual.stream().map(PlayerResultDto::name).toList())
                .containsExactlyElementsOf(blackjackService.getAllPlayerNames());
        }
    }

    @Nested
    class CalculateDealerResultTest {

        @Test
        void 딜러_승무패_집계는_참가자_결과와_일관되어야_한다() {

            // given
            blackjackService.dealInitialCards();
            List<PlayerResultDto> playerResults = blackjackService.calculatePlayerResults();

            // when
            DealerResultDto actual = blackjackService.calculateDealerResult();

            // then
            int expectedWin = (int) playerResults.stream()
                .filter(playerResultDto -> playerResultDto.result() == Result.LOSE)
                .count();
            int expectedDraw = (int) playerResults.stream()
                .filter(playerResultDto -> playerResultDto.result() == Result.DRAW)
                .count();
            int expectedLose = (int) playerResults.stream()
                .filter(playerResultDto -> playerResultDto.result() == Result.WIN)
                .count();

            assertThat(actual.win()).isEqualTo(expectedWin);
            assertThat(actual.draw()).isEqualTo(expectedDraw);
            assertThat(actual.lose()).isEqualTo(expectedLose);
            assertThat(actual.win() + actual.draw() + actual.lose())
                .isEqualTo(blackjackService.getPlayerCount());
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

    private void addCards(Participant participant, Rank... ranks) {
        for (Rank rank : ranks) {
            participant.addCard(List.of(new Card(rank, Suit.HEART)));
        }
    }
}
