package my.com.tm.portal.leasing.auditing;

import static javax.persistence.TemporalType.TIMESTAMP;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U> {
      
	@CreatedDate
    @Temporal(TIMESTAMP)
	@Column(name = "created_datetime",nullable=false)
    protected Date createdDateTime;
    
	@LastModifiedDate
    @Temporal(TIMESTAMP)
	@Column(name = "last_modified_datetime",nullable=false)
    protected Date lastModifiedDateTime;
	
	@CreatedBy
	@Column(name = "created_by",length=50,nullable=false)
    protected U createdBy;
	
	@LastModifiedBy
	@Column(name = "last_modified_by",length=50,nullable=false)
    protected U lastModifiedBy;

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Date getLastModifiedDateTime() {
		return lastModifiedDateTime;
	}

	public void setLastModifiedDateTime(Date lastModifiedDateTime) {
		this.lastModifiedDateTime = lastModifiedDateTime;
	}

	public U getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(U createdBy) {
		this.createdBy = createdBy;
	}

	public U getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(U lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
}