package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import config.CardDeckFactory;
import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PlayersTest {
    @Test
    @DisplayName("카드 분배 테스트")
    void hitCardsTest(){
        //given
        Players players = Players.from(new ArrayList<>(
                List.of("pobi", "lisa")
        ));

        CardDeckFactory cardDeckFactory = new CardDeckFactory();
        CardDeck cardDeck = cardDeckFactory.create();

        Dealer dealer = new Dealer(cardDeck);

        //when-then

//        assertSoftly(softly -> {
//            softly.assertThat(playerList.getFirst().getCardDeck().getCardsSize()).isEqualTo(2);
//            softly.assertThat(playerList.get(1).getCardDeck().getCardsSize()).isEqualTo(2);
//        });

        assertDoesNotThrow(() -> players.hitCards(dealer));

    }

    @ParameterizedTest
    @DisplayName("플레이어 인원 검증 테스트")
    @MethodSource("provideListForPlayers")
    void validatePlayerNumbers(List<String> names) {
        assertThatThrownBy(() -> Players.from(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 플레이어 인원은 1~6명 입니다.");
    }

    private static Stream<Arguments> provideListForPlayers(){
        return Stream.of(Arguments.of(
                List.of("포비", "이든", "네오", "저스틴", "링크", "말론", "리사"),
                List.of()
        ));
    }

}
