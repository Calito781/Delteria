package delteria.web.card;

public class DurationFormatter {

	public String format(long seconds) {
		long hours = getHours(seconds);
		long minutes = getMinutes(seconds) % 60;
		
		if (hours > 0) {
			return String.format("%dh %dm", hours, minutes);
		} else {
			return String.format("%dm", minutes);
		}
	}

	private long getSeconds(long seconds) {
		return seconds;
	}

	private long getMinutes(long seconds) {
		return getSeconds(seconds) / 60L;
		
	}

	private long getHours(long seconds) {
		return getMinutes(seconds) / 60L;
	}
}
