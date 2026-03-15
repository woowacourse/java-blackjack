package domain.participant;

import domain.card.Card;
import domain.card.Pattern;
import domain.card.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PlayerTest {

    PlayerInfo playerInfo;
    Hand dummyHand;
    String name;
    Money bettingMoney;

    @BeforeEach
    void init() {
        dummyHand = new Hand();
        name = "제발";
        bettingMoney = new Money(BigDecimal.valueOf(10000));
        playerInfo = new PlayerInfo(name, bettingMoney);
    }

    @Test
    @DisplayName("플레이어는 히트 시 카드를 뽑아 핸드에 저장한다")
    void 플레이어는_카드를_뽑아_핸드에_저장한다() {
        Player player = new Player(playerInfo);
        int beforeSize = player.handSize();
        player.keepCard(new Card(Rank.EIGHT, Pattern.CLOVER));
        int afterSize = player.handSize();

        assertThat(beforeSize + 1).isEqualTo(afterSize);
    }

    @Test
    @DisplayName("플레이어는 스탠드 시 카드를 뽑지 않고 핸드의 총합을 계산한다")
    void 플레이어는_카드를_뽑지_않고_카드_총합을_계산한다() {
        Player player = new Player(playerInfo);
        Card card1 = new Card(Rank.FOUR, Pattern.CLOVER);
        Card card2 = new Card(Rank.SIX, Pattern.CLOVER);
        player.keepCard(card1);
        player.keepCard(card2);

        int expectedScore = card1.getCardScore() + card2.getCardScore();
        int playerScore = player.getTotalCardScore();

        assertThat(playerScore).isEqualTo(expectedScore);
    }

    @Test
    void 플레이어는_배팅한_금액을_알고있다() {
        Player player = new Player(playerInfo);
        BigDecimal amount = player.getBettingMoney();

        assertThat(amount).isEqualTo(BigDecimal.valueOf(10000));
    }
}
