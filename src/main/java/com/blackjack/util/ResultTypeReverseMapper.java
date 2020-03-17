package com.blackjack.util;

import static com.blackjack.domain.ResultType.DRAW;
import static com.blackjack.domain.ResultType.LOSE;
import static com.blackjack.domain.ResultType.WIN;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.blackjack.domain.ResultType;

public class ResultTypeReverseMapper {
	private static final Map<ResultType, ResultType> resultTypeReverseMapper = new HashMap<>();

	static {
		resultTypeReverseMapper.put(WIN, LOSE);
		resultTypeReverseMapper.put(DRAW, DRAW);
		resultTypeReverseMapper.put(LOSE, WIN);
	}

	private ResultTypeReverseMapper() {
	}

	public static ResultType getReverseResultType(ResultType resultType) {
		return Optional.ofNullable(resultTypeReverseMapper.get(resultType))
				.orElseThrow(() -> new IllegalArgumentException("ResultType이 존재하지 않습니다."));
	}
}
