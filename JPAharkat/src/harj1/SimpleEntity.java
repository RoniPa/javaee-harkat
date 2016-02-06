package harj1;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: SimpleEntity
 *
 */
@Entity
@Table(name="SIMPLEENTITY")
@NamedQuery(name = "selectAll", query = "SELECT a from SimpleEntity a")
public class SimpleEntity implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="`name`")
	private String name;
	@Column(name="`desc`")
	private String desc;
	
	public SimpleEntity(){
		super();
		this.name = null;
		this.desc = null;
	}
	
	public Long getId(){
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	@Override
	public String toString(){
		return "Tämä on SimpleEntity nimeltä "+this.name+", "+this.desc;
	}
}
