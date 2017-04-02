package com.iit.glid2017.app;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ListActivityFragment extends Fragment implements ResultDialog.ResultDialogListener {

    private static final String CONTENT_LIST_KEY = "content_list_key";

    private ArrayList<DataModel> mContentList;
    private CustomAdapter customAdapter;
    private View mView;

    public ListActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mView = view;

        recyclerView.setLayoutManager(linearLayoutManager);

        if (savedInstanceState != null) {
            Log.v("onCreateView", "restore");
            mContentList = (ArrayList<DataModel>) savedInstanceState.getSerializable(CONTENT_LIST_KEY);
        } else {
            //mContentList = new ArrayList<>();
            initContent();
        }
        customAdapter = new CustomAdapter(getActivity().getApplicationContext(), mContentList);
        recyclerView.setAdapter(customAdapter);

        setHasOptionsMenu(true);


        return view;
    }

    private void initContent() {
        mContentList = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            DataModel dataModel = new DataModel("Title " + i, "Description " + i, R.mipmap.ic_launcher, false);
            mContentList.add(dataModel);
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(CONTENT_LIST_KEY, mContentList);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.menu_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_add:
                // Log.v("test", "add menu item clicked");
                addItemPerform();
                break;
            case R.id.action_delete:
                //  Log.v("test", "add menu item clicked");
                onRemove();
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    private void addItemPerform() {
        ResultDialog resultDialog = ResultDialog.newInstance(this);
        resultDialog.show(getFragmentManager(), "");
    }

    @Override
    public void onSave(String title, String description) {
        DataModel dataModel = new DataModel(title, description, R.mipmap.ic_launcher, false);
        mContentList.add(0, dataModel);

        customAdapter.notifyItemInserted(0);
    }


    public void onRemove() {
        int pos;

        ArrayList<DataModel> newContentList = new ArrayList<>();
        newContentList.clear();
        if (mContentList.size() == 0) {
            Snackbar.make(mView, "There is no item !!", Snackbar.LENGTH_LONG).show();
        } else {

            int numberOfElementDeleted = 0;
            for (DataModel d : mContentList) {
                Log.v("msg", d.getTitle() + d.isCheck());
                if (d.isCheck()) {
                    pos = mContentList.indexOf(d);
                    customAdapter.notifyItemRemoved(pos);
                    customAdapter.notifyItemRangeChanged(pos, mContentList.size());
                    numberOfElementDeleted++;
                } else {
                    newContentList.add(d);
                }

            }
            if (numberOfElementDeleted == 0) {
                Snackbar.make(mView, "No item selected !!", Snackbar.LENGTH_LONG).show();
            } else {
                Snackbar.make(mView, numberOfElementDeleted + " item deleted successfully!!", Snackbar.LENGTH_LONG).show();
            }
        }

        mContentList.clear();
        mContentList.addAll(newContentList);


      /*  for (Integer i : tabPos)
        {
            mContentList.remove(i);
            customAdapter.notifyItemRemoved(i);
        }*/

/*
        for (DataModel d: mContentList ) {
            if(d.isCheck())
            {
                pos=mContentList.indexOf(d);
                mContentList.remove(d);
                customAdapter.notifyItemRemoved(pos);
            }

        }
*/
    }

}
