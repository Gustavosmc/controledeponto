package Model;

public class ConfigBackup {
	private Integer idconfigbackup;
	private String localSgdb;
	private String localBkp;
	private String localRest;
	public Integer getId() {
		return idconfigbackup;
	}
	public void setId(Integer id) {
		this.idconfigbackup = id;
	}
	public String getLocalSgdb() {
		return localSgdb;
	}
	public void setLocalSgdb(String localSgdb) {
		this.localSgdb = localSgdb;
	}
	public String getLocalBkp() {
		return localBkp;
	}
	public void setLocalBkp(String localBkp) {
		this.localBkp = localBkp;
	}
	public String getLocalRest() {
		return localRest;
	}
	public void setLocalRest(String localRest) {
		this.localRest = localRest;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idconfigbackup == null) ? 0 : idconfigbackup.hashCode());
		result = prime * result + ((localBkp == null) ? 0 : localBkp.hashCode());
		result = prime * result + ((localRest == null) ? 0 : localRest.hashCode());
		result = prime * result + ((localSgdb == null) ? 0 : localSgdb.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConfigBackup other = (ConfigBackup) obj;
		if (idconfigbackup == null) {
			if (other.idconfigbackup != null)
				return false;
		} else if (!idconfigbackup.equals(other.idconfigbackup))
			return false;
		if (localBkp == null) {
			if (other.localBkp != null)
				return false;
		} else if (!localBkp.equals(other.localBkp))
			return false;
		if (localRest == null) {
			if (other.localRest != null)
				return false;
		} else if (!localRest.equals(other.localRest))
			return false;
		if (localSgdb == null) {
			if (other.localSgdb != null)
				return false;
		} else if (!localSgdb.equals(other.localSgdb))
			return false;
		return true;
	}
	
	
}
