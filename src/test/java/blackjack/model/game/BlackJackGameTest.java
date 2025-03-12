package blackjack.model.game;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.card.Card;
import blackjack.model.card.CardShape;
import blackjack.model.card.CardType;
import blackjack.model.player.Dealer;
import blackjack.model.player.Participant;
import blackjack.model.player.Participants;
import java.util.List;

import blackjack.model.player.PlayerName;
import org.junit.jupiter.api.Test;

class BlackJackGameTest {

    @Test
    void 게임이_시작하면_모든_플레이어에게_카드를_두장씩_배분한다() {
        // given
        Dealer dealer = new Dealer();
        Participant participant1 = new Participant(new PlayerName("벡터"), new BettedMoney(10_000));
        Participant participant2 = new Participant(new PlayerName("한스"), new BettedMoney(10_000));
        Participants participants = new Participants(List.of(participant1, participant2));
        DeckInitializer deckInitializer = new DeckInitializer();

        // when
        BlackJackGame blackJackGame = new BlackJackGame(deckInitializer, dealer, participants);
        blackJackGame.initializeGame();

        // then
        assertThat(dealer.getReceivedCards().size()).isEqualTo(2);
        assertThat(participant1.getReceivedCards().size()).isEqualTo(2);
        assertThat(participant2.getReceivedCards().size()).isEqualTo(2);

    }

    @Test
    void 참가자가_카드를_받는다() {
        // given
        Dealer dealer = new Dealer();
        Participant participant1 = new Participant(new PlayerName("벡터"), new BettedMoney(10_000));
        Participant participant2 = new Participant(new PlayerName("한스"), new BettedMoney(10_000));
        Participants participants = new Participants(List.of(participant1, participant2));
        DeckInitializer deckInitializer = new DeckInitializer();

        // when
        BlackJackGame blackJackGame = new BlackJackGame(deckInitializer, dealer, participants);
        blackJackGame.giveCardToCurrentTurnParticipant(true);
        blackJackGame.giveCardToCurrentTurnParticipant(true);

        // then
        assertThat(participant1.getReceivedCards().size()).isEqualTo(2);
    }

    @Test
    void 참가자가_카드를_받지_않는다() {
        // given
        Dealer dealer = new Dealer();
        Participant participant1 = new Participant(new PlayerName("벡터"), new BettedMoney(10_000));
        Participant participant2 = new Participant(new PlayerName("한스"), new BettedMoney(10_000));
        Participants participants = new Participants(List.of(participant1, participant2));
        DeckInitializer deckInitializer = new DeckInitializer();

        // when
        BlackJackGame blackJackGame = new BlackJackGame(deckInitializer, dealer, participants);
        blackJackGame.giveCardToCurrentTurnParticipant(true);
        blackJackGame.giveCardToCurrentTurnParticipant(false);

        // then
        assertThat(participant1.getReceivedCards().size()).isEqualTo(1);
        assertThat(blackJackGame.getCurrentTurnParticipant()).isEqualTo(participant2);
    }

    @Test
    void 딜러에게_카드를_추가한다() {
        // given
        Dealer dealer = new Dealer();
        Participant participant1 = new Participant(new PlayerName("벡터"), new BettedMoney(10_000));
        Participant participant2 = new Participant(new PlayerName("한스"), new BettedMoney(10_000));
        Participants participants = new Participants(List.of(participant1, participant2));
        DeckInitializer deckInitializer = new DeckInitializer();

        // when
        BlackJackGame blackJackGame = new BlackJackGame(deckInitializer, dealer, participants);
        blackJackGame.drawDealerCard();

        // then
        assertThat(dealer.getReceivedCards().size()).isEqualTo(1);
    }

    @Test
    void 딜러의_포인트가_16_이하면_카드를_더_받아야_한다() {
        // given
        Dealer dealer = new Dealer();
        Participant participant1 = new Participant(new PlayerName("벡터"), new BettedMoney(10_000));
        Participant participant2 = new Participant(new PlayerName("한스"), new BettedMoney(10_000));
        Participants participants = new Participants(List.of(participant1, participant2));
        DeckInitializer deckInitializer = new DeckInitializer();
        BlackJackGame blackJackGame = new BlackJackGame(deckInitializer, dealer, participants);
        dealer.putCard(new Card(CardShape.HEART, CardType.NORMAL_2));
        // when

        // then
        assertThat(blackJackGame.isDealerCardDrawable()).isTrue();
    }

    @Test
    void 딜러의_포인트가_17_이상이면_카드를_더_받지_않는다() {
        // given
        Dealer dealer = new Dealer();
        Participant participant1 = new Participant(new PlayerName("벡터"), new BettedMoney(10_000));
        Participant participant2 = new Participant(new PlayerName("한스"), new BettedMoney(10_000));
        Participants participants = new Participants(List.of(participant1, participant2));
        DeckInitializer deckInitializer = new DeckInitializer();
        BlackJackGame blackJackGame = new BlackJackGame(deckInitializer, dealer, participants);
        dealer.putCard(new Card(CardShape.HEART, CardType.KING));
        dealer.putCard(new Card(CardShape.HEART, CardType.NORMAL_9));
        // when

        // then
        assertThat(blackJackGame.isDealerCardDrawable()).isFalse();
    }
}
