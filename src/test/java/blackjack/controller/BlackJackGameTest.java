package blackjack.controller;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.CardDeck;
import blackjack.domain.cardGenerator.RandomCardGenerator;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;
import blackjack.dto.PlayerDto;
import blackjack.dto.PlayersDto;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {

    List<Player> gamblers;
    Player dealer;
    CardDeck cardDeck;

    @BeforeEach
    void setUp() {
        gamblers = new ArrayList<>();
        gamblers.add(new Gambler("돌범"));
        gamblers.add(new Gambler("리차드"));

        dealer = new Dealer();

        cardDeck = new CardDeck(new RandomCardGenerator());
    }

    @DisplayName("카드를 분배한 뒤, 딜러가 가진 카드가 2장인지 확인한다.")
    @Test
    void dealer_receive_cards_two() {
        //given
        final BlackJackGame blackJackGame = new BlackJackGame();

        //when
        blackJackGame.spreadCards(gamblers, dealer, cardDeck);
        final int receivedCardsSize = PlayerDto.from(dealer).getPlayingCards().size();

        //then
        assertThat(receivedCardsSize).isEqualTo(2);
    }

    @DisplayName("카드를 분배한 뒤, 겜블러 1명이 가진 카드가 2장인지 확인한다.")
    @Test
    void gambler_receive_cards_two() {
        //given
        final BlackJackGame blackJackGame = new BlackJackGame();

        //when
        blackJackGame.spreadCards(gamblers, dealer, cardDeck);
        final int receivedCardsSize = PlayersDto.from(gamblers).getValue().get(0).getPlayingCards().size();

        //then
        assertThat(receivedCardsSize).isEqualTo(2);
    }
}
