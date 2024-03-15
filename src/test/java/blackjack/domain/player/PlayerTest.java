package blackjack.domain.player;

import static org.assertj.core.api.Assertions.assertThatCode;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardSymbol;
import blackjack.domain.card.CardValue;
import blackjack.domain.card.Cards;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    @DisplayName("이름과 카드 일급 컬렉션을 통해서 플레이어를 생성 한다.")
    public void GamePlayer_Instance_create_with_name_and_cards() {
        Name name = new Name("초롱");
        Cards cards = new Cards(List.of(new Card(CardValue.EIGHT, CardSymbol.CLOVER),
                new Card(CardValue.ACE, CardSymbol.DIAMOND)));
        BettingAmount battingAmount = new BettingAmount("10000");

        assertThatCode(() -> new GamePlayer(name, cards, battingAmount))
                .doesNotThrowAnyException();
    }

}
