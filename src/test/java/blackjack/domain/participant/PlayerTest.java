package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.game.MatchResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("provideParameters")
    @DisplayName("턴 강제 종료 여부")
    void player(String comment, Cards cards, boolean expect) {
        Player player = new Player(new Name("name"), cards);
        assertThat(player.isFinished()).isEqualTo(expect);
    }

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.arguments("합계 22인 경우 true",
                        new Cards(getCardList(Denomination.TWO, Denomination.QUEEN, Denomination.KING)), true),
                Arguments.arguments("합계 20인 경우 false",
                        new Cards(getCardList(Denomination.QUEEN, Denomination.KING)), false)
        );
    }

    private static List<Card> getCardList(Denomination... arguments) {
        List<Card> list = new ArrayList<>();
        for (Denomination denomination : arguments) {
            list.add(Card.valueOf(denomination, Suit.CLOVER));
        }
        return list;
    }

    @Test
    void drawCard() {
        Player player = new Player(new Name("name"), new Cards(getCardList(Denomination.QUEEN)));
        player.drawCard(Card.valueOf(Denomination.ACE, Suit.CLOVER));
        assertThat(player.getCards().getValue().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("플레이어와 딜러가 모두 블랙잭이면 DRAW")
    void match_draw() {
        Player player = new Player(new Name("name"),
                new Cards(getCardList(Denomination.ACE, Denomination.JACK)));
        Dealer dealer = new Dealer(new Name(Dealer.NAME),
                new Cards(getCardList(Denomination.ACE, Denomination.QUEEN)));

        assertThat(player.match(dealer)).isEqualTo(MatchResult.DRAW);
    }

    @Test
    @DisplayName("플레이어와 딜러가 모두 버스트가 아니고 숫자가 같으면 DRAW")
    void match_draw1() {
        Player player = new Player(new Name("name"),
                new Cards(getCardList(Denomination.ACE, Denomination.ACE)));
        Dealer dealer = new Dealer(new Name(Dealer.NAME),
                new Cards(getCardList(Denomination.ACE, Denomination.ACE)));

        assertThat(player.match(dealer)).isEqualTo(MatchResult.DRAW);
    }

    @Test
    @DisplayName("플레이어와 딜러가 모두 버스트이면 DRAW")
    void match_draw2() {
        Player player = new Player(new Name("name"),
                new Cards(getCardList(Denomination.QUEEN, Denomination.JACK, Denomination.KING)));
        Dealer dealer = new Dealer(new Name(Dealer.NAME),
                new Cards(getCardList(Denomination.QUEEN, Denomination.JACK, Denomination.KING)));

        assertThat(player.match(dealer)).isEqualTo(MatchResult.DRAW);
    }

    @Test
    @DisplayName("플레이어가 블랙잭이고 딜러가 블랙잭이 아니면 WIN")
    void match_win() {
        Player player = new Player(new Name("name"),
                new Cards(getCardList(Denomination.ACE, Denomination.JACK)));
        Dealer dealer = new Dealer(new Name(Dealer.NAME),
                new Cards(getCardList(Denomination.ACE, Denomination.TWO, Denomination.NINE)));

        assertThat(player.match(dealer)).isEqualTo(MatchResult.WIN);
    }

    @Test
    @DisplayName("플레이어가 버스트가 아니고 딜러가 버스트이면 WIN")
    void match_win1() {
        Player player = new Player(new Name("name"),
                new Cards(getCardList(Denomination.QUEEN)));
        Dealer dealer = new Dealer(new Name(Dealer.NAME),
                new Cards(getCardList(Denomination.QUEEN, Denomination.JACK, Denomination.KING)));

        assertThat(player.match(dealer)).isEqualTo(MatchResult.WIN);
    }

    @Test
    @DisplayName("플레이어와 딜러 둘다 버스트가 아니고 플레이어의 점수가 딜러 점수보다 높으면 WIN")
    void match_win2() {
        Player player = new Player(new Name("name"),
                new Cards(getCardList(Denomination.QUEEN, Denomination.JACK)));
        Dealer dealer = new Dealer(new Name(Dealer.NAME),
                new Cards(getCardList(Denomination.QUEEN)));

        assertThat(player.match(dealer)).isEqualTo(MatchResult.WIN);
    }

    @Test
    @DisplayName("플레이어가 블랙잭이 아니고 딜러가 블랙잭이면 LOSE")
    void match_lose() {
        Player player = new Player(new Name("name"),
                new Cards(getCardList(Denomination.ACE, Denomination.TWO, Denomination.NINE)));
        Dealer dealer = new Dealer(new Name(Dealer.NAME),
                new Cards(getCardList(Denomination.ACE, Denomination.JACK)));

        assertThat(player.match(dealer)).isEqualTo(MatchResult.LOSE);
    }

    @Test
    @DisplayName("플레이어가 버스트이고 딜러가 버스트가 아니면 LOSE")
    void match_lose1() {
        Player player = new Player(new Name("name"),
                new Cards(getCardList(Denomination.QUEEN, Denomination.JACK, Denomination.KING)));
        Dealer dealer = new Dealer(new Name(Dealer.NAME),
                new Cards(getCardList(Denomination.QUEEN)));

        assertThat(player.match(dealer)).isEqualTo(MatchResult.LOSE);
    }

    @Test
    @DisplayName("플레이어와 딜러 둘다 버스트가 아니고 플레이어의 점수가 딜러 점수보다 낮으면 LOSE")
    void match_lose2() {
        Player player = new Player(new Name("name"),
                new Cards(getCardList(Denomination.QUEEN)));
        Dealer dealer = new Dealer(new Name(Dealer.NAME),
                new Cards(getCardList(Denomination.QUEEN, Denomination.JACK)));

        assertThat(player.match(dealer)).isEqualTo(MatchResult.LOSE);
    }


}
