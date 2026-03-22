package service;

import static org.assertj.core.api.Assertions.assertThat;

import constant.PlayerAction;
import constant.PolicyConstant;
import constant.Rank;
import constant.Suit;
import domain.bet.Money;
import domain.card.Card;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Player;
import domain.participant.Players;
import dto.BlackjackResultDto;
import dto.ParticipantDto;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BlackjackServiceTest {

    private BlackjackService blackjackService;
    private Players players;
    private Name firstPlayerName;
    private Name secondPlayerName;
    private Name thirdPlayerName;

    @BeforeEach
    void init() {
        firstPlayerName = new Name("aa");
        secondPlayerName = new Name("bb");
        thirdPlayerName = new Name("cc");

        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player(firstPlayerName, Money.from("1000")));
        playerList.add(new Player(secondPlayerName, Money.from("2000")));
        playerList.add(new Player(thirdPlayerName, Money.from("3000")));
        players = new Players(playerList);
        blackjackService = new BlackjackService(players);
    }

    @Nested
    class PlayerInfoTest {

        @Test
        void 참가자_정보를_정확히_조회할_수_있다() {

            // when
            List<Name> names = blackjackService.getPlayerNames();
            List<String> nameValues = blackjackService.getPlayerNameValues();
            int playerCount = blackjackService.getPlayerCount();
            String secondPlayerName = names.get(1).value();

            // then
            assertThat(nameValues).containsExactly("aa", "bb", "cc");
            assertThat(playerCount).isEqualTo(3);
            assertThat(secondPlayerName).isEqualTo("bb");
        }
    }

    @Nested
    class UpdatePlayerTest {

        @Test
        void 플레이어에게_카드를_한장_추가한다() {

            // given
            int beforeSize = blackjackService.createPlayerDto(firstPlayerName).hand().size();

            // when
            blackjackService.addCard(firstPlayerName);
            int afterSize = blackjackService.createPlayerDto(firstPlayerName).hand().size();

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
            List<BlackjackResultDto> allResultDtos = blackjackService.createBlackjackResultDto();

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
            int beforeSize = blackjackService.createBlackjackResultDto().getFirst().hand().size();

            // when
            boolean drewCard = blackjackService.drawDealerCard();
            int afterSize = blackjackService.createBlackjackResultDto().getFirst().hand().size();

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
            players.getPlayerByName(firstPlayerName).addCard(List.of(first));
            players.getPlayerByName(firstPlayerName).addCard(List.of(second));

            // when
            ParticipantDto actual = blackjackService.createPlayerDto(firstPlayerName);

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
            List<BlackjackResultDto> actual = blackjackService.createBlackjackResultDto();

            // then
            List<String> playerNames = blackjackService.getPlayerNameValues();
            List<String> expectedNames = List.of(
                PolicyConstant.DEALER_NAME,
                playerNames.get(0),
                playerNames.get(1),
                playerNames.get(2)
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
            addCards(players.getPlayerByName(firstPlayerName), Rank.TEN, Rank.SIX);

            // when
            boolean actual = blackjackService.shouldRepeat(firstPlayerName, PlayerAction.HIT);

            // then
            assertThat(actual).isTrue();
        }

        @Test
        void HIT이어도_버스트면_진행하지_않는다() {

            // given
            addCards(players.getPlayerByName(secondPlayerName), Rank.TEN, Rank.TEN, Rank.TWO);

            // when
            boolean actual = blackjackService.shouldRepeat(secondPlayerName, PlayerAction.HIT);

            // then
            assertThat(actual).isFalse();
        }

        @Test
        void STAND이면_버스트_여부와_무관하게_진행하지_않는다() {

            // given
            addCards(players.getPlayerByName(thirdPlayerName), Rank.TEN, Rank.SIX);

            // when
            boolean actual = blackjackService.shouldRepeat(thirdPlayerName, PlayerAction.STAND);

            // then
            assertThat(actual).isFalse();
        }
    }

    @Nested
    class CalculateBlackjackResultTest {

        @Test
        void 플레이어_결과별_수익과_딜러_수익을_계산한다() {

            // given
            addCards(players.getPlayerByName(firstPlayerName), Rank.TEN, Rank.TEN, Rank.TWO);
            addCards(players.getPlayerByName(secondPlayerName), Rank.TEN, Rank.NINE);
            addCards(players.getPlayerByName(thirdPlayerName), Rank.ACE, Rank.K);

            // when
            var actual = blackjackService.calculateBlackjackResult();

            // then
            assertThat(actual.dealerProfit()).isEqualTo(1000);
            assertThat(actual.playerResultDtoList())
                .extracting("name", "profit")
                .containsExactly(
                    Tuple.tuple("aa", -1000),
                    Tuple.tuple("bb", 2000),
                    Tuple.tuple("cc", 4500)
                );
        }

        @Test
        void 무승부는_0원_처리되고_딜러_수익에는_패배한_플레이어만_합산된다() {

            // given
            addCards(players.getPlayerByName(secondPlayerName), Rank.K, Rank.Q, Rank.TWO);

            // when
            var actual = blackjackService.calculateBlackjackResult();

            // then
            assertThat(actual.dealerProfit()).isEqualTo(2000);
            assertThat(actual.playerResultDtoList())
                .extracting("name", "profit")
                .containsExactly(
                    Tuple.tuple("aa", 0),
                    Tuple.tuple("bb", -2000),
                    Tuple.tuple("cc", 0)
                );
        }
    }

    private void addCards(Participant participant, Rank... ranks) {
        for (Rank rank : ranks) {
            participant.addCard(List.of(new Card(rank, Suit.HEART)));
        }
    }
}
