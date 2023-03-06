package domain;

import static fixture.DealerFixture.딜러;
import static fixture.PlayerFixture.빙봉;
import static fixture.PlayerFixture.우가;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

/**
 * @author 우가
 * @version 1.0.0
 * @Created by 우가 on 2023/03/06
 */
@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ParticipantsTest {

    @Test
    void 참가자들의_이름들을_반환한다() {
        Participants participants = new Participants(딜러, new Players(List.of(우가, 빙봉)));

        assertThat(participants.getNames()).containsExactly("딜러", "우가", "빙봉");
    }

    @Test
    void 참가자들이_가진_카드뭉치를_반환한다() {
        Participants participants = new Participants(딜러, new Players(List.of(우가, 빙봉)));

        assertThat(participants.getCards())
                .extracting(Cards::cardsToString)
                .containsExactly(List.of("A하트", "10하트"), List.of("A하트", "2하트"), List.of("A하트", "10하트"));
    }

    @Test
    void 참가자들중_플레이어를_반환한다() {
        Participants participants = new Participants(딜러, new Players(List.of(우가, 빙봉)));

        assertThat(participants.getPlayers()).contains(우가, 빙봉);
    }

    @Test
    void 참가자들중_딜러를_반환한다() {
        Participants participants = new Participants(딜러, new Players(List.of(우가, 빙봉)));

        assertThat(participants.getDealer()).isEqualTo(딜러);
    }

    @Test
    void 참가자들끼리의_우승결과를_반환한다() {
        Participants participants = new Participants(딜러, new Players(List.of(우가, 빙봉)));

        assertThat(participants.getWinningResult()).containsExactly(1, 0);
    }
}
