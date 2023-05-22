package web;

import java.util.ArrayList;
import java.util.List;
import metier.entities.Client;

public class ClientModele {
	List<Client> clients = new ArrayList<>();

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
}