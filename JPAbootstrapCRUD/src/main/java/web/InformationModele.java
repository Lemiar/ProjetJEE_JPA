package web;

import java.util.ArrayList;
import java.util.List;
import metier.entities.Information;

public class InformationModele {
	List<Information> informations = new ArrayList<>();

	public List<Information> getInformations() {
		return informations;
	}

	public void setInformations(List<Information> informations) {
		this.informations = informations;
	}
}