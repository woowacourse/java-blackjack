package blackjack.model.result;

import blackjack.model.card.Card;
import blackjack.model.card.CardDeck;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardSuit;
import blackjack.model.participant.*;
import blackjack.model.state.InitialState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ResultTest {

    @Test
    @DisplayName("플레이어가 딜러를 이겼을 때 WIN 을 반환한다.")
    void player_win() {
        //given
        Card card1 = Card.of(CardSuit.CLUB, CardNumber.ACE);
        Card card2 = Card.of(CardSuit.HEART, CardNumber.JACK);
        Card card3 = Card.of(CardSuit.CLUB, CardNumber.TWO);
        Card card4 = Card.of(CardSuit.HEART, CardNumber.THREE);
        CardDeck cardDeck = new CardDeck(List.of(card1, card2, card3, card4));
        Dealer dealer = new Dealer(new InitialState(new Hand()));
        Player player = new Player(new Name("이리내"), new InitialState(new Hand()));

        //when
        dealer.play(cardDeck);
        player.play(cardDeck);
        Result result = Result.checkPlayerResult(player, dealer);

        //then
        assertThat(result).isEqualTo(Result.WIN);
    }

    @Test
    @DisplayName("플레이어와 딜러가 비겼을 때 TIE 를 반환한다.")
    void player_tie() {
        //given
        Card card1 = Card.of(CardSuit.CLUB, CardNumber.FOUR);
        Card card2 = Card.of(CardSuit.HEART, CardNumber.FOUR);
        Card card3 = Card.of(CardSuit.CLUB, CardNumber.THREE);
        Card card4 = Card.of(CardSuit.HEART, CardNumber.TEN);
        Card card5 = Card.of(CardSuit.DIAMOND, CardNumber.THREE);
        Card card6 = Card.of(CardSuit.SPADE, CardNumber.TEN);
        CardDeck cardDeck = new CardDeck(List.of(card1, card2, card3, card4, card5, card6));
        Dealer dealer = new Dealer(new InitialState(new Hand()));
        Player player = new Player(new Name("이리내"), new InitialState(new Hand()));
        Participants participants = new Participants(dealer, List.of(player));

        //when
        participants.distributeTwoCardsToEach(cardDeck);
        participants.hitOrStandByPlayer(cardDeck, player, true);
        participants.hitOrStandByPlayer(cardDeck, player, false);
        participants.hitOrStandByDealer(cardDeck);
        Result result = Result.checkPlayerResult(player, dealer);

        //then
        assertThat(result).isEqualTo(Result.TIE);
    }

    @Test
    @DisplayName("플레이어가 딜러에게 졌을 때 LOSE 를 반환한다.")
    void player_lose() {
        //given
        Card card1 = Card.of(CardSuit.HEART, CardNumber.FIVE);
        Card card2 = Card.of(CardSuit.CLUB, CardNumber.QUEEN);
        Card card3 = Card.of(CardSuit.HEART, CardNumber.TEN);
        Card card4 = Card.of(CardSuit.DIAMOND, CardNumber.ACE);
        Card card5 = Card.of(CardSuit.SPADE, CardNumber.TEN);
        CardDeck cardDeck = new CardDeck(List.of(card1, card2, card3, card4, card5));
        Dealer dealer = new Dealer(new InitialState(new Hand()));
        Player player = new Player(new Name("이리내"), new InitialState(new Hand()));
        Participants participants = new Participants(dealer, List.of(player));

        //when
        participants.distributeTwoCardsToEach(cardDeck);
        participants.hitOrStandByPlayer(cardDeck, player, true);
        participants.hitOrStandByDealer(cardDeck);
        Result result = Result.checkPlayerResult(player, dealer);

        //then
        assertThat(result).isEqualTo(Result.LOSE);
    }

}
