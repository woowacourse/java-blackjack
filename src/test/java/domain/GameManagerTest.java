package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import domain.card.CardGroup;
import domain.card.RandomCardGenerator;
import domain.fake.FaceCardGenerator;
import domain.fake.TwoScoreCardGenerator;
import domain.gamer.Dealer;
import domain.gamer.Player;
import java.util.List;
import org.junit.jupiter.api.Test;

public class GameManagerTest {

    @Test
    void 게임_매니저를_생성한다() {
        //given
        CardGroup cardGroup = new CardGroup();
        RandomCardGenerator randomCardGenerator = new RandomCardGenerator();
        final List<Player> players = List.of(
                new Player("윌슨",cardGroup,randomCardGenerator),
                new Player("가이온",cardGroup,randomCardGenerator));
        Dealer dealer = new Dealer(cardGroup,randomCardGenerator);

        //when
        final GameManager gameManager = GameManager.create(dealer,players);
        //then
        assertThat(gameManager).isInstanceOf(GameManager.class);
    }

    @Test
    void 딜러_점수가_16이하면_카드를_계속_뽑는다(){
        //given
        CardGroup cardGroup = new CardGroup();
        RandomCardGenerator randomCardGenerator = new RandomCardGenerator();
        final List<Player> players = List.of();
        Dealer dealer = new Dealer(cardGroup,randomCardGenerator);

        //when
        final GameManager gameManager = GameManager.create(dealer,players);
        //then
        assertThatCode(gameManager::receiveCardToDealer).doesNotThrowAnyException();
    }

    @Test
    void 버스트가_나기_전까지_카드를_더_받는다(){
        CardGroup cardGroup = new CardGroup();
        RandomCardGenerator randomCardGenerator = new RandomCardGenerator();
        final List<Player> players = List.of(
                new Player("윌슨",cardGroup,randomCardGenerator),
                new Player("가이온",cardGroup,randomCardGenerator));
        final Dealer dealer = new Dealer(cardGroup,randomCardGenerator);
        final GameManager gameManager = GameManager.create(dealer,players);
        final Player player = new Player("윌슨",cardGroup,randomCardGenerator);

        boolean isHitting = gameManager.isAbleToHit(player);

        assertThat(isHitting).isTrue();
    }

    @Test
    void 플레이어에게_카드를_추가한다() {
        //given
        CardGroup cardGroup = new CardGroup();
        RandomCardGenerator randomCardGenerator = new RandomCardGenerator();
        final List<Player> players = List.of(
                new Player("윌슨",cardGroup,randomCardGenerator),
                new Player("가이온",cardGroup,randomCardGenerator));
        final Dealer dealer = new Dealer(cardGroup,randomCardGenerator);
        final Player player = new Player("윌슨",cardGroup,randomCardGenerator);

        final GameManager gameManager = GameManager.create(dealer,players);


        assertThatCode(() -> gameManager.giveCardToGamer(player)).doesNotThrowAnyException();;
    }

    @Test
    void 플레이어가_버스트_하지_않고_딜러보다_점수가_높아야_승리(){
        //given
        CardGroup cardGroup1 = new CardGroup();
        CardGroup cardGroup2 = new CardGroup();
        CardGroup cardGroup3 = new CardGroup();

        final List<Player> players = List.of(
                new Player("윌슨",cardGroup1,new FaceCardGenerator()),
                new Player("가이온",cardGroup2,new FaceCardGenerator()));
        final Dealer dealer = new Dealer(cardGroup3,new TwoScoreCardGenerator());

        //when
        final GameManager gameManager = GameManager.create(dealer, players);
        gameManager.receiveCardToDealer();
        gameManager.giveCardToGamer(players.getFirst());
        gameManager.giveCardToGamer(players.getFirst());
        gameManager.giveCardToGamer(players.get(1));
        gameManager.giveCardToGamer(players.get(1));
        final GameResult gameResult1 = gameManager.calculateResult(0);
        final GameResult gameResult2 = gameManager.calculateResult(1);

        //then
        assertThat(gameResult1).isEqualTo(GameResult.WIN);
        assertThat(gameResult2).isEqualTo(GameResult.WIN);

    }

    @Test
    void 플레이어가_버스트_하는_경우_무조건_패배(){
        //given
        CardGroup cardGroup1 = new CardGroup();
        CardGroup cardGroup2 = new CardGroup();
        CardGroup cardGroup3 = new CardGroup();

        final List<Player> players = List.of(
                new Player("윌슨",cardGroup1,new FaceCardGenerator()),
                new Player("가이온",cardGroup2,new FaceCardGenerator()));
        final Dealer dealer = new Dealer(cardGroup3,new TwoScoreCardGenerator());

        //when
        final GameManager gameManager = GameManager.create(dealer, players);
        gameManager.receiveCardToDealer();
        gameManager.giveCardToGamer(players.getFirst());
        gameManager.giveCardToGamer(players.getFirst());
        gameManager.giveCardToGamer(players.getFirst());

        gameManager.giveCardToGamer(players.get(1));
        gameManager.giveCardToGamer(players.get(1));
        gameManager.giveCardToGamer(players.get(1));
        final GameResult gameResult1 = gameManager.calculateResult(0);
        final GameResult gameResult2 = gameManager.calculateResult(1);

        //then
        assertThat(gameResult1).isEqualTo(GameResult.LOSE);
        assertThat(gameResult2).isEqualTo(GameResult.LOSE);
    }

    @Test
    void 딜러와_플레이어의_점수가_같은_경우_무승부(){
        //given
        CardGroup cardGroup1 = new CardGroup();
        CardGroup cardGroup2 = new CardGroup();
        CardGroup cardGroup3 = new CardGroup();

        final List<Player> players = List.of(
                new Player("윌슨",cardGroup1,new FaceCardGenerator()),
                new Player("가이온",cardGroup2,new FaceCardGenerator()));
        final Dealer dealer = new Dealer(cardGroup3,new FaceCardGenerator());

        //when
        final GameManager gameManager = GameManager.create(dealer, players);
        gameManager.receiveCardToDealer();
        gameManager.giveCardToGamer(players.getFirst());
        gameManager.giveCardToGamer(players.getFirst());

        gameManager.giveCardToGamer(players.get(1));
        gameManager.giveCardToGamer(players.get(1));

        final GameResult gameResult1 = gameManager.calculateResult(0);
        final GameResult gameResult2 = gameManager.calculateResult(1);

        //then
        assertThat(gameResult1).isEqualTo(GameResult.DRAW);
        assertThat(gameResult2).isEqualTo(GameResult.DRAW);
    }

    @Test
    void 딜러와_플레이어가_서로_버스트한_경우_무승부(){
        //given
        CardGroup cardGroup1 = new CardGroup();
        CardGroup cardGroup2 = new CardGroup();
        CardGroup cardGroup3 = new CardGroup();

        final List<Player> players = List.of(
                new Player("윌슨",cardGroup1,new FaceCardGenerator()),
                new Player("가이온",cardGroup2,new FaceCardGenerator()));
        final Dealer dealer = new Dealer(cardGroup3,new FaceCardGenerator());

        //when
        final GameManager gameManager = GameManager.create(dealer, players);
        gameManager.receiveCardToDealer();
        gameManager.giveCardToGamer(dealer);

        gameManager.giveCardToGamer(players.getFirst());
        gameManager.giveCardToGamer(players.getFirst());
        gameManager.giveCardToGamer(players.getFirst());

        gameManager.giveCardToGamer(players.get(1));
        gameManager.giveCardToGamer(players.get(1));
        gameManager.giveCardToGamer(players.get(1));

        final GameResult gameResult1 = gameManager.calculateResult(0);
        final GameResult gameResult2 = gameManager.calculateResult(1);

        //then
        assertThat(gameResult1).isEqualTo(GameResult.DRAW);
        assertThat(gameResult2).isEqualTo(GameResult.DRAW);
    }

    @Test
    void 플레이어가_총_몇명인지_계산한다() {
        //given
        CardGroup cardGroup1 = new CardGroup();
        CardGroup cardGroup2 = new CardGroup();
        CardGroup cardGroup3 = new CardGroup();

        final List<Player> players = List.of(
                new Player("윌슨",cardGroup1,new FaceCardGenerator()),
                new Player("가이온",cardGroup2,new FaceCardGenerator()));
        final Dealer dealer = new Dealer(cardGroup3,new FaceCardGenerator());

        //when
        final GameManager gameManager = GameManager.create(dealer, players);
        final int size = gameManager.calculatePlayerSize();

        //then
        assertThat(size).isEqualTo(2);

    }

    @Test
    void 인덱스를_이용해서_플레이어를_가져온다() {
        //given
        CardGroup cardGroup1 = new CardGroup();
        CardGroup cardGroup2 = new CardGroup();
        CardGroup cardGroup3 = new CardGroup();

        final List<Player> players = List.of(
                new Player("윌슨",cardGroup1,new FaceCardGenerator()),
                new Player("가이온",cardGroup2,new FaceCardGenerator()));
        final Dealer dealer = new Dealer(cardGroup3,new FaceCardGenerator());

        //when
        final GameManager gameManager = GameManager.create(dealer, players);
        final Player player = gameManager.findPlayerByIndex(0);
        final String name = player.getName();

        //then
        assertThat(name).isEqualTo("윌슨");

    }
}
