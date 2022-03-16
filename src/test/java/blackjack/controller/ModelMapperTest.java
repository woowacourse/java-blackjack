package blackjack.controller;

import static blackjack.domain.card.CardNumber.*;
import static blackjack.domain.card.CardSymbol.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.TestDeck;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.dto.ParticipantDto;

class ModelMapperTest {
    private static ParticipantDto dto;

    @BeforeAll
    static void setUp() {
        CardDeck cardDeck = new CardDeck(new TestDeck());
        Participant player = new Player(Name.of("pobi"));

        player.init(cardDeck);
        dto = ModelMapper.map(player);
    }

    @Test
    @DisplayName("Dto의 이름을 확인한다.")
    void name() {
        assertThat(dto.getName()).isEqualTo("pobi");
    }

    @Test
    @DisplayName("Dto의 카드 목록을 확인한다.")
    void cards() {
        assertThat(dto.getCards()).isEqualTo(Set.of(new Card(DIAMOND, QUEEN),
            new Card(CLUB, FIVE)));
    }

    @Test
    @DisplayName("Dto의 점수를 확인한다.")
    void score() {
        assertThat(dto.getScore()).isEqualTo(15);
    }
}
