package domain;

import static domain.card.CardScore.*;
import static domain.card.CardType.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.ArrayList;
import java.util.List;

import domain.card.*;
import domain.fake.AceCardGenerator;
import domain.gamer.Player;
import org.junit.jupiter.api.Test;

public class PlayerTest {


    @Test
    void 플레이어를_생성한다() {
        //given
        final String name = "윌슨";
        final List<Card> cards = List.of(new Card(DIAMOND, ACE), new Card(DIAMOND, EIGHT));
        final CardGroup cardGroup = new CardGroup(cards);
        CardGenerator randomCardGenerator = new RandomCardGenerator();

        //when
        final Player player = new Player(name, cardGroup, randomCardGenerator);

        //then
        assertThat(player).isInstanceOf(Player.class);

    }

    @Test
    void 플레이어에게_카드를_준다() {
        //given
        final String name = "윌슨";
        final List<Card> cards = new ArrayList<>();
        final CardGenerator cardGenerator = new AceCardGenerator();
        final CardGroup cardGroup = new CardGroup(cards);

        //when
        final Player player = new Player(name, cardGroup, cardGenerator);
        player.receiveCard();

        //then
        assertThat(cardGroup.getCards().size()).isEqualTo(1);
    }

    @Test
    void 플레이어의_게임_결과를_판단_한다() {
        //given
        final String name1 = "윌슨";
        final List<Card> cards1 = List.of(new Card(DIAMOND,TWO), new Card(CLOVER,FOUR));
        final CardGroup cardGroup1 = new CardGroup(cards1);

        final String name2 = "가이온";
        final List<Card> cards2 = List.of(new Card(DIAMOND,TEN), new Card(CLOVER,FIVE));
        final CardGroup cardGroup2 = new CardGroup(cards2);

        final String name3 = "포비";
        final List<Card> cards3 = List.of(new Card(DIAMOND,FIVE), new Card(HEART,TWO));
        final CardGroup cardGroup3 = new CardGroup(cards3);

        final RandomCardGenerator randomCardGenerator = new RandomCardGenerator();

        //when
        final Player player1 = new Player(name1, cardGroup1, randomCardGenerator);
        final Player player2 = new Player(name2, cardGroup2, randomCardGenerator);
        final Player player3 = new Player(name3, cardGroup3, randomCardGenerator);

        final GameResult gameResult1 = player1.calculateGameResult(7);
        final GameResult gameResult2 = player2.calculateGameResult(7);
        final GameResult gameResult3 = player3.calculateGameResult(7);

        //then
        assertAll(
                () -> assertThat(gameResult1).isEqualTo(GameResult.LOSE),
                () -> assertThat(gameResult2).isEqualTo(GameResult.WIN),
                () -> assertThat(gameResult3).isEqualTo(GameResult.DRAW)
        );
    }
}
