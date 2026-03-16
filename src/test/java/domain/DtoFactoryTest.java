package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import dto.GameInitialInfoDto;
import dto.GameScoreResultDto;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DtoFactoryTest {

    @Test
    void 초기정보를_생성하면_딜러는_한장만_공개된다() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Rank.ACE, Suit.SPADE));
        dealer.receiveCard(new Card(Rank.KING, Suit.HEART));

        Player player = new Player("pobi", new BettingMoney(1000));
        player.receiveCard(new Card(Rank.TWO, Suit.CLUB));
        player.receiveCard(new Card(Rank.THREE, Suit.DIAMOND));

        Players players = Players.of(List.of(player));

        List<GameInitialInfoDto> result = DtoFactory.toInitialInfo(dealer, players);

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getPlayerName()).isEqualTo("딜러");
        assertThat(result.get(0).getHand()).hasSize(1);
    }

    @Test
    void 초기정보를_생성하면_플레이어는_두장을_공개한다() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Rank.ACE, Suit.SPADE));
        dealer.receiveCard(new Card(Rank.KING, Suit.HEART));

        Player player = new Player("pobi", new BettingMoney(1000));
        player.receiveCard(new Card(Rank.TWO, Suit.CLUB));
        player.receiveCard(new Card(Rank.THREE, Suit.DIAMOND));

        Players players = Players.of(List.of(player));

        List<GameInitialInfoDto> result = DtoFactory.toInitialInfo(dealer, players);

        assertThat(result.get(1).getPlayerName()).isEqualTo("pobi");
        assertThat(result.get(1).getHand()).hasSize(2);
    }

    @Test
    void 점수결과를_생성하면_딜러와_플레이어의_이름과_점수_정보를_포함한다() {
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(Rank.TEN, Suit.SPADE));
        dealer.receiveCard(new Card(Rank.SEVEN, Suit.HEART));

        Player player = new Player("pobi", new BettingMoney(1000));
        player.receiveCard(new Card(Rank.NINE, Suit.CLUB));
        player.receiveCard(new Card(Rank.EIGHT, Suit.DIAMOND));

        Players players = Players.of(List.of(player));

        List<GameScoreResultDto> result = DtoFactory.toScoreResults(dealer, players);

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getPlayerName()).isEqualTo("딜러");
        assertThat(result.get(0).getHand()).hasSize(2);
        assertThat(result.get(0).getResult()).isEqualTo(17);

        assertThat(result.get(1).getPlayerName()).isEqualTo("pobi");
        assertThat(result.get(1).getHand()).hasSize(2);
        assertThat(result.get(1).getResult()).isEqualTo(17);
    }
}