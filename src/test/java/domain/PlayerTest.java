package domain;

import domain.model.Player;
import domain.service.CardDistributor;
import domain.service.CardFactory;
import org.junit.jupiter.api.Test;
import repository.CardRepository;
import repository.DealerRepository;
import util.Parser;
import util.RandomCardGenerator;
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
                    new RandomCardGenerator(
                            new RandomRankNumberGenerator(),
                            new RandomShapeNumberGenerator()
                    )
            )
    );

    @Test
    void 플레이어_생성_테스트() {
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
        Player phobi = Player.of("phobi");
        Player jason = Player.of("jason");
        List<Player> players = List.of(phobi, jason);
        cardDistributor.initialize(players);

        // when
        cardDistributor.distributeAdditionalCard(phobi);
    }
}
