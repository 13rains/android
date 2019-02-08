package com.a13rain.unodir.aqualife.db;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AquariumRepos {
    private AquariumDao mAquaDao;
    private LiveData<List<Aquarium>> mAllAquarium;

    public AquariumRepos(Application application) {
        AquariumRoomDB db = AquariumRoomDB.getInstance(application);

        mAquaDao = db.aquariumDao();

        // select도 ui thread에서 실행하면 안되나?
        // Cannot access database on the main thread since it may potentially lock the UI for a long period of tim
        mAllAquarium = mAquaDao.getAllAquarium();
//        Runnable addRunnable = new Runnable() {
//            @Override
//            public void run() {
//                mAllAquarium = mAquaDao.getAllAquarium();
//            }
//        };
//        Executor diskIO = Executors.newSingleThreadExecutor();
//
//        diskIO.execute(addRunnable);
    }

    public LiveData<List<Aquarium>> getAllAquariums() {
        return mAllAquarium;
    }

    public void insert(final Aquarium aquarium) {
        Runnable addRunnable = new Runnable() {
            @Override
            public void run() {
                mAquaDao.insert(aquarium);
            }
        };
        Executor diskIO = Executors.newSingleThreadExecutor();

        diskIO.execute(addRunnable);
    }
    //new insertAsyncTask(mAquaDao).execute(aquarium);

    private static class insertAsyncTask extends AsyncTask<Aquarium, Void, Void> {
        private AquariumDao mAsyncTaskDao;

        insertAsyncTask(AquariumDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Aquarium... aquariums) {
            mAsyncTaskDao.insert(aquariums[0]);
            return null;
        }
    }
}
