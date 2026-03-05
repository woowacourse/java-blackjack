package converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import domain.Player;
import dto.PlayersDto;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BlackjackConverterTest {

    private final BlackjackConverter blackjackConverter = new BlackjackConverter();

    @Nested
    class ConvertPlayersDtoTest {

        @Nested
        class Success {

            @Test
            void 게임_참가자_목록을_PlayersDto로_변환해야_한다() {

                // given
                List<Player> players = List.of(
                    new Player("jacob"),
                    new Player("seoye")
                );

                // when
                PlayersDto actual = blackjackConverter.convertPlayersDto(players);

                // then
                assertThat(actual.players())
                    .hasSize(2)
                    .containsExactlyElementsOf(players);
            }
        }

        @Nested
        class Fail {

            @Test
            void 변환된_PlayersDto의_목록은_수정할_수_없어야_한다() {

                // given
                List<Player> players = List.of(
                    new Player("jacob"),
                    new Player("seoye")
                );
                PlayersDto actual = blackjackConverter.convertPlayersDto(players);

                // when & then
                assertThatThrownBy(() -> actual.players().add(new Player("mike")))
                    .isInstanceOf(UnsupportedOperationException.class);
            }
        }
    }
}
