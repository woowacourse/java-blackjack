package domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CloverCard;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("플레이어는 ")
class PlayerTest {
    @Test
    @DisplayName("처음에 2장의 카드를 받는다.")
    void generatePlayerTest() {
        //given
        List<Card> firstTurnCards = List.of(CloverCard.ACE, CloverCard.FIVE);
        Player player = new Player(new Name("플레이어"), firstTurnCards);

        //when
        List<Card> cards = player.getCards();

        //then
        assertThat(cards.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("카드 한 장을 받아 패에 넣는다.")
    void receiveCardTest() {
        //given
        List<Card> firstTurnCards = List.of(CloverCard.ACE, CloverCard.FIVE);
        Player player = new Player(new Name("플레이어"), firstTurnCards);

        //when
        player.receiveCard(CloverCard.FOUR);

        //then
        assertThat(player.getCards().size()).isEqualTo(3);
    }

    @ParameterizedTest(name = "{2} 상태값을 확인할 수 있다.")
    @MethodSource("isPlayerStatusTestCase")
    @DisplayName("자신의 상태값을 확인할 수 있다.")
    void isPlayerStatusTest(List<Card> firstTurnCards, Card drawCard, PlayerStatus playerStatus) {
        //given
        Player player = new Player(new Name("플레이어"), firstTurnCards);

        //when
        player.receiveCard(drawCard);

        //then
        assertThat(player.isUserStatus(playerStatus)).isTrue();

    }

    static Stream<Arguments> isPlayerStatusTestCase() {
        return Stream.of(
                Arguments.of(List.of(CloverCard.TEN, CloverCard.KING), CloverCard.QUEEN, Named.of("버스트", PlayerStatus.BUST)),
                Arguments.of(List.of(CloverCard.TWO, CloverCard.THREE), CloverCard.FOUR, Named.of("노멀", PlayerStatus.NORMAL))
        );
    }
}
