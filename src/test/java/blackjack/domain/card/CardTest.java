package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardTest {

    @Test
    @DisplayName("하트 타입의 숫자 2카드 생성을 확인한다")
    void createHeartNumberCard() {
        final Card card = new Heart(CardNumber.TWO);
        final String expectedPattern = "하트";
        final int expectedNumber = 2;

        final String actualPattern = card.getCardPattern();
        final int actualNumber = card.getCardNumber();
        assertThat(actualPattern).isEqualTo(expectedPattern);
        assertThat(actualNumber).isEqualTo(expectedNumber);
    }

    @Test
    @DisplayName("클로버 타입의 숫자 2카드 생성을 확인한다")
    void createCloverNumberCard() {
        final Card card = new Clover(CardNumber.THREE);
        final String expectedPattern = "클로버";
        final int expectedNumber = 3;

        final String actualPattern = card.getCardPattern();
        final int actualNumber = card.getCardNumber();
        assertThat(actualPattern).isEqualTo(expectedPattern);
        assertThat(actualNumber).isEqualTo(expectedNumber);
    }

    @Test
    @DisplayName("스페이드 타입의 King 카드 생성을 확인한다.")
    void createSpecialTenCard() {
        final Card card = new Spade(CardNumber.KING);
        final String expectedPattern = "스페이드";
        final int expectedNumber = 10;

        final String actualPattern = card.getCardPattern();
        final int actualNumber = card.getCardNumber();
        assertThat(actualPattern).isEqualTo(expectedPattern);
        assertThat(actualNumber).isEqualTo(expectedNumber);
    }

    @Test
    @DisplayName("다이아몬드 타입의 Ace 카드 생성을 확인한다.")
    void createSpecialQueenCard() {
        final Card card = new Diamond(CardNumber.ACE);
        final String expectedPattern = "다이아몬드";
        final int expectedNumber = 11;

        final String actualPattern = card.getCardPattern();
        final int actualNumber = card.getCardNumber();
        assertThat(actualPattern).isEqualTo(expectedPattern);
        assertThat(actualNumber).isEqualTo(expectedNumber);
    }
}
