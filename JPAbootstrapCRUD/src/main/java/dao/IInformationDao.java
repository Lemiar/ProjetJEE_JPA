package dao;

import java.util.List;
import metier.entities.Information;

public interface IInformationDao {

public Information save(Information information);
public Information getInformation(Long id);
public Information updateInformation(Information information);
public void deleteInformation(Long id);
public List<Information> getAllInformations();
}