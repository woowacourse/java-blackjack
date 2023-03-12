package domain.user;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CloverCard;

import java.util.List;
import java.util.stream.Stream;

import domain.state.State;
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
        Player player = new Player(new Name("player"));
        player.receiveCards(List.of(CloverCard.ACE, CloverCard.FIVE));

        //when
        List<Card> cards = player.getCards();

        //then
        assertThat(cards.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("카드 한 장을 받아 패에 넣는다.")
    void receiveCardTest() {
        //given
        Player player = new Player(new Name("player"));
        player.receiveCards(List.of(CloverCard.ACE, CloverCard.FIVE));

        //when
        player.receiveCard(CloverCard.FOUR);

        //then
        assertThat(player.getCards().size()).isEqualTo(3);
    }
}
