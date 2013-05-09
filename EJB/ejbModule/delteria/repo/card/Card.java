package delteria.repo.card;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Card {

	@Id
	public String account;
	
	@Column
	public String nickname;

	@Column
	public boolean isOnline;
	
	@Column
	public long onlineDuration;
	
	@Column
	public String hat;
	
	@Column
	public String head;
	
	@Column
	public String body;
	
	@Column
	public Map<String, String> colors;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	public Date expiry_date;
	
	public Card() {}

	public Card(String account, String nickname, boolean isOnline, long onlineDuration, String hat, String head, String body, Map<String, String> colors) {
		this.account = account;
		this.nickname = nickname;
		this.isOnline = isOnline;
		this.onlineDuration = onlineDuration;
		this.hat = hat;
		this.head = head;
		this.body = body;
		this.colors = colors;
		
		Calendar current_time = new GregorianCalendar();
		current_time.add(5, Calendar.MINUTE);
		this.expiry_date = current_time.getTime();
	}
}