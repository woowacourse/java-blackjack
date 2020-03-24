package domain.gamer.dto;

import domain.card.*;
import domain.gamer.Dealer;
import domain.gamer.Player;
import domain.gamer.Players;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class GamerCardsWithScoreAssemblerTest {
    private GamerCardsWithScoreDto gamerCardsWithScoreDto;

    @BeforeEach
    void setUp() {
        PlayingCards playingCards = new PlayingCards(Collections.singletonList(new Card(Symbol.QUEEN, Type.CLOVER)));
        gamerCardsWithScoreDto = GamerCardsWithScoreAssembler.create(new Player(playingCards, "testName"));
    }

    @Test
    @DisplayName("생성 테스트")
    void of() {
        assertThat(GamerCardsAssembler.create(new Player(new PlayingCards(Collections.emptyList()), "testName"))).isNotNull();
    }

    @Test
    @DisplayName("List Dto 생성 테스트")
    void createDtos() {
        Deck deck = DeckFactory.create();
        Dealer dealer = new Dealer(deck.dealInitCards());
        Players players = Players.valueOf(deck, Arrays.asList(new GamerMoneyDto("a", 0),
                new GamerMoneyDto("b", 0),
                new GamerMoneyDto("c", 0)));
        assertThat(GamerCardsWithScoreAssembler.createDtos(dealer, players)).isNotNull();
    }
}
