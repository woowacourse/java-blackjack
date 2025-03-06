package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TempPlayerRepositoryTest {

    private PlayerRepository playerRepository = PlayerRepository.getInstance();

    @BeforeEach
    void setUp() {
        playerRepository.clear();
    }


    @DisplayName("참여자 전원의 객체를 저장한다")
    @Test
    void test2() {
        //given
        List<Player> tempParticipants = List.of(
                new Player("mimi", Cards.createEmpty()),
                new Player("wade", Cards.createEmpty())
        );

        // when
        playerRepository.addAll(tempParticipants);

        // then
        assertThat(playerRepository.getAll()).containsAll(tempParticipants);
    }

    @DisplayName("참여자 이름을 통해 참여자를 조회한다")
    @Test
    void test3() {
        //given
        playerRepository.addAll(List.of(new Player("mimi", Cards.createEmpty())));

        //when
        Player player = playerRepository.getByName("mimi");

        //then
        assertThat(player).isEqualTo(new Player("mimi", Cards.createEmpty()));
    }

    @DisplayName("모든 참여자의 정보를 조회한다")
    @Test
    void test5() {
        //given
        List<Player> testTempParticipants = List.of(
                new Player("mimi", Cards.createEmpty()),
                new Player("wade", Cards.createEmpty()));

        List<Player> expectedTempParticipants = List.of(
                new Player("mimi", Cards.createEmpty()),
                new Player("wade", Cards.createEmpty()));
        playerRepository.addAll(testTempParticipants);

        //when
        List<Player> allTempParticipants = playerRepository.getAll();

        //then
        assertThat(allTempParticipants).containsExactlyInAnyOrderElementsOf(expectedTempParticipants);
    }
}
