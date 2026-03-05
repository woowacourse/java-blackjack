package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class HandTest {

    @Test
    @DisplayName("카드가 한 장 손 패에 추가되어야 한다.")
    void 카드_한_장_뽑기() {
        Hand hand = new Hand();
        hand.drawCard();

        int expected = 1;
        int actual = hand.cards().size();

        assertEquals(expected, actual);
    }

    // 손 패 카드
//    private static Stream<List<Card>> randomCards() {
//        return Stream.of(
//                List.of(new Card(CardRank.QUEEN, CardMark.SPADE)),
//                List.of(new Card(CardRank.QUEEN, CardMark.SPADE), new Card(CardRank.ACE, CardMark.HEART)),
//                List.of(new Card(CardRank.TWO, CardMark.SPADE), new Card(CardRank.ACE, CardMark.HEART), new Card(CardRank.QUEEN, CardMark.CLOVER))
//        );
//    }
}