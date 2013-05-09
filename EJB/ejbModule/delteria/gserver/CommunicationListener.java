package delteria.gserver;

import com.google.gson.JsonElement;

public interface CommunicationListener {
	void onReceivedMessage(JsonElement message);
	boolean isExpired();
}
