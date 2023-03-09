package domain.player;


import domain.card.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    @DisplayName("카드를 출력한다")
    void createParticipant_thenGetCards() {
        //given
        final Player player = Participant.of("준팍", 0);
        final List<Card> deck = List.of(Card.of(Suit.DIAMOND, Rank.FIVE)
                , Card.of(Suit.SPADE, Rank.TWO)
                , Card.of(Suit.CLUBS, Rank.SIX));

        deck.forEach(player::takeCard);

        //when
        List<Card> cards = player.getCards();

        //then
        assertThat(cards)
                .isEqualTo(deck);
    }

    @Test
    @DisplayName("카드를 입력하면 스코어를 출력한다")
    void givenCards_thenReturnScore() {
        //given
        final Player player = Participant.of("범블비", 0);
        final List<Rank> ranks = List.of(Rank.FIVE, Rank.SIX, Rank.SEVEN);
        final Deck deck = Deck.from(TestCardGenerator.from(ranks));

        ranks.forEach(i -> player.takeCard(deck.dealCard()));

        //when
        final Score score = player.score();

        //then
        assertThat(score).isEqualTo(Score.from(18));
    }

    @Test
    @DisplayName("플레이어의 이름을 출력한다")
    void getNameTest() {
        //given
        final String name = "범블비";
        final Player player = Participant.of(name, 0);

        //when
        final String result = player.getName();

        //then
        assertThat(result).isEqualTo(name);
    }
}
