package domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import domain.card.Card;
import domain.card.CardDeck;
import domain.card.TrumpNumber;
import domain.card.TrumpShape;
import domain.result.GameResult;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PlayerTest {

    @Test
    void 이름으로부터_플레이어를_생성한다() {
        // given
        final String name = "pobi";

        // when & then
        assertThatCode(() -> Player.of(name))
                .doesNotThrowAnyException();
    }

    @Test
    void 플레이어의_이름이_같다면_같은_객체임을_반환한다() {
        // given
        Player pobi1 = Player.of("pobi");
        Player pobi2 = Player.of("pobi");
        Player pobis = Player.of("pobis");

        // when
        boolean isEqual = pobi1.equals(pobi2);
        boolean isNotEqual = pobi1.equals(pobis);

        // then
        assertThat(isEqual).isTrue();
        assertThat(isNotEqual).isFalse();
    }

    @Test
    void 카드를_한_장_씩_받아_들고있는다() {
        // given
        final Player player = Player.of("pobi");
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
        final Player player = Player.of("pobi");
        final Card card = Card.of(
                TrumpNumber.ACE, TrumpShape.CLUB
        );
        player.receive(card);

        // when
        int score = player.calculateScore();

        // then
        assertThat(score).isEqualTo(11);
    }

    @MethodSource("createPlayerAndResult")
    @ParameterizedTest
    void 플레이어가_딜러와의_게임_결과를_반환한다(Player player, GameResult result) {
        // given
        List<Card> cards = List.of(
                Card.of(TrumpNumber.ACE, TrumpShape.CLUB),
                Card.of(TrumpNumber.SIX, TrumpShape.CLUB)
        );
        CardDeck cardDeck = CardDeck.of(cards);
        final Dealer dealer = Dealer.of();
        dealer.receive(cardDeck.popCard());
        dealer.receive(cardDeck.popCard());

        // when
        GameResult playerResult = player.determineBlackjackResult(dealer);

        // then
        assertThat(playerResult).isEqualTo(result);
    }

    private static Stream<Arguments> createPlayerAndResult() {
        final Player loser = Player.of("pobi");
        loser.receive(Card.of(TrumpNumber.ACE, TrumpShape.CLUB));
        loser.receive(Card.of(TrumpNumber.FIVE, TrumpShape.CLUB));

        final Player drawer = Player.of("pobi");
        drawer.receive(Card.of(TrumpNumber.ACE, TrumpShape.CLUB));
        drawer.receive(Card.of(TrumpNumber.SIX, TrumpShape.CLUB));

        final Player winner = Player.of("pobi");
        winner.receive(Card.of(TrumpNumber.ACE, TrumpShape.CLUB));
        winner.receive(Card.of(TrumpNumber.SEVEN, TrumpShape.CLUB));

        return Stream.of(
                Arguments.of(loser, GameResult.LOSE),
                Arguments.of(drawer, GameResult.DRAW),
                Arguments.of(winner, GameResult.WIN)
        );
    }
}
