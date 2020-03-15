package com.blackjack.exception;

/**
 * EmptyCardDeckException class
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/03/15
 */
public class EmptyCardDeckException extends RuntimeException {
	public EmptyCardDeckException() {
		super("카드가 남아있지 않아 더 이상 뽑을 수 없습니다.");
	}
}
