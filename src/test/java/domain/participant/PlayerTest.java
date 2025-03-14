package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.TrumpNumber;
import domain.card.TrumpShape;
import domain.result.BlackjackResult;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PlayerTest {

    @Test
    void 이름으로부터_플레이어를_생성한다() {
        // given
        final String name = "pobi";

        // when & then
        assertThatCode(() -> Player.of(name, Money.of(1000)))
                .doesNotThrowAnyException();
    }

    @Test
    void 카드를_한_장_씩_받아_들고있는다() {
        // given
        final Player player = Player.of("pobi", Money.of(1000));
        final Card card = Card.of(
                TrumpNumber.ACE, TrumpShape.CLUB
        );

        // when & then
        assertThatCode(() -> player.receive(card))
                .doesNotThrowAnyException();
    }

    @Test
    void 플레이어의_점수를_반환한다() {
        // given
        final Player player = Player.of("pobi", Money.of(1000));
        final Card card = Card.of(
                TrumpNumber.ACE, TrumpShape.CLUB
        );
        player.receive(card);

        // when
        int score = player.getScore();

        // then
        assertThat(score).isEqualTo(11);
    }

    @MethodSource("createPlayerAndResult")
    @ParameterizedTest
    void 플레이어가_딜러와의_게임_결과를_반환한다(Player player, BlackjackResult result) {
        // given
        final Dealer dealer = Dealer.of(CardDeck.of());
        dealer.receive(Card.of(TrumpNumber.ACE, TrumpShape.CLUB));
        dealer.receive(Card.of(TrumpNumber.SIX, TrumpShape.CLUB));

        // when
        BlackjackResult blackjackResult = player.getBlackjackResult(dealer);

        // then
        assertThat(blackjackResult).isEqualTo(result);
    }

    @MethodSource("createPlayerAndCanHitResult")
    @ParameterizedTest
    void 플레이어가_히트할_수_있는지_판단한다(Player player, boolean expectedResult) {
        // when
        boolean result = player.canHit();

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void 플레이어가_이겨서_베팅_금액을_얻는다() {
        // given
        final Dealer dealer = Dealer.of(CardDeck.of());
        final Player player = Player.of("winner", Money.of(1000));

        // when
        player.win(dealer);

        // then
        assertThat(player.getTotalWinnings()).isEqualTo(1000);
    }

    @Test
    void 플레이어가_져서_베팅_금액을_잃는다() {
        // given
        final Dealer dealer = Dealer.of(CardDeck.of());
        final Player player = Player.of("winner", Money.of(1000));

        // when
        player.lose(dealer);

        // then
        assertThat(player.getTotalWinnings()).isEqualTo(-1000);
    }

    private static Stream<Arguments> createPlayerAndResult() {
        final Player loser = Player.of("pobi", Money.of(1000));
        loser.receive(Card.of(TrumpNumber.ACE, TrumpShape.CLUB));
        loser.receive(Card.of(TrumpNumber.FIVE, TrumpShape.CLUB));

        final Player drawer = Player.of("pobi", Money.of(1000));
        drawer.receive(Card.of(TrumpNumber.ACE, TrumpShape.CLUB));
        drawer.receive(Card.of(TrumpNumber.SIX, TrumpShape.CLUB));

        final Player winner = Player.of("pobi", Money.of(1000));
        winner.receive(Card.of(TrumpNumber.ACE, TrumpShape.CLUB));
        winner.receive(Card.of(TrumpNumber.SEVEN, TrumpShape.CLUB));

        return Stream.of(
                Arguments.of(loser, BlackjackResult.LOSE),
                Arguments.of(drawer, BlackjackResult.DRAW),
                Arguments.of(winner, BlackjackResult.WIN)
        );
    }

    public static Stream<Arguments> createPlayerAndCanHitResult() {
        final Player canHitPlayer = Player.of("pobi", Money.of(1000));
        canHitPlayer.receive(Card.of(TrumpNumber.ACE, TrumpShape.CLUB));
        canHitPlayer.receive(Card.of(TrumpNumber.FIVE, TrumpShape.CLUB));

        final Player canNotHitPlayer = Player.of("pobi", Money.of(1000));
        canNotHitPlayer.receive(Card.of(TrumpNumber.JACK, TrumpShape.CLUB));
        canNotHitPlayer.receive(Card.of(TrumpNumber.TEN, TrumpShape.CLUB));
        canNotHitPlayer.receive(Card.of(TrumpNumber.ACE, TrumpShape.CLUB));

        return Stream.of(
                Arguments.of(canHitPlayer, true),
                Arguments.of(canNotHitPlayer, false)
        );
    }
}
