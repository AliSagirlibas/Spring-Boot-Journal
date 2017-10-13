package net.onur.springbootjournal.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
public class Journal {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String title ;
	
	
	@NotNull
	private Date created ;
	private String summary;
	
	@Transient
	private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("MM/dd/yyyy");
	
	public Journal() {
		
	}
	
	public Journal(String title,  String summary,String createdDateStr) throws ParseException {
		super();
		this.title = title;
		this.created = simpleDateFormat.parse(createdDateStr) ;
		this.summary = summary;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	
	public String getCreatedDateAsShortDate()	
	{
		return simpleDateFormat.format(created);
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Journal [id=");
		builder.append(id);
		builder.append(", title=");
		builder.append(title);
		builder.append(", created=");
		builder.append(getCreatedDateAsShortDate());
		builder.append(", summary=");
		builder.append(summary);
		builder.append("]");
		return builder.toString();
	}
	
	
}
