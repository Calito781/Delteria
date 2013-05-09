package delteria.repo.file;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class File {

	@Id
	public String filename;
		
	@Column
	public byte[] content;
	
	File() {
	}
	
	public File(String filename, byte[] content) {
		this.filename = filename;
		this.content = content;
	}
}
