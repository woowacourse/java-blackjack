package blackjack.util;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Symbol;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import blackjack.view.dto.CardDto;
import blackjack.view.dto.PlayerDto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DtoAssemblerTest {

    @Test
    void createPlayerDto() {
        PlayerDto gamerDto = DtoAssembler.createPlayerDto(new Gamer("pobi", 1));
        assertThat(gamerDto.getScore()).isEqualTo(0);
        assertThat(gamerDto.getName()).isEqualTo("pobi");
        assertThat(gamerDto.getCardList()).isEqualTo(Collections.emptyList());

        PlayerDto dealerDto = DtoAssembler.createPlayerDto(new Dealer());

        assertThat(dealerDto.getScore()).isEqualTo(0);
        assertThat(dealerDto.getName()).isEqualTo("딜러");
        assertThat(dealerDto.getCardList()).isEqualTo(Collections.emptyList());
    }

    @Test
    void creatCardDto() {

        for (Symbol symbol : Symbol.values()) {
            for (CardNumber number : CardNumber.values()) {
                Card card = new Card(symbol, number);
                CardDto cardDto = DtoAssembler.creatCardDto(card);

                assertThat(cardDto.getName()).isEqualTo(card.getName());
            }

        }
    }

    @Test
    void createPlayerDtos() {
        Gamer[] gamer = {
                new Gamer("pobi",1 ),
                new Gamer("jason", 1)
        };


        Card[] cards = {
                new Card(Symbol.CLOVER, CardNumber.FIVE),
                new Card(Symbol.HEART, CardNumber.SIX)
        };

        for(int i=0; i<2; i++) {
            gamer[i].addCardToDeck(cards[i]);
        }

        List<PlayerDto> playerDtos = DtoAssembler.createPlayerDtos(Arrays.asList(
                gamer
        ));

        assertThat(playerDtos.size()).isEqualTo(2);

        for (int i = 0; i < 2; i++) {
            assertThat(playerDtos.get(i).getName()).isEqualTo(gamer[i].getName());
            assertThat(playerDtos.get(i).getScore()).isEqualTo(cards[i].getAccumulateScore());

            assertThat(playerDtos.get(i).getCardList().get(0).getName()).isEqualTo(cards[i].getName());

        }
    }
}