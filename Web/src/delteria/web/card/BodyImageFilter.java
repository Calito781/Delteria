package delteria.web.card;

import java.awt.image.RGBImageFilter;
import java.util.Map;

public class BodyImageFilter extends RGBImageFilter {

	private final Map<String, String> colors;

	public BodyImageFilter(Map<String, String> colors) {
		this.colors = colors;
	}

	@Override
	public int filterRGB(int x, int y, int rgba) {
		switch (rgba & 0xFFFFFF) {
		case 0xFFAD6B: // skin
			return rgba	& 0xFF000000 | (Color.valueOf(this.colors.get("skin").toUpperCase()).rgb) & 0xFFFFFF;
		case 0xCE1829: // shoes
			return rgba	& 0xFF000000 | (Color.valueOf(this.colors.get("shoes").toUpperCase()).rgb) & 0xFFFFFF;
		case 0xFF0000: // sleeves
			return rgba	& 0xFF000000 | (Color.valueOf(this.colors.get("sleeves").toUpperCase()).rgb) & 0xFFFFFF;
		case 0xFFFFFF: // coat
			return rgba	& 0xFF000000 | (Color.valueOf(this.colors.get("coat").toUpperCase()).rgb) & 0xFFFFFF;
		case 0x0000FF: // belt
			return rgba	& 0xFF000000 | (Color.valueOf(this.colors.get("belt").toUpperCase()).rgb) & 0xFFFFFF;
			default:
				return rgba;
		}
		
	}

}