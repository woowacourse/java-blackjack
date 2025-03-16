package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.CardGroup;
import domain.card.CardScore;
import domain.card.CardType;
import domain.gamer.Dealer;
import domain.gamer.PlayerGroup;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class PlayerGroupTest {

    @Test
    void 플레이어가_버스트_하지_않고_딜러보다_점수가_높아야_승리() {
        //given
        final List<String> playerNames = List.of("윌슨", "가이온");
        final Dealer dealer = new Dealer(new CardGroup());

        //when
        final PlayerGroup playerGroup = PlayerGroup.of(playerNames);
        playerGroup.giveCardByName("윌슨", new Card(CardType.HEART, CardScore.ACE));
        playerGroup.giveCardByName("가이온", new Card(CardType.CLOVER, CardScore.ACE));
        final Map<String, GameResult> playersGameResult = playerGroup.calculatePlayersGameResult(dealer);

        //then
        assertThat(playersGameResult).containsEntry("윌슨", GameResult.WIN)
                .containsEntry("가이온", GameResult.WIN);
    }




    @Test
    void 딜러와_플레이어의_점수가_같은_경우_무승부() {
        //given
        final List<String> playerNames = List.of("윌슨", "가이온");
        final Dealer dealer = new Dealer(new CardGroup());

        //when
        final PlayerGroup playerGroup = PlayerGroup.of(playerNames);
        playerGroup.giveCardByName("윌슨", new Card(CardType.HEART, CardScore.ACE));
        dealer.giveCard(new Card(CardType.DIAMOND, CardScore.ACE));
        final Map<String, GameResult> playersGameResult = playerGroup.calculatePlayersGameResult(dealer);

        //then
        assertThat(playersGameResult).containsEntry("윌슨", GameResult.DRAW);
    }

    @Test
    void 플레이어가_버스트_라면_플레이어_패배() {
        //given
        final List<String> playerNames = List.of("윌슨");
        final Dealer dealer = new Dealer(new CardGroup());

        //when
        final PlayerGroup playerGroup = PlayerGroup.of(playerNames);
        playerGroup.giveCardByName("윌슨", new Card(CardType.HEART, CardScore.JACK));
        playerGroup.giveCardByName("윌슨",new Card(CardType.CLOVER, CardScore.QUEEN));
        playerGroup.giveCardByName("윌슨", new Card(CardType.DIAMOND, CardScore.TEN));
        dealer.giveCard(new Card(CardType.HEART, CardScore.JACK));
        dealer.giveCard(new Card(CardType.DIAMOND, CardScore.JACK));
        dealer.giveCard(new Card(CardType.SPADE, CardScore.JACK));
        final Map<String, GameResult> playersGameResult = playerGroup.calculatePlayersGameResult(dealer);

        //then
        assertThat(playersGameResult).containsEntry("윌슨", GameResult.LOSE);
    }

    @Test
    void 플레이어의_점수가_21을_초과할_경우_버스트() {
        //given
        final List<String> playerNames = List.of("윌슨");

        //when
        final PlayerGroup playerGroup = PlayerGroup.of(playerNames);
        playerGroup.giveCardByName("윌슨", new Card(CardType.HEART, CardScore.JACK));
        playerGroup.giveCardByName("윌슨",new Card(CardType.CLOVER, CardScore.QUEEN));
        playerGroup.giveCardByName("윌슨", new Card(CardType.DIAMOND, CardScore.TEN));
        final boolean result = playerGroup.canPlayerReceiveCard("윌슨");

        //then
        assertThat(result).isFalse();
    }
}
