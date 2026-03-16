package blackjack.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BlackjackGameTest {
    private BlackjackGame createGame(String... names){
        return BlackjackGame.create(List.of(names), cards -> {});
    }

    @Test
    void 딜러는_2장을_가진다(){
        BlackjackGame game = createGame("pobi", "jason");

        game.deal();

        assertThat(game.getDealer().countCards()).isEqualTo(2);
    }

    @Test
    void 플레이어가_hit을_선택하면_카드를_받는다(){
        BlackjackGame game = createGame("dalsoo");
        game.deal();
        List<Integer> cardCounts = new ArrayList<>();

        game.playPlayerTurns(
                name -> true,
                (name, cards) -> cardCounts.add(cards.size())
        );
        assertThat(cardCounts).isSorted();
    }

    @Test
    void 플레이어가_stay를_선택하면_카드를_받지_않는다(){
        BlackjackGame game = createGame("dalsoo");
        game.deal();
        List<Integer> cardCounts = new ArrayList<>();

        game.playPlayerTurns(
                name -> false,
                (name, cards) -> cardCounts.add(cards.size())
        );

        assertThat(cardCounts).containsExactly(2);
    }

    @Test
    void 딜러는_16이하면_카드를_더_받는다(){
        BlackjackGame game = createGame("dalsoo");
        game.deal();
        game.playPlayerTurns(name -> false, (name, cards) -> {});
        game.playDealerTurn();

        assertThat(game.getDealer().canHit()).isFalse();
    }
}