package jdbcharkat;
import java.util.ArrayList;
import java.util.List;

public class MonivalintaDTO {
	private Integer id;
	private String kysymys;
	private List<VaihtoehtoDTO> vaihtoehdot;
	
	public MonivalintaDTO() {
		this.id = null;
		this.kysymys = null;
		this.vaihtoehdot = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKysymys() {
		return kysymys;
	}

	public void setKysymys(String kysymys) {
		this.kysymys = kysymys;
	}

	public List<VaihtoehtoDTO> getVaihtoehdot() {
		return vaihtoehdot;
	}

	public void setVaihtoehdot(List<VaihtoehtoDTO> vaihtoehdot) {
		this.vaihtoehdot = vaihtoehdot;
	}

	@Override
	public String toString() {
		return "MonivalintaDTO [id=" + id + ", kysymys=" + kysymys + ", vaihtoehdot=" + vaihtoehdot + "]";
	}
}
