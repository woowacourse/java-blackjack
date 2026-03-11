package domain;

import domain.model.Player;
import domain.service.CardDistributor;
import domain.service.CardFactory;
import org.junit.jupiter.api.Test;
import repository.CardRepository;
import repository.DealerRepository;
import util.RandomCardGenerator;
import util.RandomRankNumberGenerator;
import util.RandomShapeNumberGenerator;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CardDistributorTest {
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

    @Test
    void 추가_카드_분배_테스트() {
        // given
        Player phobi = Player.of("phobi");
        Player jason = Player.of("jason");
        List<Player> players = List.of(phobi, jason);
        cardDistributor.initialize(players);

        // when
        cardDistributor.distributeAdditionalCard(phobi);

        // then
        assertThat(phobi.getDeckSize()).isEqualTo(3);
    }

//    @Test
//    void 카드를_52장_이상_생성하는_경우_실패_확인() {
//        // given
//        Player phobi = Player.of("phobi");
//        cardDistributor.initialize(List.of(phobi));
//
//        // when & then
//        assertThatThrownBy(() ->{
//            for(int i=0; i<70; i++) {
//                cardDistributor.distributeAdditionalCard(phobi);
//            }
//        }).isInstanceOf(StackOverflowError.class);
//    }

    @Test
    void 카드를_52장_이상_생성하는_경우() {
        // given
        Player phobi = Player.of("phobi");
        cardDistributor.initialize(List.of(phobi));

        // when & then
        assertThatThrownBy(() ->{
            for(int i=0; i<70; i++) {
                cardDistributor.distributeAdditionalCard(phobi);
            }
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("카드는 52장을 초과할 수 없습니다.");
    }
}
