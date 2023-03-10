package view;

public class SingleResultDto {

	final String name;
	final String result;

	public SingleResultDto(String name, String result) {
		this.name = name;
		this.result = result;
	}

	public String getName() {
		return name;
	}

	public String getResult() {
		return result;
	}
}
