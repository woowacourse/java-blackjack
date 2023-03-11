package view;

import java.util.List;

public class MultiResultsDto {

	final String name;
	final List<String> results;

	public MultiResultsDto(String name, List<String> results) {
		this.name = name;
		this.results = results;
	}

	public String getName() {
		return name;
	}

	public List<String> getResults() {
		return results;
	}
}
