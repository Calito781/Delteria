package delteria.web.card;

public enum Color {
	DARKBLUE(0x0000C5),
	BLUE(0x0000FF),
	LIGHTBLUE(0x8484FF),
	CYNOBER(0x00FFFF),
	DARKPURPLE(0x840084),
	PURPLE(0xFF00FF),
	BROWN(0x840000),
	DARKRED(0xCE1829),
	RED(0xFF0000),
	PINK(0xFF8484),
	ORANGE(0xFFAD6B),
	YELLOW(0xFFFF00),
	DARKGREEN(0x00AD00),
	GREEN(0x00FF00),
	LIGHTGREEN(0x84FF84),
	BLACK(0x000008),
	GRAY(0x848484),
	LIGHTGRAY(0xCECECE),
	WHITE(0xFFFFFF)
	;
	
	public final int rgb;
	private Color(int rgb) {
		this.rgb = rgb;
	}
}