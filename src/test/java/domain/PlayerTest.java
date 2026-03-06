package domain;

import domain.model.*;
import domain.service.CardDistributor;
import domain.service.CardFactory;
import org.junit.jupiter.api.Test;
import repository.CardRepository;
import repository.DealerRepository;
import util.Parser;
import util.RandomRankNumberGenerator;
import util.RandomShapeNumberGenerator;
import util.StringParser;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PlayerTest {

    private final CardDistributor cardDistributor = new CardDistributor(
            new DealerRepository(),
            new CardFactory(
                    new CardRepository(),
                    new RandomRankNumberGenerator(),
                    new RandomShapeNumberGenerator()
            )
    );

    @Test
    void 플레이어_셍성_테스트() {
        // given
        String playerNamesInput = "phobi,jason";
        Parser parser = new StringParser();
        List<String> playerNames = parser.splitToDelimiter(playerNamesInput, ",");

        // when
        // 플레이어 리스트 = 플레이어 객체를 만드는 메소드 실행
        List<Player> players = playerNames.stream()
                .map(Player::of)
                .toList();

        // then
        assertThat(players).isNotNull();
        assertThat(players.size()).isEqualTo(2);
        assertThat(players.get(0).getName()).isEqualTo("phobi");
        assertThat(players.get(1).getName()).isEqualTo("jason");
    }

    @Test
    void 플레이어_덱_합산_테스트() {
        // given
        Player player = Player.of("phobi");
        List<Card> cards1 = List.of(
                Card.of(CardRank.TWO, CardShape.HEART),
                Card.of(CardRank.THREE, CardShape.CLUB),
                Card.of(CardRank.FOUR, CardShape.DIAMOND)
        );
        Deck deck1 = Deck.of(cards1);

        // when
        player.assignDeck(deck1);

        // then
        assertThat(player.getDeckSum()).isEqualTo(9);
    }
}
