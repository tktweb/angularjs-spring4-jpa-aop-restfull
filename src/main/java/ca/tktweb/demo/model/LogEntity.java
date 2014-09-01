package ca.tktweb.demo.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Table(name="LOG")
@Entity
public class LogEntity extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@ManyToOne
	@JoinColumn(name="user_key")
	private UserEntity owner;
	private String codeAction;
	public UserEntity getOwner() {
		return owner;
	}
	public void setOwner(UserEntity owner) {
		this.owner = owner;
	}
	public String getCodeAction() {
		return codeAction;
	}
	public void setCodeAction(String codeAction) {
		this.codeAction = codeAction;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((codeAction == null) ? 0 : codeAction.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogEntity other = (LogEntity) obj;
		if (codeAction == null) {
			if (other.codeAction != null)
				return false;
		} else if (!codeAction.equals(other.codeAction))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		return true;
	}
}
