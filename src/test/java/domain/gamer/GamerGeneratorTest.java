package domain.gamer;

import domain.card.RandomCardGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GamerGeneratorTest {
    @Test
    void 플레이어_생성_테스트(){
        List<String> playerNames = List.of("윌슨","가이온");
        List<Player> playerList = GamerGenerator.generatePlayer(playerNames,new RandomCardGenerator());

        assertThat(playerList).hasSize(2);
    }

    @Test
    void 딜러_생성_테스트(){
        Dealer dealer = GamerGenerator.generateDealer(new RandomCardGenerator());

        assertThat(dealer).isInstanceOf(Dealer.class);
    }
}