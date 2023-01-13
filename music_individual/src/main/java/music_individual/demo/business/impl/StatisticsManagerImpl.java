package music_individual.demo.business.impl;

import music_individual.demo.business.ILoginStatisticsManager;
import music_individual.demo.business.IPlaylistStatisticsManager;
import music_individual.demo.persistence.PlaylistStatisticsRepusitory;
import music_individual.demo.persistence.LoginStatisticsRepusitory;
import music_individual.demo.persistence.entities.ListenedPlaylistsEntity;
import music_individual.demo.persistence.entities.LoginsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsManagerImpl implements ILoginStatisticsManager, IPlaylistStatisticsManager {

    @Autowired
    private LoginStatisticsRepusitory loginStatisticsRepo;

    @Autowired
    private PlaylistStatisticsRepusitory playlistStatisticsRepo;


    @Override
    public void registerLogin(LoginsEntity login) {
        loginStatisticsRepo.save(login);
    }

    @Override
    public Integer getNumberOfLoginsForTheLastDays(Integer numberOfDays) {
        return loginStatisticsRepo.getNumberOfLoginsFromLastDays(numberOfDays);
    }

    @Override
    public void registerListenedPlaylist(ListenedPlaylistsEntity listenedPlaylist) {
        playlistStatisticsRepo.save(listenedPlaylist);
    }

    @Override
    public List<ListenedPlaylistsEntity> getTop5PlaylistsLast7Days() {
        return playlistStatisticsRepo.getTop5ListenedPlaylistsLast7Days();
    }
}
