package com.risikous.android;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.risikous.android.fragments.CommentFragment;
import com.risikous.android.fragments.HelpFragment;
import com.risikous.android.fragments.IncidentFragment;
import com.risikous.android.fragments.InfoFragment;
import com.risikous.android.fragments.PublicationFragment;

public class MainActivity extends ActionBarActivity {

    private Fragment mCurrentFragment = null;
    private String[] mTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private int _currentPosition = -1;
    private Fragment replaceFragement;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Risikous");

        mTitles = getResources().getStringArray(R.array.nav_title_arrays);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);


        mDrawerList.setAdapter(new ArrayAdapter<>(this,
                R.layout.drawer_list_item, mTitles));

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.nav_drawer_open, R.string.nav_drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                setTitle(mTitles[_currentPosition]);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                setTitle("Risikous");
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        configureActionbar();

        mDrawerLayout.openDrawer(mDrawerList);
        mDrawerList.setSelection(0);
        selectItem(0, true);


    }

    private void configureActionbar() {
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            if (position == _currentPosition) {
                mDrawerLayout.closeDrawer(mDrawerList);
                return;
            }
            selectItem(position, true);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.setGroupVisible(R.id.menugroup, false);

        return super.onPrepareOptionsMenu(menu);
    }

    private void selectItem(int position, boolean closeDrawerAfterSelection) {

        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new InfoFragment();
                _currentPosition = position;
                break;
            case 1:
                if(isConnected()){
                fragment = new IncidentFragment();
                _currentPosition = position;}
                else Toast.makeText(this, "Keine Internetverbindung.", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                if(isConnected()){
                    fragment = new PublicationFragment();
                    _currentPosition = position;}
                else Toast.makeText(this, "Keine Internetverbindung.", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                fragment = new HelpFragment();
                _currentPosition = position;
                break;
            case 4:
                closeDrawerAfterSelection = false;
                break;


        }

        if (fragment != null) {
            mCurrentFragment = fragment;
            if (mCurrentFragment instanceof PublicationFragment)
                replaceFragement = mCurrentFragment;
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fT = fragmentManager.beginTransaction();
            fT.replace(R.id.content_frame, fragment)
                    .commit();


            mDrawerList.setItemChecked(position, true);
        }

        if (closeDrawerAfterSelection) {
            mDrawerLayout.closeDrawer(mDrawerList);
        }

        if (!closeDrawerAfterSelection) {
            mDrawerLayout.openDrawer(mDrawerList);
        }
    }

    public void startCommentActivity(String clickID){
        Fragment fragment = new CommentFragment();
        mCurrentFragment = fragment;
        Bundle bundle = new Bundle();
        bundle.putString("pubID", clickID);
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }


    @Override
    public void onBackPressed() {
        if (mCurrentFragment != null && mCurrentFragment instanceof InfoFragment) {
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case DialogInterface.BUTTON_POSITIVE:
                            finish();
                        case DialogInterface.BUTTON_NEGATIVE:
                            dialog.cancel();
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Wollen Sie die Anwenung schließen?").setPositiveButton("OK", dialogClickListener)
                    .setNegativeButton("Abbrechen", dialogClickListener).show();
        }

        if (mCurrentFragment != null && mCurrentFragment instanceof PublicationFragment) {
            if (((PublicationFragment) mCurrentFragment).onBackPressed()) {
                return;
            }

        }

        if (mCurrentFragment != null && mCurrentFragment instanceof IncidentFragment || mCurrentFragment instanceof HelpFragment || mCurrentFragment instanceof PublicationFragment) {
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case DialogInterface.BUTTON_POSITIVE:
                            selectItem(0, true);
                            setTitle("Info");
                        case DialogInterface.BUTTON_NEGATIVE:
                            dialog.cancel();
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Wollen Sie zu dem Startbildschirm zurückkehren?").setPositiveButton("OK", dialogClickListener)
                    .setNegativeButton("Abbrechen", dialogClickListener).show();
        }

        if (mCurrentFragment != null && mCurrentFragment instanceof CommentFragment) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,replaceFragement).commit();
            mCurrentFragment = replaceFragement;
        }
    }

    public boolean isConnected(){
        ConnectivityManager cM = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cM.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
    /*@Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mCurrentFragment != null && mCurrentFragment instanceof InfoFragment) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                finish();
                            case DialogInterface.BUTTON_NEGATIVE:
                                dialog.cancel();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Wollen Sie die Anwenung schließen?").setPositiveButton("OK", dialogClickListener)
                        .setNegativeButton("Abbrechen", dialogClickListener).show();
            }

            if (mCurrentFragment != null && mCurrentFragment instanceof IncidentFragment || mCurrentFragment instanceof PublicationFragment || mCurrentFragment instanceof HelpFragment) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                selectItem(0, true);
                                setTitle("Info");
                            case DialogInterface.BUTTON_NEGATIVE:
                                dialog.cancel();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Wollen Sie zu dem Startbildschirm zurückkehren?").setPositiveButton("OK", dialogClickListener)
                        .setNegativeButton("Abbrechen", dialogClickListener).show();
            }
        }
        return super.onKeyDown(keyCode, event);
    }*/
}
