package music_individual.demo.business;

import music_individual.demo.persistence.entities.LoginsEntity;

import java.util.List;

public interface ILoginStatisticsManager {
    public void registerLogin(LoginsEntity login);
    public Integer getNumberOfLoginsForTheLastDays(Integer numberOfDays);
}
