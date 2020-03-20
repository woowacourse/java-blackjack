package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.BettingMoney;
import blackjack.domain.Name;
import blackjack.domain.Names;
import blackjack.domain.card.CardDeck;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GamblersTest {

    private Gamblers gamblers;

    @BeforeEach
    void resetVariable() {
        Map<Name, BettingMoney> playerInfo = new LinkedHashMap<>();
        for (Name name : Names.of("jamie1,jamie2").getNames()) {
            playerInfo.put(name, BettingMoney.of("500"));
        }
        gamblers = new Gamblers(playerInfo);
    }

    @DisplayName("생성자 NULL일 경우 예외")
    @Test
    void create() {
        assertThatThrownBy(() -> new Gamblers(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("잘못");
    }

    @DisplayName("플레이어 최대인원 초과시 예외 발생")
    @Test
    void validMaximumPlayerCount() {
        Map<Name, BettingMoney> playerInfo = new LinkedHashMap<>();
        for (Name name : Names.of("jamie1,jamie2,jamie3,jamie4,jamie5,jamie6,jamie7,jamie8")
            .getNames()) {
            playerInfo.put(name, BettingMoney.of("500"));
        }
        assertThatThrownBy(() -> new Gamblers(playerInfo))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("참여 인원");
    }

    @DisplayName("플레이어들의 이름들을 받아오는 테스트")
    @Test
    void getPlayerNames() {
        assertThat(gamblers.getGamblesNames().get(0)).isEqualTo("jamie1");
        assertThat(gamblers.getGamblesNames().get(1)).isEqualTo("jamie2");
    }

    @DisplayName("플레이어 카드가 없다면 가져올 때 예외 발생")
    @Test
    void drawCard_CardZero_ThrownException() {
        for (Gambler gambler : gamblers.getGamblers()) {
            assertThatThrownBy(gambler::getCardsInfos)
                .isInstanceOf(NullPointerException.class).hasMessageContaining("없습니다.");
        }
    }

    @DisplayName("플레이어의 카드들을 순차적으로 뽑는 테스트")
    @Test
    void drawCard() {
        CardDeck cardDeck = new CardDeck();
        gamblers.drawCard(cardDeck, 1);

        for (Gambler gambler : gamblers.getGamblers()) {
            assertThat(gambler.getCardsInfos().size()).isEqualTo(1);
        }
    }
}
