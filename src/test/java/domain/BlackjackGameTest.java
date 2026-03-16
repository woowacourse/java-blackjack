package domain;

import static domain.BlackjackGame.HIT_DRAW_COUNT;
import static domain.BlackjackGame.INIT_DRAW_COUNT;
import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardRank;
import domain.card.CardSuit;
import domain.card.Shuffler;
import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import domain.result.BetProfit;
import domain.result.BetProfits;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BlackjackGameTest {

    private static final int ONE_HUNDRED_THOUSAND = 100_000;

    @Test
    @DisplayName("처음 카드를 뽑는 경우 2장(INIT_DRAW_COUNT)을 뽑는다.")
    public void 첫_드로우_성공() {
        // given
        final BlackjackGame blackjackGame = new BlackjackGame(
                new Participants(List.of(new Player(new Name("zzaekkii"), ONE_HUNDRED_THOUSAND))));

        // when
        blackjackGame.initDraw();

        // then
        final Player zakie = blackjackGame.getParticipants().getPlayers().getFirst();
        final Dealer dealer = blackjackGame.getParticipants().getDealer();
        assertThat(zakie.getHand()).hasSize(INIT_DRAW_COUNT);
        assertThat(dealer.getHand()).hasSize(INIT_DRAW_COUNT);
    }

    @Test
    @DisplayName("Hit할 경우, 1장(HIT_DRAW_COUNT)을 뽑는다.")
    public void 히트_드로우_성공() {
        // given
        final Player player = new Player(new Name("zzaekkii"), ONE_HUNDRED_THOUSAND);
        final BlackjackGame blackjackGame = new BlackjackGame(
                new Participants(List.of(player)));
        blackjackGame.initDraw();

        // when
        blackjackGame.hit(player);
        final Participant dealer = blackjackGame.getParticipants().getDealer();
        blackjackGame.hit(dealer);

        // then
        assertThat(player.getHand()).hasSize(INIT_DRAW_COUNT + HIT_DRAW_COUNT);
        assertThat(dealer.getHand()).hasSize(INIT_DRAW_COUNT + HIT_DRAW_COUNT);
    }

    @ParameterizedTest
    @MethodSource("게임_결과_테스트케이스")
    @DisplayName("게임 결과에 따라 플레이어와 딜러 수익을 계산한다.")
    void 게임_결과에_따른_수익_계산(
            final Participants participants,
            final int expectedPlayerProfit,
            final int expectedDealerProfit
    ) {
        // given
        final BlackjackGame blackjackGame = new BlackjackGame(participants);

        // when
        final BetProfits results = blackjackGame.getBetProfits();

        // then
        final BetProfit playerResult = results.betProfits().getFirst();

        assertThat(playerResult.profit()).isEqualTo(expectedPlayerProfit);
        assertThat(results.dealerProfit().profit()).isEqualTo(expectedDealerProfit);
    }

    private static Stream<Arguments> 게임_결과_테스트케이스() {
        return Stream.of(
                Arguments.of(블랙잭_승리(), 150000, -150000),
                Arguments.of(일반_승리(), 100000, -100000),
                Arguments.of(패배(), -100000, 100000),
                Arguments.of(무승부(), 0, 0)
        );
    }

    private static Participants 블랙잭_승리() {
        final Player player = new Player(new Name("포비"), ONE_HUNDRED_THOUSAND);
        player.draw(new Card(CardSuit.DIAMOND, CardRank.ACE));        // 블랙잭
        player.draw(new Card(CardSuit.DIAMOND, CardRank.KING));

        final Participants participants = new Participants(new ArrayList<>(List.of(player)));

        final Participant dealer = participants.getDealer();        // 16
        dealer.draw(new Card(CardSuit.DIAMOND, CardRank.NINE));
        dealer.draw(new Card(CardSuit.DIAMOND, CardRank.SEVEN));

        return participants;
    }

    private static Participants 일반_승리() {
        final Player player = new Player(new Name("포비"), ONE_HUNDRED_THOUSAND);
        player.draw(new Card(CardSuit.DIAMOND, CardRank.TEN));        // 19
        player.draw(new Card(CardSuit.DIAMOND, CardRank.NINE));

        final Participants participants = new Participants(new ArrayList<>(List.of(player)));

        final Participant dealer = participants.getDealer();
        dealer.draw(new Card(CardSuit.DIAMOND, CardRank.SEVEN));       // 16
        dealer.draw(new Card(CardSuit.DIAMOND, CardRank.NINE));

        return participants;
    }

    private static Participants 패배() {
        final Player player = new Player(new Name("포비"), ONE_HUNDRED_THOUSAND);
        player.draw(new Card(CardSuit.DIAMOND, CardRank.EIGHT));        // 17
        player.draw(new Card(CardSuit.DIAMOND, CardRank.NINE));

        final Participants participants = new Participants(new ArrayList<>(List.of(player)));

        final Participant dealer = participants.getDealer();            // 20
        dealer.draw(new Card(CardSuit.DIAMOND, CardRank.TEN));
        dealer.draw(new Card(CardSuit.DIAMOND, CardRank.KING));

        return participants;
    }

    private static Participants 무승부() {
        final Player player = new Player(new Name("포비"), ONE_HUNDRED_THOUSAND);
        player.draw(new Card(CardSuit.DIAMOND, CardRank.TEN));          // 19
        player.draw(new Card(CardSuit.DIAMOND, CardRank.NINE));

        final Participants participants = new Participants(new ArrayList<>(List.of(player)));

        final Participant dealer = participants.getDealer();
        dealer.draw(new Card(CardSuit.DIAMOND, CardRank.KING));         // 19
        dealer.draw(new Card(CardSuit.DIAMOND, CardRank.NINE));

        return participants;
    }


    static class StubShuffler implements Shuffler {

        @Override
        public void shuffle(final List<Card> list) {
        }
    }
}
