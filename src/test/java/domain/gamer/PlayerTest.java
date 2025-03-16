package domain.gamer;

import domain.GameResult;
import domain.card.Card;
import domain.card.CardGenerator;
import domain.card.CardGroup;
import domain.card.RandomCardGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static domain.card.CardScore.*;
import static domain.card.CardType.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PlayerTest {
    @Test
    void 플레이어를_생성한다() {
        //given
        final String name = "윌슨";
        final List<Card> cards = List.of(new Card(DIAMOND, ACE), new Card(DIAMOND, EIGHT));
        final CardGroup cardGroup = new CardGroup(cards);
        CardGenerator randomCardGenerator = new RandomCardGenerator();

        //when
        final Player player = new Player(name, cardGroup);

        //then
        assertThat(player).isInstanceOf(Player.class);

    }

    @Test
    void 플레이어에게_카드를_준다() {
        //given
        final String name = "윌슨";
        final List<Card> cards = new ArrayList<>();
        final CardGroup cardGroup = new CardGroup(cards);

        //when
        final Player player = new Player(name, cardGroup);
        player.receiveCard(new Card(CLOVER, ACE));

        //then
        assertThat(cardGroup.getCards().size()).isEqualTo(1);
    }

    @Test
    void 플레이어의_게임_결과를_판단_한다() {
        //given
        final String name1 = "윌슨";
        final List<Card> cards1 = List.of(new Card(DIAMOND, TWO), new Card(CLOVER, FOUR));
        final CardGroup cardGroup1 = new CardGroup(cards1);

        final String name2 = "가이온";
        final List<Card> cards2 = List.of(new Card(DIAMOND, TEN), new Card(CLOVER, FIVE));
        final CardGroup cardGroup2 = new CardGroup(cards2);

        final String name3 = "포비";
        final List<Card> cards3 = List.of(new Card(DIAMOND, FIVE), new Card(HEART, TWO));
        final CardGroup cardGroup3 = new CardGroup(cards3);

        final RandomCardGenerator randomCardGenerator = new RandomCardGenerator();

        //when
        final Player player1 = new Player(name1, cardGroup1);
        final Player player2 = new Player(name2, cardGroup2);
        final Player player3 = new Player(name3, cardGroup3);

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

    @DisplayName("플레이어의 카드가 2장이고 합이 21인 경우 블랙잭이다.")
    @Test
    void isBlackJackTest() {
        String name = "가이온";
        CardGroup cardGroup = new CardGroup();
        Player blackJackPlayer = new Player(name, cardGroup);
        blackJackPlayer.receiveCard(new Card(CLOVER, ACE));
        blackJackPlayer.receiveCard(new Card(CLOVER, JACK));

        boolean isBlackJack = blackJackPlayer.isBlackJack();

        assertThat(isBlackJack).isTrue();
    }

    @DisplayName("플레이어의 카드가 3장이고 합이 21인 경우 블랙잭이 아니다.")
    @Test
    void isNotBlackJackTest1() {
        String name = "가이온";
        CardGroup cardGroup = new CardGroup();
        Player blackJackPlayer = new Player(name, cardGroup);
        blackJackPlayer.receiveCard(new Card(CLOVER, ACE));
        blackJackPlayer.receiveCard(new Card(CLOVER, TWO));
        blackJackPlayer.receiveCard(new Card(CLOVER, EIGHT));

        boolean isBlackJack = blackJackPlayer.isBlackJack();

        assertThat(isBlackJack).isFalse();
    }

    @DisplayName("플레이어의 카드가 2장이고 합이 21이 아닌 경우 블랙잭이 아니다.")
    @Test
    void isNotBlackJackTest2() {
        String name = "가이온";
        CardGroup cardGroup = new CardGroup();
        Player blackJackPlayer = new Player(name, cardGroup);
        blackJackPlayer.receiveCard(new Card(CLOVER, JACK));
        blackJackPlayer.receiveCard(new Card(CLOVER, TWO));

        boolean isNotBlackJack = blackJackPlayer.isBlackJack();

        assertThat(isNotBlackJack).isFalse();
    }
}
