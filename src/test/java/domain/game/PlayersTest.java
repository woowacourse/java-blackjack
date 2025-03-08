package domain.game;

import static org.assertj.core.api.Assertions.assertThatCode;

import domain.card.CardDeck;
import domain.card.CardShuffler;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

public class PlayersTest {

    @Test
    void 플레이어를_정상적으로_생성한다() {
        //given
        List<String> playerNames = List.of("aa", "bb", "cc", "dd", "ee");

        //when & then
        assertThatCode(() -> new Players(playerNames))
                .doesNotThrowAnyException();
    }

    @Test
    void 인원_수가_5명_초과이면_예외를_던진다() {
        //given
        List<String> playerNames = List.of("aa", "bb", "cc", "dd", "ee", "ff");

        //when & then
        AssertionsForClassTypes.assertThatThrownBy(() -> new Players(playerNames))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 인원_수가_0명이면_예외를_던진다() {
        //given
        List<String> playerNames = List.of();

        //when & then
        AssertionsForClassTypes.assertThatThrownBy(() -> new Players(playerNames))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 같은_이름의_플레이어가_여러_명_있으면_예외를_던진다() {
        //given
        List<String> playerNames = List.of("aa", "aa");

        //when & then
        AssertionsForClassTypes.assertThatThrownBy(() -> new Players(playerNames))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 모든_플레이어는_게임_시작_시_카드를_드로우한다() {
        //given
        List<String> playerNames = List.of("aa", "bb");
        Players players = new Players(playerNames);

        //when
        players.drawCardWhenStart(CardDeck.createCards(new CardShuffler()));

        //when
        Assertions.assertThat(players.getPlayers().get(0).getCardsCount()).isEqualTo(2);
        Assertions.assertThat(players.getPlayers().get(1).getCardsCount()).isEqualTo(2);
    }
}
