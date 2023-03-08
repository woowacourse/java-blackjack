package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.participant.Participant;
import domain.participant.Participants;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {


    @Test
    void 딜러보다_점수가_높으면_WIN() {
        // given
        BlackjackGame blackjackGame = BlackjackGame.of(List.of("배럴"));
        blackjackGame.getDealer().addCard(new Card(Denomination.NINE, Suit.SPADE));
        List<Participant> players = blackjackGame.getPlayers();

        // when
        players.get(0).addCard(new Card(Denomination.ACE, Suit.SPADE));

        // then
        assertThat(blackjackGame.getResult()).contains(
                // 딜러 9 vs 배럴 ACE
                Map.entry("배럴", PlayerGameResult.WIN)
        );
    }

    @Test
    void 딜러와_점수가_같다면_DRAW() {
        // given
        BlackjackGame blackjackGame = BlackjackGame.of(List.of("둘리"));
        blackjackGame.getDealer().addCard(new Card(Denomination.NINE, Suit.SPADE));
        List<Participant> players = blackjackGame.getPlayers();

        // when
        players.get(0).addCard(new Card(Denomination.NINE, Suit.SPADE));

        // then
        assertThat(blackjackGame.getResult()).contains(
                // 딜러 9 vs 둘리 9
                Map.entry("둘리", PlayerGameResult.DRAW)
        );
    }

    @Test
    void 딜러보다_점수가_낮으면_LOSE() {
        // given
        BlackjackGame blackjackGame = BlackjackGame.of(List.of("패배자이름"));
        blackjackGame.getDealer().addCard(new Card(Denomination.NINE, Suit.SPADE));
        List<Participant> players = blackjackGame.getPlayers();

        // when
        players.get(0).addCard(new Card(Denomination.TWO, Suit.SPADE));

        // then
        assertThat(blackjackGame.getResult()).contains(
                // 딜러 9 vs 패배자 1
                Map.entry("패배자이름", PlayerGameResult.LOSE)
        );
    }
}