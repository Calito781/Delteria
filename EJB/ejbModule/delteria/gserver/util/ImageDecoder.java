package delteria.gserver.util;

import java.awt.Image;
import java.io.ByteArrayInputStream;

import javax.imageio.ImageIO;

import com.sun.org.apache.xml.internal.security.utils.Base64;

public class ImageDecoder {
	public Image decode(String encoded_image) {
		try {
			byte[] image = Base64.decode(encoded_image);
			return ImageIO.read(new ByteArrayInputStream(image));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
