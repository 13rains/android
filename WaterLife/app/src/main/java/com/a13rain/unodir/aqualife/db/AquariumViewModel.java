package com.a13rain.unodir.aqualife.db;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class AquariumViewModel extends AndroidViewModel {
    private AquariumRepos mRepository;

    private LiveData<List<Aquarium>> mAquariums;

    public AquariumViewModel(Application application) {
        super(application);
        mRepository = new AquariumRepos(application);
        mAquariums = mRepository.getAllAquariums();
    }

    public LiveData<List<Aquarium>> getAllAquariums() {
        return mAquariums;
    }

    public void insert(Aquarium aquarium) {
        mRepository.insert(aquarium);
    }
}
