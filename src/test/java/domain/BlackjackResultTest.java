package domain;

import static org.assertj.core.api.Assertions.*;

import domain.shuffle.NoShuffleStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class BlackjackResultTest {

    private final List<Card> cardList = List.of(
            Card.of(Rank.NINE, Suit.DIAMOND),
            Card.of(Rank.NINE, Suit.CLOVER),
            Card.of(Rank.EIGHT, Suit.DIAMOND));
    private final String name = "포비";

    @Test
    @DisplayName("Dealer 수익 정상 계산 여부를 판정한다.")
    void 딜러_수익_정상_계산_테스트(){
        Deck deck = Deck.of(new NoShuffleStrategy());
        Player player = Player.of(Cards.of(cardList), name);
        Dealer dealer = Dealer.of(deck.drawInitialHand());

        BlackjackResult blackjackResult = BlackjackResult.of();
        blackjackResult.add(player, 1000L);
        //blackjackResult.resolveResult(player, dealer);
        long dealerProfit = blackjackResult.dealerProfit();

        long expect = 1000L;
        //assertThat(dealerProfit).isEqualTo(expect);
    }

    @Test
    @DisplayName("Player 수익 정상 계산 여부를 판정한다.")
    void 플레이어_수익_정상_계산_테스트(){
        Deck deck = Deck.of(new NoShuffleStrategy());
        Player player = Player.of(Cards.of(cardList), name);
        Dealer dealer = Dealer.of(deck.drawInitialHand());


        BlackjackResult blackjackResult = BlackjackResult.of();
        blackjackResult.add(player, 1000L);
        //blackjackResult.resolveResult(player, dealer);
        //long dealerProfit = blackjackResult.playerProfit(blackjackResult);

        long expect = 1000L;
        //assertThat(dealerProfit).isEqualTo(expect);
    }
}