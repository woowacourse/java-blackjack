package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("참가자 리스트 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ParticipantsTest {

    @Test
    void 플레이어별_이름과_카드리스트의_총합을_반환한다() {
        ParticipantName nameOfDrago = new ParticipantName("drago");
        Cards cardsOfDrago = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.EIGHT),
                        new Card(Suit.DIAMOND, Rank.JACK),
                        new Card(Suit.HEART, Rank.FOUR)));
        Participant drago = new Player(nameOfDrago, cardsOfDrago);

        ParticipantName nameOfDuei = new ParticipantName("duei");
        Cards cardsOfDuei = new Cards(
                List.of(new Card(Suit.DIAMOND, Rank.EIGHT),
                        new Card(Suit.DIAMOND, Rank.JACK),
                        new Card(Suit.HEART, Rank.TWO)));
        Participant duei = new Player(nameOfDuei, cardsOfDuei);

        Participants participants = new Participants(List.of(drago, duei));
        Map<Participant, Integer> expected = Map.of(drago, 22, duei, 20);

        assertThat(participants.getTotalRankSumByPlayer()).isEqualTo(expected);
    }
}
