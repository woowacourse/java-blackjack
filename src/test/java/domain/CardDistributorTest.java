package domain;

import domain.model.Player;
import domain.service.BlackJackService;
import domain.service.CardDistributor;
import domain.service.CardFactory;
import org.junit.jupiter.api.Test;
import repository.CardRepository;
import repository.DealerRepository;
import util.RandomRankNumberGenerator;
import util.RandomShapeNumberGenerator;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CardDistributorTest {
    private final CardDistributor cardDistributor = new CardDistributor(
            new DealerRepository(),
            new CardFactory(
                    new CardRepository(),
                    new RandomRankNumberGenerator(),
                    new RandomShapeNumberGenerator()
            )
    );


    @Test
    void 초기_카드_분배_테스트() {
        // given
        Player phobi = Player.of("phobi");
        Player jason = Player.of("jason");

        List<Player> players = List.of(phobi, jason);

        // when
        cardDistributor.initialize(players);

        // then
        assertThat(players.get(0).getDeckSize()).isEqualTo(2);
        assertThat(players.get(1).getDeckSize()).isEqualTo(2);
        assertThat(cardDistributor.getDealer().getDeckSize()).isEqualTo(2);
    }
}
