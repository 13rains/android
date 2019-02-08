package com.a13rain.unodir.aqualife;


import android.arch.lifecycle.LiveData;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.a13rain.unodir.aqualife.controls.ItemDataBox;
import com.a13rain.unodir.aqualife.controls.ListAdapter;
import com.a13rain.unodir.aqualife.db.AquaDB;
import com.a13rain.unodir.aqualife.db.Aquarium;
import com.a13rain.unodir.aqualife.db.AquariumRepos;
import com.a13rain.unodir.aqualife.db.AquariumViewModel;

import java.util.List;

public class HomeFragment extends Fragment {
    ListView lvAquarium;
    ListAdapter listAdapter;
    private AquariumViewModel mAquaViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fgm_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(R.string.nav_home);

        lvAquarium = (ListView) getView().findViewById(R.id.lvAqiarium);

        listAdapter = new ListAdapter(getActivity());

 /*       RecyclerView recyclerView = (RecyclerView)getView().findViewById(R.id.lvAqiarium);
        final AquariumListAdapter adapter = new AquariumListAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Model Provider
        mAquaViewModel= ViewModelProviders.of(this).get(AquariumViewModel.class);

        //observe : model의 LiveData를 관찰한다.
        mAquaViewModel.getAllAquariums().observe(this, new Observer<List<Aquarium>>() {
            @Override
            public void onChanged(@Nullable final List<Aquarium> aquariums) {
                // Update the cached copy of the words in the adapter.
                adapter.setAquariums(aquariums);
            }
        });*/

        initAquariumList();
    }

    private void initAquariumList() {
        Resources resources = getResources();

        Resources res = getResources();

        AquariumRepos mAquariumRepos;
        LiveData<List<Aquarium>> mAllAquarium;
        mAquariumRepos = new AquariumRepos(getActivity().getApplication());
        mAllAquarium = mAquariumRepos.getAllAquariums();

//        for (Aquarium aqua : mAllAquarium) {
//            listAdapter.addItem(
//                    new ItemDataBox(
//                            res.getDrawable(R.drawable.aq301)
//                            , aqua.getName()
//                            , Integer.toString(aqua.getX())
//                            , aqua.getSummary()
//                    ));
//        }

        try {
            AquaDB dbmgr = new AquaDB(getActivity());

            SQLiteDatabase db;

            db = dbmgr.getReadableDatabase();

            Cursor cursor = db.query("aquarium", null, null, null, null, null, null);
            //Cursor c = db.rawQuery("SELECT * FROM aquarium;", null);

            if (cursor.getCount() > 0) {
                cursor.moveToFirst();

                do {
                    listAdapter.addItem(
                            new ItemDataBox(
                                    res.getDrawable(R.drawable.aq301)
                                    , cursor.getString(1)
                                    , cursor.getString(4)
                                    , cursor.getString(7)

                            ));
                } while (cursor.moveToNext());
            }


            cursor.close();
            dbmgr.close();
        } catch (SQLiteException e) {
            int kk = 0;
        }
        //
        // 아이템 데이터 만들기

        final String[] nation = getResources().getStringArray(R.array.tank_name);
//        String[] explain = getResources().getStringArray(R.array.tank_explain);
//        String[] population = getResources().getStringArray(R.array.tank_summary);
        final String[] capital = getResources().getStringArray(R.array.capital);
//
//        listAdapter.addItem(new ItemDataBox(res.getDrawable(R.drawable.aq301), nation[0], population[0], explain[0]));
//        listAdapter.addItem(new ItemDataBox(res.getDrawable(R.drawable.aq201), nation[1], population[1], explain[1]));

        // 리스트뷰에 어댑터 설정
        lvAquarium.setAdapter(listAdapter);

        lvAquarium.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemDataBox currentItem = (ItemDataBox) listAdapter.getItem(position);
                String[] currentData = currentItem.getData();

                Toast.makeText(getActivity(), nation[position] + ": " + capital[position], Toast.LENGTH_LONG).show();
            }
        });
    }
}
