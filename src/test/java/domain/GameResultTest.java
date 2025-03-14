package domain;

import domain.card.Card;
import domain.card.CardGroup;
import domain.card.CardScore;
import domain.card.CardType;
import domain.gamer.Dealer;
import domain.gamer.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResultTest {
    @DisplayName("플레이어 20점 vs 딜러 18점")
    @Test
    void 플레이어가_버스트_하지_않고_딜러보다_점수가_높아야_승리() {
        //given
        CardGroup cardGroup1 = new CardGroup();
        CardGroup cardGroup2 = new CardGroup();
        CardGroup cardGroup3 = new CardGroup();

        cardGroup1.addCard(new Card(CardType.CLOVER, CardScore.JACK));
        cardGroup1.addCard(new Card(CardType.CLOVER, CardScore.JACK));
        cardGroup2.addCard(new Card(CardType.CLOVER, CardScore.TEN));
        cardGroup2.addCard(new Card(CardType.CLOVER, CardScore.JACK));
        cardGroup3.addCard(new Card(CardType.CLOVER, CardScore.ACE));
        cardGroup3.addCard(new Card(CardType.CLOVER, CardScore.EIGHT));

        final List<Player> players = List.of(
                new Player("윌슨", cardGroup1),
                new Player("가이온", cardGroup2));

        final Dealer dealer = new Dealer(cardGroup3);

        //then
        assertThat(GameResult.calculateResult(dealer, players.get(0))).isEqualTo(GameResult.WIN);
        assertThat(GameResult.calculateResult(dealer, players.get(1))).isEqualTo(GameResult.WIN);
    }

    @DisplayName("Face 카드 3장을 받아 30점의 경우 버스트하여 패배")
    @Test
    void 플레이어가_버스트_하는_경우_무조건_패배() {
        //given
        CardGroup cardGroup1 = new CardGroup();
        CardGroup cardGroup3 = new CardGroup();

        cardGroup1.addCard(new Card(CardType.CLOVER, CardScore.JACK));
        cardGroup1.addCard(new Card(CardType.CLOVER, CardScore.JACK));
        cardGroup1.addCard(new Card(CardType.CLOVER, CardScore.JACK));


        final Player player1 = new Player("윌슨", cardGroup1);
        final Dealer dealer = new Dealer(cardGroup3);

        //then
        assertThat(GameResult.calculateResult(dealer, player1)).isEqualTo(GameResult.LOSE);
    }

    @Test
    void 딜러와_플레이어의_점수가_같은_경우_무승부() {
        //given
        CardGroup cardGroup1 = new CardGroup();
        CardGroup cardGroup3 = new CardGroup();

        final Player player1 = new Player("윌슨", cardGroup1);
        final Dealer dealer = new Dealer(cardGroup3);

        //then
        assertThat(GameResult.calculateResult(dealer, player1)).isEqualTo(GameResult.DRAW);
    }

    @Test
    void 딜러와_플레이어가_서로_버스트한_경우_무승부() {
        //given
        CardGroup cardGroup1 = new CardGroup();
        CardGroup cardGroup3 = new CardGroup();

        cardGroup1.addCard(new Card(CardType.CLOVER, CardScore.JACK));
        cardGroup1.addCard(new Card(CardType.CLOVER, CardScore.JACK));
        cardGroup1.addCard(new Card(CardType.CLOVER, CardScore.JACK));

        cardGroup3.addCard(new Card(CardType.CLOVER, CardScore.JACK));
        cardGroup3.addCard(new Card(CardType.CLOVER, CardScore.JACK));
        cardGroup3.addCard(new Card(CardType.CLOVER, CardScore.JACK));

        final Player player1 = new Player("윌슨", cardGroup1);

        final Dealer dealer = new Dealer(cardGroup3);

        assertThat(GameResult.calculateResult(dealer, player1)).isEqualTo(GameResult.DRAW);
    }
}
