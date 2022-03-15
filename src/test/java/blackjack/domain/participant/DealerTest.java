package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.generator.ManualDeckGenerator;
import blackjack.domain.result.MatchStatus;

class DealerTest {

    private final ManualDeckGenerator manualCardStrategy = new ManualDeckGenerator();

    @DisplayName("딜러의 카드 합이 17 미만일 때만 추가로 드로우한다.")
    @ParameterizedTest
    @MethodSource("blackjack.domain.participant.provider.DealerTestProvider#provideForStartWithDrawCardTest")
    void dealerUnderMinimumTotal(final List<Card> initializedCards, final List<Card> expectedCards) {
        final Deck deck = this.generateDeck(initializedCards);
        final Dealer dealer = Dealer.readyToPlay(deck);
        dealer.drawCards(deck, this.generateCardDrawCallback());

        final List<Card> actualCards = dealer.getCards();
        assertThat(actualCards).isEqualTo(expectedCards);
    }

    private CardDrawCallback generateCardDrawCallback() {
        return new CardDrawCallback() {
            @Override
            public boolean isContinuable(String participantName) {
                return true;
            }

            @Override
            public void onUpdate(String dealerName, List<Card> cards) {
            }
        };
    }

    @DisplayName("플레이어의 카드 합계가 버스트일 경우, 플레이어는 패배한다.")
    @ParameterizedTest()
    @MethodSource("blackjack.domain.participant.provider.DealerTestProvider#provideForPlayerLoseByBust")
    void playerLoseByBust(final List<Card> initializedCards,
                          final List<Card> drewCards,
                          final MatchStatus expectedPlayerMatchResult) {
        final Deck deck = this.generateDeck(initializedCards, drewCards);
        final Dealer dealer = Dealer.readyToPlay(deck);
        final Player player = Player.readyToPlay("playerName", deck);
        player.drawCard(deck);

        final MatchStatus actualPlayerMatchStatus = dealer.judgeWinner(player);
        assertThat(actualPlayerMatchStatus).isEqualTo(expectedPlayerMatchResult);
    }

    @DisplayName("딜러의 카드 합계가 버스트일 경우, 플레이어가 승리한다.")
    @ParameterizedTest()
    @MethodSource("blackjack.domain.participant.provider.DealerTestProvider#provideForDealerLoseByBust")
    void dealerLoseByBust(final List<Card> initializedCards,
                          final List<Card> drewCards,
                          final MatchStatus expectedPlayerMatchResult) {
        final Deck deck = this.generateDeck(initializedCards, drewCards);
        final Dealer dealer = Dealer.readyToPlay(deck);
        final Player player = Player.readyToPlay("playerName", deck);
        dealer.drawCard(deck);

        final MatchStatus actualPlayerMatchStatus = dealer.judgeWinner(player);
        assertThat(actualPlayerMatchStatus).isEqualTo(expectedPlayerMatchResult);
    }

    @DisplayName("딜러의 카드 합계가 플레이어보다 크거나 같으면 플레이어가 패배한다.")
    @ParameterizedTest
    @MethodSource("blackjack.domain.participant.provider.DealerTestProvider#provideForDealerCalculateWinningResultTest")
    void dealerJudgeWinnerTest(final List<Card> initializedCards, final MatchStatus expectedPlayerMatchResult) {
        final Deck deck = this.generateDeck(initializedCards);
        final Dealer dealer = Dealer.readyToPlay(deck);
        final Player player = Player.readyToPlay("playerName", deck);

        final MatchStatus actualPlayerMatchStatus = dealer.judgeWinner(player);
        assertThat(actualPlayerMatchStatus).isEqualTo(expectedPlayerMatchResult);
    }

    private Deck generateDeck(final List<Card> initializedCards1, final List<Card> initializedCards2) {
        final List<Card> initializedCards = new ArrayList<>();
        initializedCards.addAll(initializedCards1);
        initializedCards.addAll(initializedCards2);
        return generateDeck(initializedCards);
    }

    private Deck generateDeck(final List<Card> initializedCards) {
        manualCardStrategy.initCards(initializedCards);
        return Deck.generate(manualCardStrategy);
    }

}
